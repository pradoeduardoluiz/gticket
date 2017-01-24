package br.com.gticket.bo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.TicketDesenvolvimentoDAO;
import br.com.gticket.model.StatusProgresso;
import br.com.gticket.model.StatusTicket;
import br.com.gticket.model.TicketDesenvolvimento;
import br.com.gticket.util.Util;

public class TicketDesenvolvimentoBO extends TicketBO {

	private TicketDesenvolvimentoDAO dao;

	public TicketDesenvolvimentoBO() {
		dao = new TicketDesenvolvimentoDAO();
	}

	public TicketDesenvolvimento salvar(TicketDesenvolvimento ticket)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		validaCamposObrigatorios(ticket);

		if (inclusao(ticket)) {
			inicializar(ticket);
		}

		return dao.salvar(ticket);

	}

	private void inicializar(TicketDesenvolvimento ticket) {
		ticket.setAtivo(true);
		ticket.setEmAndamento(false);
		ticket.setStatusTicket(StatusTicket.PENDENTE);
		ticket.setStatusProcesso(StatusProgresso.PENDENTE);
		ticket.setTempoDesenvolvimento(0);
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

	public List<TicketDesenvolvimento> listar(String filtro) {
		return dao.listar(filtro);
	}

	public TicketDesenvolvimento buscarPorId(Integer id) {
		return dao.buscarPorId(id);

	}

	public void aprovar(TicketDesenvolvimento ticket)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		validaCamposObrigatorios(ticket);

		if (reprovado(ticket)) {

			if (campoVazio(ticket.getMotivoReprovacao())) {

				throw new ValorEmBrancoException(
						"Se ticket reprovado é obrigatório informar um motivo!");

			}

		}

		if (aprovado(ticket)) {

			if (campoVazio(ticket.getAnalise())) {
				throw new ValorEmBrancoException(
						"Se ticket aprovado é obrigatório informar um análise!");
			}

			if (campoVazio(ticket.getDesenvolvedor())) {
				throw new ValorEmBrancoException(
						"Se ticket aprovado é obrigatório informar um desenvolvedor!");
			}

		}

		ticket.setStatusProcesso(StatusProgresso.DESENVOLVIMENTO);

		dao.salvar(ticket);

	}

	private boolean aprovado(TicketDesenvolvimento ticket) {
		return ticket.getStatusTicket() == StatusTicket.APROVADO;
	}

	private boolean reprovado(TicketDesenvolvimento ticket) {
		return ticket.getStatusTicket() == StatusTicket.REPROVADO;
	}

	public void play(TicketDesenvolvimento ticket)
			throws ValorInvalidoException {

		if (ticket.getEmAndamento() == null) {
			ticket.setEmAndamento(false);
		}

		if (ticket.getEmAndamento()) {
			throw new ValorInvalidoException("Ticket #" + ticket.getId()
					+ " já está em andamento!");
		}

		pausarTodos();

		ticket.setEmAndamento(true);
		ticket.setDataUltimoPlay(new Date());
		ticket.setDataUltimoPause(null);

		dao.salvar(ticket);

	}

	private void pausarTodos() throws ValorInvalidoException {
		List<TicketDesenvolvimento> ticktesAprovados = dao.listar("aprovados");

		for (TicketDesenvolvimento ticketAprovado : ticktesAprovados) {
			if (emAndamento(ticketAprovado)) {
				pause(ticketAprovado);
			}
		}
	}

	private boolean emAndamento(TicketDesenvolvimento ticket) {
		return ticket.getEmAndamento() != null && ticket.getEmAndamento();
	}

	public void pause(TicketDesenvolvimento ticket)
			throws ValorInvalidoException {

		if (!ticket.getEmAndamento()) {
			throw new ValorInvalidoException("Ticket #" + ticket.getId()
					+ " já está pausado");
		}

		ticket.setEmAndamento(false);
		ticket.setDataUltimoPause(new Date());
		ticket.setTempoDesenvolvimento(Util.calcularTempoDesenvolvimento(
				ticket.getTempoDesenvolvimento(), ticket.getDataUltimoPlay(),
				ticket.getDataUltimoPause()));

		dao.salvar(ticket);
	}

	public void finalizar(TicketDesenvolvimento ticket)
			throws ValorInvalidoException {

		try {
			Util.validarHora(ticket.getStrTempoDesenvolvimento());
		} catch (ParseException e) {

			throw new ValorInvalidoException(
					"Tempo de desenvolvimento inválido!");
		}

		if (emAndamento(ticket)) {
			pause(ticket);
		}
		ticket.setStatusProcesso(StatusProgresso.TESTES);
		dao.salvar(ticket);

	}
}

package br.com.gticket.bo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.TicketDesenvolvimentoDAO;
import br.com.gticket.model.Ajuste;
import br.com.gticket.model.StatusProgresso;
import br.com.gticket.model.StatusTicket;
import br.com.gticket.model.TicketContato;
import br.com.gticket.model.TicketDesenvolvimento;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.EmailUtil;
import br.com.gticket.util.SessionUtil;
import br.com.gticket.util.Util;

public class TicketDesenvolvimentoBO extends TicketBO {

	private TicketDesenvolvimentoDAO dao;

	public TicketDesenvolvimentoBO() {
		dao = new TicketDesenvolvimentoDAO();
	}

	public TicketDesenvolvimento salvar(TicketDesenvolvimento ticket)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException, EmailException {

		validaCamposObrigatorios(ticket);
		TicketDesenvolvimento ticketSalvo = null;
		boolean inclusao = false;

		if (inclusao(ticket)) {
			inicializar(ticket);
			inclusao = true;
		}

		ticketSalvo = dao.salvar(ticket);

		if (inclusao) {
			if (ticket.getEnviarEmail()) {
				try {
					EmailUtil.enviarEmailTicketAbertura(ticketSalvo);
				} catch (EmailException e) {
					throw new EmailException("Ticket #" + ticketSalvo.getId()
							+ "Foi criado mas não foi possível enviar e-mail!");
				}
			}
		}

		return ticketSalvo;

	}

	private void inicializar(TicketDesenvolvimento ticket) {
		ticket.setAtivo(true);
		ticket.setEmAndamento(false);
		ticket.setStatusTicket(StatusTicket.PENDENTE);
		ticket.setStatusProcesso(StatusProgresso.PENDENTE);
		ticket.setTempoDesenvolvimento(0);
		ticket.setUsuarioInclusao((Usuario) SessionUtil
				.getParam("usuarioLogado"));
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
			ValorInvalidoException, EmailException {

		validaCamposObrigatorios(ticket);

		if (reprovado(ticket)) {

			if (campoVazio(ticket.getAnalise())) {

				throw new ValorEmBrancoException(
						"Se ticket reprovado é obrigatório informar um motivo!");

			}

			ticket.setStatusProcesso(StatusProgresso.REPROVADA);
			dao.salvar(ticket);

			try {
				EmailUtil.enviarEmailTicketReprovado(ticket);
			} catch (EmailException e) {

				throw new EmailException("Ticket #" + ticket.getId()
						+ "Foi reprovado mas não foi possível enviar e-mail!");
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

			if (campoVazio(ticket.getAnalistaTeste())) {
				throw new ValorEmBrancoException(
						"Se ticket aprovado é obrigatório informar um análista de testes!");
			}

			ticket.setStatusProcesso(StatusProgresso.DESENVOLVIMENTO);
			dao.salvar(ticket);

			try {
				EmailUtil.enviarEmailTicketAprovacao(ticket);
			} catch (EmailException e) {

				throw new EmailException("Ticket #" + ticket.getId()
						+ "Foi aprovado mas não foi possível enviar e-mail!");
			}

		}

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

	public void enviarParatestes(TicketDesenvolvimento ticket)
			throws ValorInvalidoException, EmailException {

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

		try {
			EmailUtil.enviarEmailTicketTestes(ticket);
		} catch (EmailException e) {
			throw new EmailException(
					"Ticket #"
							+ ticket.getId()
							+ "Foi enviado para testes mas não foi possível enviar e-mail!");
		}

	}

	public void notificarDesenv(TicketDesenvolvimento ticket) throws Exception {

		boolean temAjuste = temAjuste(ticket.getAjustes());

		if (!temAjuste) {
			throw new Exception(
					"Para enviar notificação para desenvolvedor é necessário ter pelo menos um ajuste pendente!");
		}

		ticket.setStatusProcesso(StatusProgresso.AJUSTES);
		dao.salvar(ticket);

		try {

			EmailUtil.enviarEmailNotificarDesenv(ticket);

		} catch (EmailException e) {
			throw new EmailException(
					"Ticket #"
							+ ticket.getId()
							+ "Foi enviado para ajustes mas não foi possível enviar e-mail!");
		}

	}

	private boolean temAjuste(List<Ajuste> ajustes) {

		boolean temAjuste = false;

		for (Ajuste ajuste : ajustes) {
			if (!ajuste.getConcluido()) {
				temAjuste = true;
			}
		}
		return temAjuste;
	}

	public void notificarTester(TicketDesenvolvimento ticket) throws Exception {

		boolean temAjuste = temAjuste(ticket.getAjustes());

		if (temAjuste) {
			throw new Exception(
					"Para enviar notificação para tester é necessário concluír todos os ajuste pendente!");
		}

		ticket.setStatusProcesso(StatusProgresso.TESTES);
		dao.salvar(ticket);

		try {

			EmailUtil.enviarEmailNotificarTester(ticket);

		} catch (EmailException e) {
			throw new EmailException(
					"Ticket #"
							+ ticket.getId()
							+ "Foi enviado para testes mas não foi possível enviar e-mail!");
		}

	}

	public void finalizarTestes(TicketDesenvolvimento ticket) throws Exception {

		boolean temAjuste = temAjuste(ticket.getAjustes());

		if (temAjuste) {
			throw new Exception(
					"Para finalizar ticket é necessário concluír todos os ajuste pendente!");
		}

		ticket.setStatusProcesso(StatusProgresso.FINALIZADO);
		ticket.setDataDeFinalizacao(new Date());
		dao.salvar(ticket);

		try {

			EmailUtil.enviarEmailTicketFinalizado(ticket);

		} catch (EmailException e) {
			throw new EmailException("Ticket #" + ticket.getId()
					+ "Foi finalizado mas não foi possível enviar e-mail!");
		}

	}

}

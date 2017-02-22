package br.com.gticket.bo;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.AgendaDAO;
import br.com.gticket.model.Agenda;
import br.com.gticket.model.Ajuste;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.EmailUtil;
import br.com.gticket.util.SessionUtil;

public class AgendaBO extends BO implements ValidaFormulario {

	private AgendaDAO dao;

	public AgendaBO() {

		dao = new AgendaDAO();

	}

	public void salvar(Agenda agenda) throws ValorEmBrancoException,
			ValorZeradoException, ValorInvalidoException, EmailException {

		validaCamposObrigatorios(agenda);

		if (inclusao(agenda)) {
			agenda.setDiaTodo(false);
			agenda.setFinalizada(false);

		}

		dao.salvar(agenda);

		if (inclusao(agenda)) {
			try {
				EmailUtil.enviarEmailAgenda(agenda);
			} catch (EmailException e) {
				throw new EmailException(
						"Foi criada agenda mas não foi possível enviar e-mail!");
			}
		}

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Agenda agenda = (Agenda) object;

		if (campoVazio(agenda.getTitulo())) {
			throw new ValorEmBrancoException("Campo título é obrigatório");
		}

		if (campoVazio(agenda.getDescricao())) {
			throw new ValorEmBrancoException("Campo descrição é obrigatório");
		}

		if (campoVazio(agenda.getDataInicio())) {
			throw new ValorEmBrancoException("Campo data inicio é obrigatório");
		}

		if (campoVazio(agenda.getDataFim())) {
			throw new ValorEmBrancoException("Campo data fim é obrigatório");
		}

		if (agenda.getDataInicio().getTime() == agenda.getDataFim().getTime()) {
			throw new ValorInvalidoException(
					"Campos data inicio e fim não podem ser iguais");
		}

		if (agenda.getDataInicio().getTime() > agenda.getDataFim().getTime()) {
			throw new ValorInvalidoException(
					"Campos data inicio não pode ser maior que data fim");
		}

	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean inclusao(Object objeto) {

		Agenda agenda = (Agenda) objeto;

		return agenda.getId() == null || agenda.getId() == 0;
	}

	public List<Agenda> listar() {
		return dao.listar();
	}

	public Agenda buscarPorId(int id) {

		return dao.buscarPorId(id);
	}

	public void excluir(Integer id) {

		dao.excluir(id);

	}

	public List<Agenda> listarMinhasAgendas() throws ValorInvalidoException {

		Usuario usuario;

		usuario = (Usuario) SessionUtil.getParam("usuarioLogado");

		if (usuario == null) {
			throw new ValorInvalidoException(
					"Houve um problema ao carregas as agendas do usuário logado!");
		}

		return dao.listarMinhasAgendas(usuario);
	}

	public void finalizar(Agenda agenda) throws ValorEmBrancoException {

		if (campoVazio(agenda.getResultado())) {
			throw new ValorEmBrancoException("Campo resultado é obrigatório");

		}

		agenda.setFinalizada(true);
		agenda.setDataBaixa(new Date());

		dao.salvar(agenda);

	}
}

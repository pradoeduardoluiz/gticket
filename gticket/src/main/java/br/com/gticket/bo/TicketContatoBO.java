package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.TicketContatoDAO;
import br.com.gticket.model.TicketContato;

public class TicketContatoBO extends BO implements ValidaFormulario {

	private TicketContatoDAO dao;

	public TicketContatoBO() {
		dao = new TicketContatoDAO();
	}

	public void salvar(TicketContato ticket) throws ValorEmBrancoException,
			ValorZeradoException, ValorInvalidoException {

		validaCamposObrigatorios(ticket);

		dao.salvar(ticket);

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		TicketContato ticket = (TicketContato) object;

		if (campoVazio(ticket.getDataDeInclusao())) {
			throw new ValorEmBrancoException(
					"Campo data de inclusão é obrigatório");
		}

		if (campoVazio(ticket.getCliente())) {
			throw new ValorEmBrancoException("Campo cliente é obrigatório");
		}

		if (campoVazio(ticket.getContato())) {
			throw new ValorEmBrancoException("Campo contato é obrigatório");
		}

		if (campoVazio(ticket.getSituacao())) {
			throw new ValorEmBrancoException("Campo situação é obrigatório");
		}

		if (campoVazio(ticket.getCategoria())) {
			throw new ValorEmBrancoException("Campo categoria é obrigatório");
		}

		if (campoVazio(ticket.getAssunto())) {
			throw new ValorEmBrancoException("Campo assunto é obrigatório");
		}

	}

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

	}

	@Override
	public boolean inclusao(Object object) {
		return false;
	}

	public TicketContato buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Integer id) {
		dao.excluir(id);
	}

	public List<TicketContato> listar() {
		return dao.listar();
	}

}

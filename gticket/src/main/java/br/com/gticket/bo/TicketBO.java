package br.com.gticket.bo;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Ticket;

public class TicketBO extends BO implements ValidaFormulario {

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Ticket ticket = (Ticket) object;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean inclusao(Object object) {
		Ticket ticket = (Ticket) object;
		return ticket.getId() == null || ticket.getId() == 0;

	}

}

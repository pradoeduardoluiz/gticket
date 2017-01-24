package br.com.gticket.bo;

import java.util.Date;
import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.TicketContatoDAO;
import br.com.gticket.model.TicketContato;

public class TicketContatoBO extends TicketBO {

	private TicketContatoDAO dao;

	public TicketContatoBO() {
		dao = new TicketContatoDAO();
	}

	public TicketContato salvar(TicketContato ticket)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		validaCamposObrigatorios(ticket);

		if (inclusao(ticket)) {
			ticket.setAtivo(true);
			ticket.setDataDeFinalizacao(new Date());
		}
		return dao.salvar(ticket);

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

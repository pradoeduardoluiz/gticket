package br.com.gticket.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.gticket.model.TicketContato;

public class TicketContatoDAO extends DAO {

	public TicketContato salvar(TicketContato ticket) {

		TicketContato ticketRetorno;
		ticketRetorno = getEntityManager().merge(ticket);
		getEntityManager().flush();

		return ticketRetorno;
	}

	public TicketContato buscarPorId(Integer id) {
		return getEntityManager().find(TicketContato.class, id);
	}

	public void excluir(Integer id) {
		TicketContato ticket = getEntityManager().getReference(
				TicketContato.class, id);

		getEntityManager().remove(ticket);

	}

	public List<TicketContato> listar() {

		Query query = getEntityManager().createQuery("From ticket_contato");

		List<TicketContato> lista = query.getResultList();

		return lista;
	}
}

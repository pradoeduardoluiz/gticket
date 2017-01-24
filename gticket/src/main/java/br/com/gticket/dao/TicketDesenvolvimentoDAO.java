package br.com.gticket.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.gticket.model.StatusProgresso;
import br.com.gticket.model.StatusTicket;
import br.com.gticket.model.TicketDesenvolvimento;

public class TicketDesenvolvimentoDAO extends DAO {

	public TicketDesenvolvimento salvar(TicketDesenvolvimento ticket) {

		TicketDesenvolvimento ticketRetorno;
		ticketRetorno = getEntityManager().merge(ticket);
		getEntityManager().flush();

		return ticketRetorno;
	}

	public void excluir(Integer id) {

		TicketDesenvolvimento ticket = getEntityManager().getReference(
				TicketDesenvolvimento.class, id);

		getEntityManager().remove(ticket);

	}

	public List<TicketDesenvolvimento> listar(String filtro) {

		Query query = null;

		if (filtro.contains("pendentes")) {
			query = montaQueryStatusProcesso();
			query.setParameter("parametro", StatusProgresso.PENDENTE);
		} else if (filtro.contains("aprovados")) {
			query = montaQueryStatusProcesso();
			query.setParameter("parametro", StatusProgresso.DESENVOLVIMENTO);
		} else if (filtro.contains("testes")) {
			query = montaQueryStatusProcesso();
			query.setParameter("parametro", StatusProgresso.TESTES);
		} else {

			query = getEntityManager().createQuery(
					"From ticket_desenvolvimento");
		}

		List<TicketDesenvolvimento> lista = query.getResultList();

		return lista;

	}

	private Query montaQueryStatusProcesso() {
		return getEntityManager().createQuery(
				"From ticket_desenvolvimento where statusProcesso=:parametro");
	}

	public TicketDesenvolvimento buscarPorId(Integer id) {
		return getEntityManager().find(TicketDesenvolvimento.class, id);
	}

	public void pausarTodos() {

	}

}

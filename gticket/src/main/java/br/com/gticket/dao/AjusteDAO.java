package br.com.gticket.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.gticket.model.Ajuste;
import br.com.gticket.model.TicketDesenvolvimento;

public class AjusteDAO extends DAO {

	public void salvar(Ajuste ajuste) {

		getEntityManager().merge(ajuste);
		getEntityManager().flush();

	}

	public Ajuste buscarPorId(Integer id) {

		return getEntityManager().find(Ajuste.class, id);
	}

	public void excluir(Integer id) {

		Ajuste ajuste = buscarPorId(id);

		getEntityManager().remove(ajuste);

	}

	public List<Ajuste> listar(TicketDesenvolvimento ticket) {

		Query query = getEntityManager().createQuery(
				"From ajuste where ticket_id=:idTicket", Ajuste.class);

		query.setParameter("idTicket", ticket);

		return (List<Ajuste>) query.getResultList();

	}
}

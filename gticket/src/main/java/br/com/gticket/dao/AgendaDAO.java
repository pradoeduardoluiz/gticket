package br.com.gticket.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.gticket.model.Agenda;
import br.com.gticket.model.Ajuste;
import br.com.gticket.model.Categoria;
import br.com.gticket.model.Usuario;

public class AgendaDAO extends DAO {

	public List<Agenda> listar() {

		Query query = getEntityManager().createQuery("From agenda",
				Agenda.class);

		return (List<Agenda>) query.getResultList();
	}

	public void salvar(Agenda agenda) {

		getEntityManager().merge(agenda);

	}

	public Agenda buscarPorId(int id) {

		return getEntityManager().find(Agenda.class, id);

	}

	public void excluir(Integer id) {

		Agenda agenda = buscarPorId(id);

		if (agenda != null) {
			getEntityManager().remove(agenda);
		}

	}

	public List<Agenda> listarMinhasAgendas(Usuario usuario) {

		Query query = getEntityManager().createQuery(
				"From agenda where usuario_id=:idUsuario ORDER BY dataInicio",
				Agenda.class);

		query.setParameter("idUsuario", usuario);

		return (List<Agenda>) query.getResultList();

	}

}

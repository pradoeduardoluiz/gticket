package br.com.gticket.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Modulo;

public class ModuloDAO extends DAO {

	public void salvar(Modulo modulo) {

		getEntityManager().merge(modulo);

	}

	public boolean buscarPorNome(String nome, Integer id) {

		Query query = getEntityManager().createQuery(
				"From modulo where nome=:nome", Modulo.class);
		query.setParameter("nome", nome);

		Modulo modulo = null;

		try {
			modulo = (Modulo) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (modulo.getId() == id) {
			return false;

		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Modulo> listar() {

		Query query = getEntityManager().createQuery("From modulo",
				Modulo.class);

		return (List<Modulo>) query.getResultList();
	}

	public Modulo buscarPorId(Integer id) {

		return getEntityManager().find(Modulo.class, id);
	}

	public void excluir(Integer id) {
		Modulo modulo = getEntityManager().getReference(Modulo.class, id);
		getEntityManager().remove(modulo);

	}

}

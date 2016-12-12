package br.com.gticket.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Categoria;

public class CategoriaDAO extends DAO {

	public void salvar(Categoria categoria) {

		getEntityManager().merge(categoria);

	}

	public boolean jaExisteRegistroComValor(String valorDocampo, Integer id,
			String nomeDoCampo) {

		String strQuery = "From categoria where campo=:campo";

		strQuery = strQuery.replaceAll("campo", nomeDoCampo);

		Query query = getEntityManager().createQuery(strQuery, Categoria.class);

		query.setParameter(nomeDoCampo, valorDocampo);

		Categoria categoria = null;

		try {
			categoria = (Categoria) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (id != 0) {
			if (categoria.getId() == id) {
				return false;

			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listar() {

		Query query = getEntityManager().createQuery("From categoria",
				Categoria.class);

		return (List<Categoria>) query.getResultList();
	}

	public Categoria buscarPorId(Integer id) {

		return getEntityManager().find(Categoria.class, id);
	}

	public void excluir(Integer id) {
		Categoria categoria = getEntityManager().getReference(Categoria.class,
				id);
		getEntityManager().remove(categoria);

	}

}

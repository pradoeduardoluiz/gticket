package br.com.gticket.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Contato;
import br.com.gticket.model.Usuario;

public class ContatoDAO extends DAO {

	public void salvar(Contato contato) {
		getEntityManager().merge(contato);
	}

	public Contato buscarPorId(Integer id) {
		return getEntityManager().find(Contato.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Contato> listar() {

		Query query = getEntityManager().createQuery("From contato",
				Contato.class);

		return (List<Contato>) query.getResultList();

	}

	public void excluir(Integer id) {

		Contato contato = buscarPorId(id);
		getEntityManager().remove(contato);

	}

	public boolean jaExisteRegistroComValor(String valorDocampo, Integer id,
			String nomeDoCampo) {

		String strQuery = "From contato where campo=:campo";

		strQuery = strQuery.replaceAll("campo", nomeDoCampo);

		Query query = getEntityManager().createQuery(strQuery, Contato.class);

		query.setParameter(nomeDoCampo, valorDocampo);

		Contato contato = null;

		try {
			contato = (Contato) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (id != 0) {
			if (contato.getId() == id) {
				return false;
			}
		}
		return true;

	}

}

package br.com.gticket.dao;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Cliente;

public class ClienteDAO extends DAO {

	public void salvar(Cliente cliente) {
		getEntityManager().merge(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {

		Query query = getEntityManager().createQuery("From cliente",
				Cliente.class);

		return (List<Cliente>) query.getResultList();
	}

	public Cliente buscarPorId(Integer id) {

		return getEntityManager().find(Cliente.class, id);
	}

	public void excluir(Integer id) {
		Cliente cliente = getEntityManager().getReference(Cliente.class, id);
		getEntityManager().remove(cliente);

	}

	public boolean jaExisteRegistroComValor(String valorDocampo, Integer id,
			String nomeDoCampo) {

		String strQuery = "From cliente where campo=:campo";

		strQuery = strQuery.replaceAll("campo", nomeDoCampo);

		Query query = getEntityManager().createQuery(strQuery, Cliente.class);

		query.setParameter(nomeDoCampo, valorDocampo);

		Cliente cliente = null;

		try {
			cliente = (Cliente) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (id != 0) {
			if (cliente.getId() == id) {
				return false;
			}
		}

		return true;

	}

	public Cliente buscarPorNome(String razaoSocial) {

		Query query = getEntityManager().createQuery(
				"From cliente where razaoSocial=:razaoSocial", Cliente.class);

		query.setParameter("razaoSocial", razaoSocial);

		Cliente cliente = null;

		cliente = (Cliente) query.getSingleResult();

		try {
			cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (NoResultException e) {
			throw new NoResultException("Não encontrado cliente com este nome!");
		}
	}
}

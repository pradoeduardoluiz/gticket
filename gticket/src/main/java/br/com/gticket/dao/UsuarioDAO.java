package br.com.gticket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Perfil;
import br.com.gticket.model.Usuario;

public class UsuarioDAO extends DAO {

	public void salvar(Usuario usuario) {

		getEntityManager().merge(usuario);

	}

	public List listarUsuarios() {
		// TODO Auto-generated method stub

		Query query = getEntityManager().createQuery("From usuario",
				Usuario.class);

		return query.getResultList();
	}

	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return getEntityManager().find(Usuario.class, id);
	}

	public void excluir(Integer id) {
		Usuario usuario = getEntityManager().getReference(Usuario.class, id);
		getEntityManager().remove(usuario);
	}

	public boolean buscarPorNome(String nome) {
		// TODO Auto-generated method stub

		Query query = getEntityManager().createQuery(
				"From usuario where nome=:nome", Usuario.class);
		query.setParameter("nome", nome);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}
		return true;

	}

	public boolean buscarPorEmail(String email) {

		Query query = getEntityManager().createQuery(
				"From usuario where email=:email", Usuario.class);
		query.setParameter("email", email);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}
		return true;

	}

	public boolean buscarPorCpf(String cpf) {

		Query query = getEntityManager().createQuery(
				"From usuario where cpf=:cpf", Usuario.class);
		query.setParameter("cpf", cpf);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}
		return true;
	}
}

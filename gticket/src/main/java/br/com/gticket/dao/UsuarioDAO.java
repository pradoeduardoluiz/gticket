package br.com.gticket.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Usuario;

public class UsuarioDAO extends DAO {

	public void salvar(Usuario usuario) {

		getEntityManager().merge(usuario);

	}

	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub

		Query query = getEntityManager().createQuery("From usuario",
				Usuario.class);

		return (List<Usuario>) query.getResultList();
	}

	public Usuario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return getEntityManager().find(Usuario.class, id);
	}

	public void excluir(Integer id) {
		Usuario usuario = getEntityManager().getReference(Usuario.class, id);
		getEntityManager().remove(usuario);
	}

	public boolean buscarPorNome(String nome, Integer id) {
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

		if (usuario.getId() == id) {
			return false;
		}

		return true;

	}

	public boolean buscarPorEmail(String email) {
		// TODO Auto-generated method stub

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

	public boolean buscarPorEmail(String email, Integer id) {

		Query query = getEntityManager().createQuery(
				"From usuario where email=:email", Usuario.class);
		query.setParameter("email", email);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (usuario.getId() == id) {
			return false;
		}

		return true;

	}

	public boolean buscarPorCpf(String cpf, Integer id) {

		Query query = getEntityManager().createQuery(
				"From usuario where cpf=:cpf", Usuario.class);
		query.setParameter("cpf", cpf);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (usuario.getId() == id) {
			return false;
		}

		return true;
	}

	public Usuario validaLogin(String email, String senha) {
		// TODO Auto-generated method stub

		Query query = getEntityManager().createQuery(
				"From usuario where email=:email and senha=:senha",
				Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);

		try {

			return (Usuario) query.getSingleResult();

		} catch (NoResultException e) {

			return null;

		}
	}

}

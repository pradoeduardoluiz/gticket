package br.com.gticket.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.gticket.model.Perfil;
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

	public boolean jaExisteRegistroComValor(String valorDocampo, Integer id,
			String nomeDoCampo) {

		String strQuery = "From usuario where campo=:campo";

		strQuery = strQuery.replaceAll("campo", nomeDoCampo);

		Query query = getEntityManager().createQuery(strQuery, Usuario.class);

		query.setParameter(nomeDoCampo, valorDocampo);

		Usuario usuario = null;

		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {

			return false;
		}

		if (id == null) {
			return true;
		}

		if (id != 0 && id != null) {
			if (usuario.getId() == id) {
				return false;
			}
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

	public List<Usuario> listarDesenvolvedores() {

		Query query = getEntityManager().createQuery(
				"From usuario where perfil =:parametro", Usuario.class);
		query.setParameter("parametro", Perfil.DESENVOLVEDOR);

		return (List<Usuario>) query.getResultList();

	}

	public List<Usuario> listarTesters() {

		Query query = getEntityManager().createQuery(
				"From usuario where perfil =:parametro", Usuario.class);
		query.setParameter("parametro", Perfil.TESTER);

		return (List<Usuario>) query.getResultList();

	}

	public Usuario buscarPorEmail(String email) {

		Query query = getEntityManager().createQuery(
				"From usuario where email=:email", Usuario.class);

		query.setParameter("email", email);

		try {

			return (Usuario) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

}

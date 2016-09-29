package br.com.gticket.dao;

import java.util.List;

import javax.persistence.Query;

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

}

package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.dao.UsuarioDAO;
import br.com.gticket.model.Usuario;

public class UsuarioBo {

	private UsuarioDAO dao;

	public UsuarioBo() {

		dao = new UsuarioDAO();
	}

	public void salvar(Usuario usuario) throws Exception {

		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {

			throw new Exception("Nome do usuário inválido");
		}

		dao.salvar(usuario);

	}

	public List listarUsuarios() {

		return dao.listarUsuarios();
	}

	public Usuario buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluirUsuario(Integer id) {

		dao.excluir(id);

	}

}

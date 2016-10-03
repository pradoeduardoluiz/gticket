package br.com.gticket.bo;

import java.util.Date;
import java.util.List;

import br.com.gticket.dao.UsuarioDAO;
import br.com.gticket.model.Usuario;
import br.com.gticket.model.exception.ValorEmBrancoException;
import br.com.gticket.model.exception.ValorInvalidoException;
import br.com.gticket.model.exception.ValorZeradoException;
import br.com.gticket.util.CriptografaSenha;

public class UsuarioBo {

	private UsuarioDAO dao;

	public UsuarioBo() {

		dao = new UsuarioDAO();
	}

	public void salvar(Usuario usuario) throws ValorEmBrancoException,
			ValorInvalidoException, ValorZeradoException {

		validaCamposObrigatorios(usuario);

		if (inclusao(usuario)) {

			if (dao.buscarPorNome(usuario.getNome())) {
				throw new ValorInvalidoException(
						"Já existe usuário com este nome cadastrado!");
			}

			if (dao.buscarPorCpf(usuario.getCpf())) {
				throw new ValorInvalidoException(
						"Já existe usuário com este CPF cadastrado!");
			}

			if (dao.buscarPorEmail(usuario.getEmail())) {
				throw new ValorInvalidoException(
						"Já existe usuário com este e-email cadastrado!");
			}

			if (!usuario.getConfirmacaoDeSenha().equals(usuario.getSenha())) {
				throw new ValorInvalidoException(
						"Senha e Confirmação se senha devem ser iguais!");
			}

			usuario.setSenha(CriptografaSenha.encryptPassword(usuario
					.getSenha()));

		}

		dao.salvar(usuario);

	}

	private void validaCamposObrigatorios(Usuario usuario)
			throws ValorEmBrancoException, ValorZeradoException {

		if (campoVazio(usuario.getNome())) {
			throw new ValorEmBrancoException("Campo Nome é Obrigatório!");
		}

		if (campoVazio(usuario.getCpf())) {
			throw new ValorEmBrancoException("Campo CPF é Obrigatório!");
		}

		if (campoVazio(usuario.getRg())) {
			throw new ValorEmBrancoException("Campo RG é Obrigatório!");
		}

		if (campoVazio(usuario.getDataNascimento())) {
			throw new ValorZeradoException(
					"Campo Data de Nascimento é Obrigatório!");
		}

		if (campoVazio(usuario.getEmail())) {
			throw new ValorEmBrancoException("Campo Email é Obrigatório!");
		}

		if (inclusao(usuario)) {
			if (campoVazio(usuario.getSenha())) {
				throw new ValorEmBrancoException("Campo Senha é Obrigatório!");
			}

			if (campoVazio(usuario.getConfirmacaoDeSenha())) {
				throw new ValorEmBrancoException(
						"Campo Confirmação de Senha é Obrigatório!");
			}
		}

	}

	private boolean campoVazio(Date data) {

		if (data == null) {
			return true;
		}
		return false;
	}

	private boolean campoVazio(String campo) {
		if (campo == null || campo.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean inclusao(Usuario usuario) {
		return usuario.getId() == null || usuario.getId() == 0;
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

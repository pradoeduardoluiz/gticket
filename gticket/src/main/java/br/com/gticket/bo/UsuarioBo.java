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
						"J� existe usu�rio com este nome cadastrado!");
			}

			if (dao.buscarPorCpf(usuario.getCpf())) {
				throw new ValorInvalidoException(
						"J� existe usu�rio com este CPF cadastrado!");
			}

			if (dao.buscarPorEmail(usuario.getEmail())) {
				throw new ValorInvalidoException(
						"J� existe usu�rio com este e-email cadastrado!");
			}

			if (!usuario.getConfirmacaoDeSenha().equals(usuario.getSenha())) {
				throw new ValorInvalidoException(
						"Senha e Confirma��o se senha devem ser iguais!");
			}

			usuario.setSenha(CriptografaSenha.encryptPassword(usuario
					.getSenha()));

		}

		dao.salvar(usuario);

	}

	private void validaCamposObrigatorios(Usuario usuario)
			throws ValorEmBrancoException, ValorZeradoException {

		if (campoVazio(usuario.getNome())) {
			throw new ValorEmBrancoException("Campo Nome � Obrigat�rio!");
		}

		if (campoVazio(usuario.getCpf())) {
			throw new ValorEmBrancoException("Campo CPF � Obrigat�rio!");
		}

		if (campoVazio(usuario.getRg())) {
			throw new ValorEmBrancoException("Campo RG � Obrigat�rio!");
		}

		if (campoVazio(usuario.getDataNascimento())) {
			throw new ValorZeradoException(
					"Campo Data de Nascimento � Obrigat�rio!");
		}

		if (campoVazio(usuario.getEmail())) {
			throw new ValorEmBrancoException("Campo Email � Obrigat�rio!");
		}

		if (inclusao(usuario)) {
			if (campoVazio(usuario.getSenha())) {
				throw new ValorEmBrancoException("Campo Senha � Obrigat�rio!");
			}

			if (campoVazio(usuario.getConfirmacaoDeSenha())) {
				throw new ValorEmBrancoException(
						"Campo Confirma��o de Senha � Obrigat�rio!");
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

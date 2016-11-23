package br.com.gticket.bo;

import java.util.List;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.UsuarioDAO;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.CriptografaSenha;

public class UsuarioBO extends BO implements ValidaFormulario {

	private UsuarioDAO dao;

	public UsuarioBO() {

		dao = new UsuarioDAO();
	}

	public void salvar(Usuario usuario) throws ValorEmBrancoException,
			ValorInvalidoException, ValorZeradoException {

		validaCamposObrigatorios(usuario);
		validaCamposUnicos(usuario);

		if (inclusao(usuario)) {

			validaCamposNaInclusao(usuario);

			usuario.setSenha(CriptografaSenha.encryptPassword(usuario
					.getSenha()));

		}

		dao.salvar(usuario);

	}

	public List<Usuario> listarUsuarios() {
		return dao.listarUsuarios();
	}

	public Usuario buscarPorId(Integer id) {
		return dao.buscarPorId(id);
	}

	public void excluirUsuario(Integer id) {

		dao.excluir(id);

	}

	public Usuario login(String email, String senha)
			throws ValorInvalidoException {
		// TODO Auto-generated method stub

		Usuario usuarioLogado;

		if (!dao.jaExisteRegistroComValor(email, 0, "email")) {
			throw new ValorInvalidoException("Email não cadastrado!");
		}

		usuarioLogado = dao.validaLogin(email,
				CriptografaSenha.encryptPassword(senha));

		if (usuarioLogado == null) {

			throw new ValorInvalidoException("Email ou senha inválidos!");
		}

		return usuarioLogado;

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException {

		Usuario usuario = (Usuario) object;

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

		if (!cpfValido(usuario.getCpf())) {
			throw new ValorEmBrancoException("Campo CPF é inválido!");
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

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorInvalidoException, ValorEmBrancoException {

		Usuario usuario = (Usuario) object;

		if (!usuario.getConfirmacaoDeSenha().equals(usuario.getSenha())) {
			throw new ValorInvalidoException(
					"Senha e Confirmação se senha devem ser iguais!");
		}

	}

	@Override
	public boolean inclusao(Object object) {

		Usuario usuario = (Usuario) object;

		return usuario.getId() == null || usuario.getId() == 0;
	}

	@Override
	public void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException {

		Usuario usuario = (Usuario) object;

		if (dao.jaExisteRegistroComValor(usuario.getNome(), usuario.getId(),
				"nome")) {
			throw new ValorInvalidoException(
					"Já existe usuário com este nome cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(usuario.getCpf(), usuario.getId(),
				"cpf")) {
			throw new ValorInvalidoException(
					"Já existe usuário com este CPF cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(usuario.getEmail(), usuario.getId(),
				"email")) {
			throw new ValorInvalidoException(
					"Já existe usuário com este e-email cadastrado!");
		}

	}
}

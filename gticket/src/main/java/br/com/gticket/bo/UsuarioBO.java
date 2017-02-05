package br.com.gticket.bo;

import java.security.CryptoPrimitive;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.dao.UsuarioDAO;
import br.com.gticket.model.Usuario;
import br.com.gticket.util.CriptografaSenha;
import br.com.gticket.util.EmailUtil;
import br.com.gticket.util.Util;

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
			throw new ValorInvalidoException("Email n�o cadastrado!");
		}

		usuarioLogado = dao.validaLogin(email,
				CriptografaSenha.encryptPassword(senha));

		if (usuarioLogado == null) {

			throw new ValorInvalidoException("Email ou senha inv�lidos!");
		}

		return usuarioLogado;

	}

	@Override
	public void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException {

		Usuario usuario = (Usuario) object;

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

		if (!cpfValido(usuario.getCpf())) {
			throw new ValorEmBrancoException("Campo CPF � inv�lido!");
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

	@Override
	public void validaCamposNaInclusao(Object object)
			throws ValorInvalidoException, ValorEmBrancoException {

		Usuario usuario = (Usuario) object;

		if (!usuario.getConfirmacaoDeSenha().equals(usuario.getSenha())) {
			throw new ValorInvalidoException(
					"Senha e Confirma��o se senha devem ser iguais!");
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
					"J� existe usu�rio com este nome cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(usuario.getCpf(), usuario.getId(),
				"cpf")) {
			throw new ValorInvalidoException(
					"J� existe usu�rio com este CPF cadastrado!");
		}

		if (dao.jaExisteRegistroComValor(usuario.getEmail(), usuario.getId(),
				"email")) {
			throw new ValorInvalidoException(
					"J� existe usu�rio com este e-email cadastrado!");
		}

	}

	public List<Usuario> listarDesenvolvedores() {
		return dao.listarDesenvolvedores();
	}

	public List<Usuario> listarTesters() {
		return dao.listarTesters();
	}

	public void gerarNovaSenha(String email, String cpf)
			throws ValorInvalidoException, EmailException {

		Usuario usuario = null;

		usuario = dao.buscarPorEmail(email);

		if (usuario == null) {
			throw new ValorInvalidoException(
					"N�o foi encontrado usu�rio com este e-mail!");
		} else {
			if (!usuario.getCpf().equals(cpf)) {
				throw new ValorInvalidoException(
						"Cpf n�o correspondente com o cadastrado!");
			}
		}

		String novaSenha = Util.getRandomPassword(8);

		usuario.setSenha(CriptografaSenha.encryptPassword(novaSenha));

		try {
			EmailUtil.enviarNovaSenha(usuario, novaSenha);
		} catch (EmailException e) {

			throw new EmailException(
					"N�o foi poss�vel gerar nova senha, tente novamente mais tarde!");
		}

		dao.salvar(usuario);

	}

	public void alterarSenha(Usuario usuario, String senhaAtual,
			String novaSenha, String confirmacaoSenha)
			throws ValorInvalidoException {

		if (!usuario.getSenha().equals(
				CriptografaSenha.encryptPassword(senhaAtual))) {

			throw new ValorInvalidoException(
					"Senha Atual n�o confere com senha cadastradal!");

		}

		if (usuario.getSenha().equals(
				CriptografaSenha.encryptPassword(novaSenha))) {

			throw new ValorInvalidoException(
					"Nova Senha n�o pode ser igual a anterior!");
		}

		if (senhaAtual.isEmpty()) {
			throw new ValorInvalidoException("Campo Nova Senha � Obrigat�rio!!");
		}

		if (senhaAtual.equals(confirmacaoSenha)) {

			throw new ValorInvalidoException(
					"Campo Nova Senha n�o confere com a confirma��o de senha!");
		}

		usuario.setSenha(CriptografaSenha.encryptPassword(novaSenha));

		dao.salvar(usuario);

	}
}

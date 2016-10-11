package br.com.gticket.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.gticket.bo.UsuarioBo;
import br.com.gticket.model.Usuario;
import br.com.gticket.model.exception.ValorInvalidoException;
import br.com.gticket.util.FacesUtil;
import br.com.gticket.util.SessionUtil;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	private boolean erro;

	private Usuario usuarioLogado;
	private UsuarioBo bo;

	@PostConstruct
	public void init() {
		erro = false;
		email = "";
		senha = "";
		usuarioLogado = new Usuario();
		bo = new UsuarioBo();

	}

	public String login() {

		try {
			usuarioLogado = bo.login(email, senha);
			SessionUtil.setParam("usuarioLogado", usuarioLogado);
			return "/logged/home";

		} catch (ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());

			e.printStackTrace();
		}

		return null;
	}

	public void erroLogin() {

		if (erro) {
			erro = false;
			FacesUtil.addErrorMessage("É necessário logar no sistema!");
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

}

package br.com.gticket.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.UsuarioBo;
import br.com.gticket.model.Usuario;

@ManagedBean
public class UsuarioMB {

	private Usuario usuario;
	private UsuarioBo bo;
	private Integer editarId;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		bo = new UsuarioBo();
	}

	public void limpar() {
	}

	public String salvar() {

		try {
			bo.salvar(usuario);
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, " ", e
							.getMessage()));

			e.printStackTrace();
		}

		usuarios = null;
		return "/lista_usuarios";

	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		usuario = bo.buscarPorId(editarId);
	}

	public String excluir(Integer id) {
		bo.excluirUsuario(id);

		return "/lista_usuarios";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {

		if (usuarios == null) {

			usuarios = bo.listarUsuarios();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioBo getBo() {
		return bo;
	}

	public void setBo(UsuarioBo bo) {
		this.bo = bo;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

}

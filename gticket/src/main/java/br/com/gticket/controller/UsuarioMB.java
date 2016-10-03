package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.UsuarioBo;
import br.com.gticket.model.Perfil;
import br.com.gticket.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private UsuarioBo bo;
	private Integer editarId;
	private List<Usuario> usuarios;
	private Perfil[] perfis;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		bo = new UsuarioBo();
	}

	public void limpar() {
		System.out.println(usuario.getId());
	}

	public void salvar() {

		try {
			bo.salvar(usuario);

			usuario = null;

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, " ",
							"Cadastro Salvo com Sucesso!"));

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, " ", e
							.getMessage()));

			e.printStackTrace();
		}

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

	public String editar(Integer id) {
		usuario = bo.buscarPorId(id);
		return "/form_usuario";
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

	public Perfil[] getPerfis() {
		perfis = Perfil.values();
		return perfis;
	}

	public void setPerfis(Perfil[] perfis) {
		this.perfis = perfis;
	}

}
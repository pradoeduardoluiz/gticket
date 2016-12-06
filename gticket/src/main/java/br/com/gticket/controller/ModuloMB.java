package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.ModuloBO;
import br.com.gticket.model.Modulo;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ModuloMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Modulo modulo;
	private ModuloBO bo;
	private Integer editarId;
	private List<Modulo> modulos;

	@PostConstruct
	public void init() {
		modulo = new Modulo();
		bo = new ModuloBO();
	}

	public void salvar() {

		try {
			bo.salvar(modulo);

			modulo = new Modulo();

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (Exception e) {
			// TODO: handle exception

			FacesUtil.addErrorMessage(e.getMessage());

			e.printStackTrace();
		}

	}

	public String excluir(Integer id) {
		bo.excluir(id);

		return "/lista_usuarios";
	}

	public void carregarModulo(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		modulo = bo.buscarPorId(editarId);
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

	public List<Modulo> getModulos() {
		return bo.listar();
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

}

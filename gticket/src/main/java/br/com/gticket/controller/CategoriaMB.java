package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.CategoriaBO;
import br.com.gticket.model.Categoria;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;
	private CategoriaBO bo;
	private Integer editarId;
	private List<Categoria> categorias;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		bo = new CategoriaBO();
	}

	public String salvar() {

		try {
			bo.salvar(categoria);

			categoria = new Categoria();

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

			return "lista_categorias?faces-redirect=true";

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

		return "";

	}

	public String excluir(Integer id) {
		bo.excluir(id);

		return "lista_categorias?faces-redirect=true";
	}

	public void carregar(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		categoria = bo.buscarPorId(editarId);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

	public List<Categoria> getCategorias() {
		return bo.listar();
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}

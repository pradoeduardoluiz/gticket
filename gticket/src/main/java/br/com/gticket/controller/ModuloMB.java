package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gticket.bo.ModuloBO;
import br.com.gticket.model.Modulo;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ModuloMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Modulo modulo;
	private ModuloBO bo;
	private Integer editaId;
	private List<Modulo> modulos;

	@PostConstruct
	public void init() {
		modulo = new Modulo();
		bo = new ModuloBO();
	}

	public void salvar() {

		try {
			bo.salvar(modulo);

			modulo = null;

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (Exception e) {
			// TODO: handle exception

			FacesUtil.addErrorMessage(e.getMessage());

			e.printStackTrace();
		}

	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public Integer getEditaId() {
		return editaId;
	}

	public void setEditaId(Integer editaId) {
		this.editaId = editaId;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

}

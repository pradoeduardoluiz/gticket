package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.ClienteBO;
import br.com.gticket.model.Cliente;
import br.com.gticket.model.Estado;
import br.com.gticket.model.Perfil;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private ClienteBO bo;
	private Integer editarId;
	private List<Cliente> clientes;
	private Estado[] estados;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		bo = new ClienteBO();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void salvar() {

		try {
			bo.salvar(cliente);
			cliente = null;

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

	}

	public void carregarCliente(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		cliente = bo.buscarPorId(editarId);
	}

	public Estado[] getEstados() {
		return estados = Estado.values();
	}

	public void setEstados(Estado[] estados) {
		this.estados = estados;
	}

}

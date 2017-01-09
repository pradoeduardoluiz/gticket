package br.com.gticket.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.ClienteBO;
import br.com.gticket.model.Cliente;
import br.com.gticket.model.Estado;
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
	private String[] buscaCliente;

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
		return bo.listar();
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String salvar() {

		try {

			bo.salvar(cliente);
			cliente = new Cliente();

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

			return "lista_clientes?faces-redirect=true";

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());
			e.printStackTrace();
		}

		return "";

	}

	public String excluir(Integer id) {

		bo.excluir(id);

		return "lista_clientes?faces-redirect=true";

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

	public String[] getBuscaCliente() {

		clientes = bo.listar();

		buscaCliente = new String[clientes.size()];
		int i = 0;

		for (Cliente cliente : clientes) {

			if (!(cliente.getRazaoSocial() != null)
					|| !(cliente.getRazaoSocial().equals(""))) {

				buscaCliente[i] = cliente.getRazaoSocial();
				i++;

			}

		}

		return buscaCliente;

	}
}

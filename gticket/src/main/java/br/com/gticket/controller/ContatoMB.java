package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.ClienteBO;
import br.com.gticket.bo.ContatoBO;
import br.com.gticket.model.Cliente;
import br.com.gticket.model.Contato;
import br.com.gticket.model.Departamento;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ContatoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Contato contato;
	private Cliente cliente;
	private String razaoSocialCliente;
	private ContatoBO bo;
	private ClienteBO boCliente;
	private List<Contato> contatos;
	private List<Cliente> clientes;
	private Integer editarId;
	private Departamento[] departamentos;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		contato = new Contato();
		bo = new ContatoBO();
		boCliente = new ClienteBO();
	}

	public void salvar() {

		try {

			if (contato.getId() == null || contato.getId() == 0) {
				cliente = boCliente.buscarPorNome(razaoSocialCliente);
				contato.setCliente(cliente);
			}

			bo.salvar(contato);

			if (contato.getId() == null || contato.getId() == 0) {
				cliente.addContato(contato);
				boCliente.salvar(cliente);
			}

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

	}

	public void carregar(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}
		contato = bo.buscarPorId(editarId);
		razaoSocialCliente = contato.getCliente().getRazaoSocial();
	}

	public String excluir(Integer id) {

		bo.excluir(id);

		return "/lista_contatos";

	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return bo.listar();
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Cliente> getClientes() {
		return boCliente.listar();
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editaId) {
		this.editarId = editaId;
	}

	public Departamento[] getDepartamentos() {

		departamentos = Departamento.values();

		return departamentos;
	}

	public void setDepartamentos(Departamento[] departamentos) {
		this.departamentos = departamentos;
	}

	public String getRazaoSocialCliente() {
		return razaoSocialCliente;
	}

	public void setRazaoSocialCliente(String razaoSocialCliente) {
		this.razaoSocialCliente = razaoSocialCliente;
	}

}

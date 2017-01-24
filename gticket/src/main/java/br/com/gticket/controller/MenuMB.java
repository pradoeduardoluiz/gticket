package br.com.gticket.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class MenuMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean menuAdministracaoCollapsed = true;
	private Boolean menuClientesCollapsed = true;
	private Boolean menuTicketCollapsed = true;
	private Boolean menuTicketContatoCollapsed = true;
	private Boolean menuTicketDesenvCollapsed = true;

	private final String facesRedirect = "?faces-redirect=true";

	// Opções do menu Administração
	private final String LISTAUSUARIOS = "lista_usuarios";
	private final String LISTAMODULOS = "lista_modulos";
	private final String LISTACATEGORIAS = "lista_categorias";

	// Opções do menu Clientes e relacionados
	private final String LISTACLIENTES = "lista_clientes";
	private final String LISTACONTATOS = "lista_contatos";

	// Opções do menu Ticket
	private final String LISTATICKETSCONTATOS = "lista_tickets_contato";

	private final String LISTATICKETSDESENV = "lista_tickets_desenv";

	public void menuAdmnistracaoAberto() {

		menuAdministracaoCollapsed = false;
		menuClientesCollapsed = true;
		menuTicketCollapsed = true;
	}

	public void menuClientesAberto() {
		menuClientesCollapsed = false;
		menuAdministracaoCollapsed = true;
		menuTicketCollapsed = false;

	}

	public void menuTicketAberto() {
		menuTicketCollapsed = false;
		menuClientesCollapsed = true;
		menuAdministracaoCollapsed = true;

	}

	public void menuTicketContatoAberto() {
		menuTicketContatoCollapsed = false;
		menuTicketDesenvCollapsed = true;

	}

	public void menuTicketDesenvAberto() {
		menuTicketDesenvCollapsed = false;
		menuTicketContatoCollapsed = true;

	}

	public Boolean getMenuAdministracaoCollapsed() {
		return menuAdministracaoCollapsed;
	}

	public void setMenuAdministracaoCollapsed(Boolean menuAdministracaoCollapsed) {
		this.menuAdministracaoCollapsed = menuAdministracaoCollapsed;
	}

	public Boolean getMenuClientesCollapsed() {
		return menuClientesCollapsed;
	}

	public void setMenuClientesCollapsed(Boolean menuClientesCollapsed) {
		this.menuClientesCollapsed = menuClientesCollapsed;
	}

	public Boolean getMenuTicketCollapsed() {
		return menuTicketCollapsed;
	}

	public void setMenuTicketCollapsed(Boolean menuTicketCollapsed) {
		this.menuTicketCollapsed = menuTicketCollapsed;
	}

	public Boolean getMenuTicketContatoCollapsed() {
		return menuTicketContatoCollapsed;
	}

	public void setMenuTicketContatoCollapsed(Boolean menuTicketContatoCollapsed) {
		this.menuTicketContatoCollapsed = menuTicketContatoCollapsed;
	}

	public Boolean getMenuTicketDesenvCollapsed() {
		return menuTicketDesenvCollapsed;
	}

	public void setMenuTicketDesenvCollapsed(Boolean menuTicketDesenvCollapsed) {
		this.menuTicketDesenvCollapsed = menuTicketDesenvCollapsed;
	}

	public String listaUsuarios() {
		menuAdmnistracaoAberto();
		return adicionaFacesRedirect(LISTAUSUARIOS);
	}

	public String listaModulos() {
		menuAdmnistracaoAberto();
		return adicionaFacesRedirect(LISTAMODULOS);
	}

	public String listaCategorias() {
		menuAdmnistracaoAberto();
		return adicionaFacesRedirect(LISTACATEGORIAS);
	}

	public String listaClientes() {
		menuClientesAberto();
		return adicionaFacesRedirect(LISTACLIENTES);
	}

	public String listaContatos() {
		menuClientesAberto();
		return adicionaFacesRedirect(LISTACONTATOS);
	}

	public String listaTicketsContatos() {
		menuTicketAberto();
		menuTicketContatoAberto();
		return adicionaFacesRedirect(LISTATICKETSCONTATOS);
	}

	public String listaTicketsDesenv(String filtro) {
		menuTicketAberto();
		menuTicketDesenvAberto();

		if (filtro == null) {
			filtro = "";
		}

		if (!filtro.isEmpty()) {
			return adicionaFacesRedirect(LISTATICKETSDESENV + "?" + "filtro="
					+ filtro);
		} else {
			return adicionaFacesRedirect(LISTATICKETSDESENV);
		}
	}

	private String adicionaFacesRedirect(String link) {
		return link + facesRedirect;
	}
}

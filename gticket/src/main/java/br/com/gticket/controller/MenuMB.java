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

	private final String facesRedirect = "?faces-redirect=true";

	// Opções do menu Administração
	private final String LISTAUSUARIOS = "lista_usuarios" + facesRedirect;
	private final String LISTAMODULOS = "lista_modulos" + facesRedirect;
	private final String LISTACATEGORIAS = "lista_categorias" + facesRedirect;

	// Opções do menu Clientes e relacionados
	private final String LISTACLIENTES = "lista_clientes" + facesRedirect;
	private final String LISTACONTATOS = "lista_contatos" + facesRedirect;

	// Opções do menu Ticket
	private final String LISTATICKETS = "lista_tickets_contato" + facesRedirect;

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

	public String listaUsuarios() {
		menuAdmnistracaoAberto();
		return LISTAUSUARIOS;
	}

	public String listaModulos() {
		menuAdmnistracaoAberto();
		return LISTAMODULOS;
	}

	public String listaCategorias() {
		menuAdmnistracaoAberto();
		return LISTACATEGORIAS;
	}

	public String listaClientes() {
		menuClientesAberto();
		return LISTACLIENTES;
	}

	public String listaContatos() {
		menuClientesAberto();
		return LISTACONTATOS;
	}

	public String listaTickets() {
		menuTicketAberto();
		return LISTATICKETS;
	}
}

package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.gticket.bo.TicketContatoBO;
import br.com.gticket.model.TicketContato;

@ManagedBean
@ViewScoped
public class TicketContatoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private TicketContato ticket;
	private TicketContatoBO bo;
	private List<TicketContato> tickets;
	private Integer editarId;

	public TicketContato getTicket() {
		return ticket;
	}

	public void setTicket(TicketContato ticket) {
		this.ticket = ticket;
	}

	public List<TicketContato> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketContato> tickets) {
		this.tickets = tickets;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

}

package br.com.gticket.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.gticket.bo.TicketContatoBO;
import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Contato;
import br.com.gticket.model.SituacaoTicket;
import br.com.gticket.model.TicketContato;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TicketContatoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private TicketContato ticket;
	private TicketContatoBO bo;
	private List<TicketContato> tickets;
	private Integer editarId;
	private List<Contato> contatos;
	private SituacaoTicket[] situacoes;

	@PostConstruct
	public void init() {

		ticket = new TicketContato();
		ticket.setTicketFinalizado(false);
		ticket.setDataDeInclusao(new Date());
		bo = new TicketContatoBO();
		contatos = new ArrayList<Contato>();

	}

	public void salvar() {

		try {
			bo.salvar(ticket);

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void excluir(Integer id) {

		bo.excluir(id);

	}

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

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public SituacaoTicket[] getSituacoes() {
		situacoes = SituacaoTicket.values();
		return situacoes;
	}

	public void setSituacoes(SituacaoTicket[] situacoes) {
		this.situacoes = situacoes;
	}

	public void carregarContatos(AjaxBehaviorEvent event) {

		if (ticket.getCliente() != null) {
			contatos = ticket.getCliente().getContatos();
		}
	}

}

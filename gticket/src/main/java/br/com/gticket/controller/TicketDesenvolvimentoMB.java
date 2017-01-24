package br.com.gticket.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.TicketContatoBO;
import br.com.gticket.bo.TicketDesenvolvimentoBO;
import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Contato;
import br.com.gticket.model.SituacaoTicket;
import br.com.gticket.model.StatusTicket;
import br.com.gticket.model.TicketContato;
import br.com.gticket.model.TicketDesenvolvimento;
import br.com.gticket.util.FacesUtil;
import br.com.gticket.util.Util;

@ManagedBean
@ViewScoped
public class TicketDesenvolvimentoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private TicketDesenvolvimento ticket;
	private TicketDesenvolvimento ticketSelecionado;
	private TicketDesenvolvimentoBO bo;
	private List<TicketDesenvolvimento> tickets;
	private Integer editarId;
	private List<Contato> contatos;
	private SituacaoTicket[] situacoes;
	private StatusTicket[] status;
	private String filtro = "";

	@PostConstruct
	public void init() {

		ticket = new TicketDesenvolvimento();
		ticket.setDataDeInclusao(new Date());
		bo = new TicketDesenvolvimentoBO();
		contatos = new ArrayList<Contato>();

	}

	public String salvar() {

		try {
			TicketDesenvolvimento retorno;

			retorno = bo.salvar(ticket);

			String msg = "";

			if (ticket.getId() == null || ticket.getId() == 0) {
				msg = "Ticket #" + retorno.getId() + " incluído com sucesso.";

			} else {
				msg = "Ticket #" + retorno.getId() + " alterado com sucesso.";
			}

			FacesUtil.addInfoMessage(msg);

			return "lista_tickets_desenv";

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

		return "";

	}

	public String excluir(Integer id) {
		bo.excluir(id);
		return "lista_tickets_desenv";
	}

	public String aprovar() {

		try {
			bo.aprovar(ticket);

			String msg = "";

			if (ticket.getStatusTicket() == StatusTicket.APROVADO) {
				msg = "Ticket #" + ticket.getId() + " aprovado com sucesso.";

			} else if (ticket.getStatusTicket() == StatusTicket.REPROVADO) {
				msg = "Ticket #" + ticket.getId() + " reprovado com sucesso.";
			}

			FacesUtil.addInfoMessage(msg);

			return "lista_tickets_desenv";

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
		}

		return null;

	}

	public void play(TicketDesenvolvimento ticketAprovado) {
		try {
			bo.play(ticketAprovado);

			FacesUtil.addInfoMessage("Ticket #" + ticketAprovado.getId()
					+ " em andamento.");

		} catch (ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void pause(TicketDesenvolvimento ticketAprovado) {

		try {
			bo.pause(ticketAprovado);

			FacesUtil.addInfoMessage("Ticket #" + ticketAprovado.getId()
					+ " pausado.");

		} catch (ValorInvalidoException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void finalizar() {

		try {
			bo.finalizar(ticket);
		} catch (ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public TicketDesenvolvimento getTicket() {
		return ticket;
	}

	public void setTicket(TicketDesenvolvimento ticket) {
		this.ticket = ticket;
	}

	public TicketDesenvolvimento getTicketSelecionado() {
		return ticketSelecionado;
	}

	public void setTicketSelecionado(TicketDesenvolvimento ticketSelecionado) {
		this.ticketSelecionado = ticketSelecionado;
	}

	public List<TicketDesenvolvimento> getTickets() {
		return bo.listar(filtro);
	}

	public void setTickets(List<TicketDesenvolvimento> tickets) {
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

	public void carregarTicket(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		ticket = bo.buscarPorId(editarId);
	}

	public StatusTicket[] getStatus() {
		return status = StatusTicket.values();
	}

	public void setStatus(StatusTicket[] status) {
		this.status = status;
	}

	public String getFiltro() {

		if (filtro.contains("?")) {
			int caracter = filtro.indexOf("?");
			String string = filtro.substring(0, caracter);
			filtro = string;
		}
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}

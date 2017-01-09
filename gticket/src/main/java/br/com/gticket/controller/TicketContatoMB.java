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
import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Contato;
import br.com.gticket.model.SituacaoTicket;
import br.com.gticket.model.TicketContato;
import br.com.gticket.util.FacesUtil;
import br.com.gticket.util.Util;

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
	private String tempoAtendimento;

	@PostConstruct
	public void init() {

		ticket = new TicketContato();
		ticket.setTicketFinalizado(false);
		ticket.setDataDeInclusao(new Date());
		bo = new TicketContatoBO();
		contatos = new ArrayList<Contato>();
		tempoAtendimento = "00:00:00";

	}

	public String salvar() {

		try {
			bo.salvar(ticket);

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

			return "lista_tickets_contato?faces-redirect=true";

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

		return "";

	}

	public String excluir(Integer id) {
		bo.excluir(id);
		return "lista_tickets_contato?faces-redirect=true";
	}

	public TicketContato getTicket() {
		return ticket;
	}

	public void setTicket(TicketContato ticket) {
		this.ticket = ticket;
	}

	public List<TicketContato> getTickets() {
		return bo.listar();
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

	public String getTempoAtendimento() {
		return tempoAtendimento;
	}

	public void setTempoAtendimento(String tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
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

	public void calcularTempoAtendimento() {

		if (ticket.getId() == null || ticket.getId() == 0) {
			tempoAtendimento = Util.calcularDiferencaHora(
					ticket.getDataDeInclusao(), new Date());
		} else {
			tempoAtendimento = Util.calcularDiferencaHora(
					ticket.getDataDeInclusao(), ticket.getDataDeFinalizacao());
		}

	}
}

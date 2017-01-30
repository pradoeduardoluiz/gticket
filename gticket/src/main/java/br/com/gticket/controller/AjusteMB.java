package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import br.com.gticket.bo.AjusteBO;
import br.com.gticket.bo.TicketDesenvolvimentoBO;
import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Ajuste;
import br.com.gticket.model.InfracaoAjuste;
import br.com.gticket.model.TicketDesenvolvimento;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AjusteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Ajuste ajuste;
	private AjusteBO bo;
	private List<Ajuste> ajustes;
	private Integer editarId;
	private Integer editarIdTicket;
	private InfracaoAjuste[] infracoes;
	private TicketDesenvolvimento ticket;
	private TicketDesenvolvimentoBO boTicket;
	private InfracaoAjuste infracao;

	@PostConstruct
	public void init() {
		ajuste = new Ajuste();
		bo = new AjusteBO();
		ticket = new TicketDesenvolvimento();
		boTicket = new TicketDesenvolvimentoBO();
	}

	public String salvar() {

		ajuste.setTicket(ticket);

		try {
			bo.salvar(ajuste);

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return "lista_ajustes?id=" + ticket.getId();

	}

	public void carregarAjuste(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		ajuste = bo.buscarPorId(editarId);
	}

	public void carregarTicket(ComponentSystemEvent event) {
		if (editarIdTicket == null) {
			return;
		}

		ticket = boTicket.buscarPorId(editarIdTicket);
	}

	public Ajuste getAjuste() {
		return ajuste;
	}

	public void setAjuste(Ajuste ajuste) {
		this.ajuste = ajuste;
	}

	public List<Ajuste> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<Ajuste> ajustes) {
		this.ajustes = ajustes;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}
	
	public Integer getEditarIdTicket() {
		return editarIdTicket;
	}

	public void setEditarIdTicket(Integer editarIdTicket) {
		this.editarIdTicket = editarIdTicket;
	}

	public InfracaoAjuste[] getInfracoes() {
		infracoes = InfracaoAjuste.values();
		return infracoes;
	}

	public void setInfracoes(InfracaoAjuste[] infracoes) {
		this.infracoes = infracoes;
	}

	public TicketDesenvolvimento getTicket() {
		return ticket;
	}

	public void setTicket(TicketDesenvolvimento ticket) {
		this.ticket = ticket;
	}

	public InfracaoAjuste getInfracao() {
		return infracao;
	}

	public void setInfracao(InfracaoAjuste infracao) {
		this.infracao = infracao;
	}

}

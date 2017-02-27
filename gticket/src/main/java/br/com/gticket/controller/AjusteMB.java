package br.com.gticket.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;

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

		if (ajuste.getId() == null)
			ajuste.setTicket(ticket);

		try {
			bo.salvar(ajuste);

			FacesUtil.addInfoMessage("Cadastro Salvo com Sucesso");

		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

		return "lista_ajustes?ticket=" + ticket.getId()
				+ "&faces-redirect=true";

	}

	public void limpar() {

		ajuste = new Ajuste();

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

	public String excluir(Integer id) {

		bo.excluir(id);

		return "lista_ajustes?ticket=" + ticket.getId()
				+ "&faces-redirect=true";

	}

	public String finalizar(Integer id) {

		bo.finalizar(id);

		return "lista_ajustes?ticket=" + ticket.getId()
				+ "&faces-redirect=true";

	}

	public String reabrir(Integer id) {

		bo.reabrir(id);

		return "lista_ajustes?ticket=" + ticket.getId()
				+ "&faces-redirect=true";

	}

	public String finalizarTestes() {

		try {
			boTicket.finalizarTestes(ticket);

			FacesUtil.addInfoMessage("ticket " + ticket.getId()
					+ " finalizado com sucesso!");

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		return "lista_tickets_desenv?filtro='testes'";

	}

	public String notificarDesenv() {

		try {
			boTicket.notificarDesenv(ticket);

			FacesUtil.addInfoMessage("Notificação enviada com sucesso!");

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		return null;
	}

	public String notificarTester() {

		try {
			boTicket.notificarTester(ticket);

			FacesUtil.addInfoMessage("Notificação enviada com sucesso!");

		} catch (Exception e) {

			FacesUtil.addErrorMessage(e.getMessage());

		}

		return null;
	}

	public Ajuste getAjuste() {
		return ajuste;
	}

	public void setAjuste(Ajuste ajuste) {
		this.ajuste = ajuste;
	}

	public List<Ajuste> getAjustes() {
		return bo.listar(ticket);
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

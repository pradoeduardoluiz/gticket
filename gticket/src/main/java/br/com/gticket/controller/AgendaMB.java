package br.com.gticket.controller;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.mail.EmailException;
import org.junit.runner.Request;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.gticket.bo.AgendaBO;
import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;
import br.com.gticket.model.Agenda;
import br.com.gticket.model.TipoAgenda;
import br.com.gticket.util.FacesUtil;

@ManagedBean
@ViewScoped
public class AgendaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private ScheduleModel eventModel;
	private ScheduleEvent event;
	private Agenda agenda;
	private AgendaBO bo;
	private List<Agenda> agendas;
	private List<Agenda> minhasAgendas;
	public Boolean agendaOk = false;
	public TipoAgenda[] tipos;
	private Integer editarId;

	@PostConstruct
	public void init() {
		agenda = new Agenda();
		bo = new AgendaBO();
		event = new DefaultScheduleEvent();
		eventModel = carregaAgendas();

	}

	public String salvar() {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			bo.salvar(agenda);
			agendaOk = true;
		} catch (ValorEmBrancoException | ValorZeradoException
				| ValorInvalidoException | EmailException e) {

			agendaOk = false;
			FacesUtil.addErrorMessage(e.getMessage());

		}

		context.addCallbackParam("agendaOk", agendaOk);
		if (agendaOk) {
			return "agenda?faces-redirect=true";
		} else {
			return null;
		}

	}

	public String finalizar() {

		try {
			bo.finalizar(agenda);
		} catch (ValorEmBrancoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
			return null;

		}

		return "lista_agendas";

	}

	public String excluir(Integer id) {

		bo.excluir(id);

		return "agenda?faces-redirect=true";

	}

	public void carregar(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		agenda = bo.buscarPorId(editarId);
	}

	private ScheduleModel carregaAgendas() {

		ScheduleModel event;
		event = new DefaultScheduleModel();

		List<Agenda> agendas = bo.listar();

		for (Agenda agenda : agendas) {

			DefaultScheduleEvent eventAgenda = new DefaultScheduleEvent();

			eventAgenda.setStartDate(agenda.getDataInicio());
			eventAgenda.setEndDate(agenda.getDataFim());
			eventAgenda.setTitle(agenda.getDescricao());
			eventAgenda.setId(String.valueOf(agenda.getId()));
			eventAgenda.setStyleClass(retornaStyleClass(agenda.getTipo()));

			event.addEvent(eventAgenda);
			eventAgenda.setId(String.valueOf(agenda.getId()));
		}

		return event;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public void addEvent(ActionEvent actionEvent) {

		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
		agenda = new Agenda();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();

		agenda = new Agenda();

		if (event.getId() != null) {
			agenda = bo.buscarPorId(Integer.parseInt(event.getId()));
		} else {

			agenda.setDataInicio(event.getStartDate());
			agenda.setDataFim(event.getEndDate());

		}

	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());

		if (event.getId() == null) {
			agenda = new Agenda();
			agenda.setDataInicio(event.getStartDate());
			agenda.setDataFim(event.getEndDate());
		}

	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public List<Agenda> getMinhasAgendas() {

		try {
			minhasAgendas = bo.listarMinhasAgendas();
		} catch (ValorInvalidoException e) {

			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}

		return minhasAgendas;
	}

	public void setMinhasAgendas(List<Agenda> minhasAgendas) {
		this.minhasAgendas = minhasAgendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public Boolean getAgendaOk() {
		return agendaOk;
	}

	public void setAgendaOk(Boolean agendaOk) {
		this.agendaOk = agendaOk;
	}

	public TipoAgenda[] getTipos() {

		tipos = TipoAgenda.values();

		return tipos;
	}

	public void setTipos(TipoAgenda[] tipos) {
		this.tipos = tipos;
	}

	public Integer getEditarId() {
		return editarId;
	}

	public void setEditarId(Integer editarId) {
		this.editarId = editarId;
	}

	private String retornaStyleClass(TipoAgenda tipo) {

		String styleClass = "";

		if (tipo.equals(TipoAgenda.TREINAMENTO)) {
			styleClass = "agendaTreinamento";
		}

		if (tipo.equals(TipoAgenda.REUNIÃO)) {
			styleClass = "agendaReuniao";
		}

		if (tipo.equals(TipoAgenda.CURSO)) {
			styleClass = "agendaCurso";
		}

		if (tipo.equals(TipoAgenda.FOLGA)) {
			styleClass = "agendaFolga";
		}

		return styleClass;
	}

}

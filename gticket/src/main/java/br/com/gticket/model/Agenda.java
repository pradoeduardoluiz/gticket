package br.com.gticket.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name = "agenda")
public class Agenda {

	@Id
	@GeneratedValue
	private Integer id;
	private Date dataInicio;
	private Date dataFim;
	private String titulo;
	private String descricao;
	private Boolean diaTodo;
	@Enumerated(EnumType.STRING)
	private TipoAgenda tipo;
	@OneToOne
	private Cliente cliente;
	private Boolean finalizada;
	private String resultado;
	private Date dataBaixa;
	@OneToOne
	private Usuario usuario;
	@Transient
	private StatusAgenda status;
	@Transient
	private String corStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(Boolean diaTodo) {
		this.diaTodo = diaTodo;
	}

	public TipoAgenda getTipo() {
		return tipo;
	}

	public void setTipo(TipoAgenda tipo) {
		this.tipo = tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Date getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(Date dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusAgenda getStatus() {

		if (this.finalizada) {
			status = StatusAgenda.CONCLUIDA;
		} else

		if (this.dataFim.getTime() < new Date().getTime()) {
			status = StatusAgenda.ATRASADA;
		} else {

			status = StatusAgenda.PENDENTE;
		}

		return status;
	}

	public void setStatus(StatusAgenda status) {
		this.status = status;
	}

	public String getCorStatus() {

		if (this.status.equals(StatusAgenda.ATRASADA)) {
			return "label label-danger";
		}

		if (this.status.equals(StatusAgenda.PENDENTE)) {
			return "label label-warning";
		}

		if (this.status.equals(StatusAgenda.CONCLUIDA)) {
			return "label label-success";
		}

		return corStatus;
	}

	public void setCorStatus(String corStatus) {
		this.corStatus = corStatus;
	}

}

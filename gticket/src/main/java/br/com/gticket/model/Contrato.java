package br.com.gticket.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "contrato")
public class Contrato {

	@Id
	@GeneratedValue
	private Integer numeroContrato;
	private Integer numeroDeLicencas;
	private Integer numeroDeFiliais;
	private Integer limiteMensalDeHoras;
	private Boolean contratoPremium;
	private Boolean contratoRecebido;
	private Boolean clienteEmAtrito;
	private Date dataInicio;
	private Date dataTermino;
	private String observacao;

	public Integer getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(Integer numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Integer getNumeroDeLicencas() {
		return numeroDeLicencas;
	}

	public void setNumeroDeLicencas(Integer numeroDeLicencas) {
		this.numeroDeLicencas = numeroDeLicencas;
	}

	public Integer getNumeroDeFiliais() {
		return numeroDeFiliais;
	}

	public void setNumeroDeFiliais(Integer numeroDeFiliais) {
		this.numeroDeFiliais = numeroDeFiliais;
	}

	public Integer getLimiteMensalDeHoras() {
		return limiteMensalDeHoras;
	}

	public void setLimiteMensalDeHoras(Integer limiteMensalDeHoras) {
		this.limiteMensalDeHoras = limiteMensalDeHoras;
	}

	public Boolean getContratoPremium() {
		return contratoPremium;
	}

	public void setContratoPremium(Boolean contratoPremium) {
		this.contratoPremium = contratoPremium;
	}

	public Boolean getContratoRecebido() {
		return contratoRecebido;
	}

	public void setContratoRecebido(Boolean contratoRecebido) {
		this.contratoRecebido = contratoRecebido;
	}

	public Boolean getClienteEmAtrito() {
		return clienteEmAtrito;
	}

	public void setClienteEmAtrito(Boolean clienteEmAtrito) {
		this.clienteEmAtrito = clienteEmAtrito;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}

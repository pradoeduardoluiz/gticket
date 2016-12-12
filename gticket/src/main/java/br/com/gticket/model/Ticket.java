package br.com.gticket.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade = { CascadeType.ALL })
	private Cliente cliente;
	@OneToOne(cascade = { CascadeType.ALL })
	private Contato contato;
	private String assunto;
	private String descricao;
	private String observacao;
	private SituacaoTicket situacao;
	@OneToOne(cascade = { CascadeType.ALL })
	private Categoria categoria;
	private Date dataDeInclusao;
	private Date dataDeFinalizacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public SituacaoTicket getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoTicket situacao) {
		this.situacao = situacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getDataDeInclusao() {
		return dataDeInclusao;
	}

	public void setDataDeInclusao(Date dataDeInclusao) {
		this.dataDeInclusao = dataDeInclusao;
	}

	public Date getDataDeFinalizacao() {
		return dataDeFinalizacao;
	}

	public void setDataDeFinalizacao(Date dataDeFinalizacao) {
		this.dataDeFinalizacao = dataDeFinalizacao;
	}

}

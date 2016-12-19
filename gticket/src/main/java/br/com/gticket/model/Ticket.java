package br.com.gticket.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity(name = "ticket")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {

	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade = { CascadeType.ALL })
	private Cliente cliente;
	@OneToOne(cascade = { CascadeType.ALL })
	private Contato contato;
	private String assunto;
	private SituacaoTicket situacao;
	@OneToOne(cascade = { CascadeType.ALL })
	private Categoria categoria;
	private String topico;
	@OneToOne(cascade = { CascadeType.ALL })
	private Usuario usuarioInclusao;
	private Date dataDeInclusao;
	private Date dataDeFinalizacao;
	private Boolean ticketFinalizado;

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

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
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

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
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

	public Boolean getTicketFinalizado() {
		return ticketFinalizado;
	}

	public void setTicketFinalizado(Boolean ticketFinalizado) {
		this.ticketFinalizado = ticketFinalizado;
	}

}

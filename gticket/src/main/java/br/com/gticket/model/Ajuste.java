package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ajuste")
public class Ajuste {

	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "ticket_id")
	private TicketDesenvolvimento ticket;
	private String comentario;
	private String programas;
	private Boolean concluido;
	@Enumerated(EnumType.STRING)
	private InfracaoAjuste infracao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getProgramas() {
		return programas;
	}

	public void setProgramas(String programas) {
		this.programas = programas;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
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

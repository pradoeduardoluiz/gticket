package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "ticket_contato")
@PrimaryKeyJoinColumn(name = "id")
public class TicketContato extends Ticket {

	private String resolucao;

	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

}

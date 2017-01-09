package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;

import br.com.gticket.util.Util;

@Entity(name = "ticket_contato")
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "update ticket set ativo = 0 where id = ?")
public class TicketContato extends Ticket {

	private String resolucao;
	@Transient
	private String tempoAtendimento;

	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public String getTempoAtendimento() {

		tempoAtendimento = Util.calcularDiferencaHora(getDataDeInclusao(),
				getDataDeFinalizacao());

		return tempoAtendimento;
	}

	public void setTempoAtendimento(String tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

}

package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "contato")
@PrimaryKeyJoinColumn(name = "contato_id")
public class Contato extends PessoaFisica {

	@Enumerated(EnumType.STRING)
	private Departamento departamento;

	private Boolean recebeAvisoAtualizacao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Boolean getRecebeAvisoAtualizacao() {
		return recebeAvisoAtualizacao;
	}

	public void setRecebeAvisoAtualizacao(Boolean recebeAvisoAtualizacao) {
		this.recebeAvisoAtualizacao = recebeAvisoAtualizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}

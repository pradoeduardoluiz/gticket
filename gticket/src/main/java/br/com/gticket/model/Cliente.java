package br.com.gticket.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cliente")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends PessoaJuridica {

	@OneToOne(cascade = { CascadeType.ALL })
	private Endereco endereco;

	@OneToOne(cascade = { CascadeType.ALL })
	private Contrato contrato;

	public Cliente() {
		endereco = new Endereco();
		contrato = new Contrato();
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}

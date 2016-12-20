package br.com.gticket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class Cliente extends PessoaJuridica {

	@OneToOne(cascade = { CascadeType.ALL })
	private Endereco endereco;

	@OneToOne(cascade = { CascadeType.ALL })
	private Contrato contrato;

	@OneToMany(mappedBy = "cliente")
	private List<Contato> contatos;

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

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public void addContato(Contato contato) {

		contatos.add(contato);

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}

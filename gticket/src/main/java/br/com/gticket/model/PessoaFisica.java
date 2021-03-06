package br.com.gticket.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public abstract class PessoaFisica extends Pessoa {

	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String email;
	private String telefone;
	private String celular;

	public PessoaFisica() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}

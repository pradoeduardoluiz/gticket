package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private String inscricaoEstadual;
	private Integer telefone1;
	private Integer telefone2;
	private Integer fax;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Integer getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(Integer telefone1) {
		this.telefone1 = telefone1;
	}

	public Integer getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(Integer telefone2) {
		this.telefone2 = telefone2;
	}

	public Integer getFax() {
		return fax;
	}

	public void setFax(Integer fax) {
		this.fax = fax;
	}

}

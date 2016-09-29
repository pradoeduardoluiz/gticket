package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

	@Id
	@GeneratedValue
	private Integer id;
	private String obsercacao;

	public Pessoa() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObsercacao() {
		return obsercacao;
	}

	public void setObsercacao(String obsercacao) {
		this.obsercacao = obsercacao;
	}

}

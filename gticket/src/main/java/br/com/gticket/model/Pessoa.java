package br.com.gticket.model;

public abstract class Pessoa {

	private Integer id;
	private String obsercacao;

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

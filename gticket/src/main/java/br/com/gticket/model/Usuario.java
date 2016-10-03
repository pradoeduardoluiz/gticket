package br.com.gticket.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity(name = "usuario")
@PrimaryKeyJoinColumn(name = "id")
public class Usuario extends PessoaFisica {

	private String senha;
	@Transient
	private String confirmacaoDeSenha;
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	public Usuario() {
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}

	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}

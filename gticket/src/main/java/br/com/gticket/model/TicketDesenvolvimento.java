package br.com.gticket.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;

import br.com.gticket.util.Util;

@Entity(name = "ticket_desenvolvimento")
@PrimaryKeyJoinColumn(name = "id")
@SQLDelete(sql = "update ticket set ativo = 0 where id = ?")
public class TicketDesenvolvimento extends Ticket {

	private String analise;
	private String programas;
	private String comentariosParaTeste;
	@OneToOne
	private Usuario desenvolvedor;
	@OneToOne
	private Usuario analistaTeste;
	@Enumerated(EnumType.STRING)
	private StatusTicket statusTicket;
	@Enumerated(EnumType.STRING)
	private StatusProgresso statusProcesso;
	private Date dataUltimoPlay;
	private Date dataUltimoPause;
	private Integer tempoDesenvolvimento;
	@Transient
	private String strTempoDesenvolvimento;
	private Boolean emAndamento;

	@OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ajuste> ajustes;

	public String getAnalise() {
		return analise;
	}

	public void setAnalise(String analise) {
		this.analise = analise;
	}

	public Usuario getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(Usuario desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public Usuario getAnalistaTeste() {
		return analistaTeste;
	}

	public void setAnalistaTeste(Usuario analistaTeste) {
		this.analistaTeste = analistaTeste;
	}

	public StatusTicket getStatusTicket() {
		return statusTicket;
	}

	public void setStatusTicket(StatusTicket statusTicket) {
		this.statusTicket = statusTicket;
	}

	public StatusProgresso getStatusProcesso() {
		return statusProcesso;
	}

	public void setStatusProcesso(StatusProgresso statusProcesso) {
		this.statusProcesso = statusProcesso;
	}

	public Date getDataUltimoPlay() {
		return dataUltimoPlay;
	}

	public void setDataUltimoPlay(Date dataUltimoPlay) {
		this.dataUltimoPlay = dataUltimoPlay;
	}

	public Date getDataUltimoPause() {
		return dataUltimoPause;
	}

	public void setDataUltimoPause(Date dataUltimoPause) {
		this.dataUltimoPause = dataUltimoPause;
	}

	public Integer getTempoDesenvolvimento() {
		return tempoDesenvolvimento;
	}

	public void setTempoDesenvolvimento(Integer tempoDesenvolvimento) {
		this.tempoDesenvolvimento = tempoDesenvolvimento;
	}

	public Boolean getEmAndamento() {
		return emAndamento;
	}

	public void setEmAndamento(Boolean emAndamento) {
		this.emAndamento = emAndamento;
	}

	public String getStrTempoDesenvolvimento() {
		strTempoDesenvolvimento = Util
				.secundosEmHoras(this.tempoDesenvolvimento);
		return strTempoDesenvolvimento;
	}

	public void setStrTempoDesenvolvimento(String strTempoDesenvolvimento) {
		this.tempoDesenvolvimento = Util
				.horasEmSegundos(strTempoDesenvolvimento);

	}

	public String getProgramas() {
		return programas;
	}

	public void setProgramas(String programas) {
		this.programas = programas;
	}

	public String getComentariosParaTeste() {
		return comentariosParaTeste;
	}

	public void setComentariosParaTeste(String comentariosParaTeste) {
		this.comentariosParaTeste = comentariosParaTeste;
	}

	public List<Ajuste> getAjustes() {
		return ajustes;
	}

	public void setAjustes(List<Ajuste> ajustes) {
		this.ajustes = ajustes;
	}

}

package br.com.gticket.util;

import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.gticket.model.Agenda;
import br.com.gticket.model.TicketContato;
import br.com.gticket.model.TicketDesenvolvimento;
import br.com.gticket.model.Usuario;

public class EmailUtil {

	public static void enviarEmailTicketAbertura(TicketContato ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getContato().getNome()
				+ "<br><br> Foi aberto o ticket#"
				+ String.valueOf(ticket.getId()) + " em "
				+ Util.dataEmString(ticket.getDataDeInclusao())
				+ "<br><br>Situação: " + ticket.getSituacao()
				+ "<br><br>Categoria: " + ticket.getCategoria().getNome()
				+ "<br><br>Topico: " + ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>Resolução: "
				+ ticket.getResolucao() + "<br><br>Duração:"
				+ ticket.getTempoAtendimento() + "<br><br> Atendito por: "
				+ ticket.getUsuarioInclusao() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Abertura de Ticket de Contato #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailTicketAbertura(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getContato().getNome()
				+ "<br><br> Foi aberto o ticket #"
				+ String.valueOf(ticket.getId()) + " em "
				+ Util.dataEmString(ticket.getDataDeInclusao())
				+ "<br><br>Situação: " + ticket.getSituacao()
				+ "<br><br>Categoria: " + ticket.getCategoria().getNome()
				+ "<br><br>Topico: " + ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br> Atendito por: "
				+ ticket.getUsuarioInclusao() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Abertura de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailTicketAprovacao(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola "
				+ ticket.getContato().getNome()
				+ "<br><br> O ticket #"
				+ String.valueOf(ticket.getId())
				+ " foi aprovado "
				+ "<br><br>Situação: "
				+ ticket.getSituacao()
				+ "<br><br>Categoria: "
				+ ticket.getCategoria().getNome()
				+ "<br><br>Topico: "
				+ ticket.getTopico()
				+ "<br><br>Assunto: "
				+ ticket.getAssunto()
				+ "<br><br> Em breve será disponibilizado uma atualização para você. "
				+ "<br><br> att " + "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Aprovação de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

		htmlMsg = "<html>Ola " + ticket.getDesenvolvedor().getNome()
				+ "<br><br> O ticket #" + String.valueOf(ticket.getId())
				+ " foi aprovado para você" + "<br><br>Situação: "
				+ ticket.getSituacao() + "<br><br>Categoria: "
				+ ticket.getCategoria().getNome() + "<br><br>Topico: "
				+ ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>Análise: "
				+ ticket.getAnalise() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getDesenvolvedor().getEmail(),
				"Aprovação de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailTicketFinalizacao() {

	}

	private static void enviarHtmlEmail(String destinatario, String assunto,
			String texto) throws EmailException {

		String server = getWebXmlParametro("userServer");
		String remetente = getWebXmlParametro("userEmail");
		String senha = getWebXmlParametro("userPassword");
		String porta = getWebXmlParametro("userPort");

		HtmlEmail email = new HtmlEmail();

		email.setHostName(server);
		email.setSmtpPort(Integer.parseInt(porta));
		email.setAuthentication(remetente, senha);
		email.setSSLOnConnect(true);
		email.setFrom(remetente);
		email.setSubject(assunto);

		email.setHtmlMsg(texto);

		email.addTo(destinatario);
		email.send();

	}

	private static String getWebXmlParametro(String parametro) {

		FacesContext fc = FacesContext.getCurrentInstance();

		return fc.getExternalContext().getInitParameter(parametro);
	}

	public static void enviarEmailTicketReprovado(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola "
				+ ticket.getContato().getNome()
				+ "<br><br> Infelizmente o ticket #"
				+ String.valueOf(ticket.getId())
				+ " foi reprovado, segue abaixo o motivo "
				+ "<br><br>Situação: "
				+ ticket.getSituacao()
				+ "<br><br>Categoria: "
				+ ticket.getCategoria().getNome()
				+ "<br><br>Topico: "
				+ ticket.getTopico()
				+ "<br><br>Assunto: "
				+ ticket.getAssunto()
				+ "<br><br>Motivo: "
				+ ticket.getAnalise()
				+ "<br><br> Qualquer dúvida entre em contato com nosso suporte técnico. "
				+ "<br><br> att " + "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Reprovação de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailTicketTestes(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getAnalistaTeste().getNome()
				+ "<br><br> O ticket #" + String.valueOf(ticket.getId())
				+ " foi finalizado e passado para teste" + "<br><br>Situação: "
				+ ticket.getSituacao() + "<br><br>Categoria: "
				+ ticket.getCategoria().getNome() + "<br><br>Topico: "
				+ ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>Análise: "
				+ ticket.getAnalise() + "<br><br>Programas Alterados: "
				+ ticket.getProgramas() + "<br><br>Comentários para testes: "
				+ ticket.getComentariosParaTeste() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getAnalistaTeste().getEmail(),
				"Aprovação de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarNovaSenha(Usuario usuario, String novaSenha)
			throws EmailException {

		String htmlMsg = "<html>Ola "
				+ usuario.getNome()
				+ "<br><br> Segue a abaixo sua nova senha para acessar o sistema"
				+ "<br> Nova Senha: <b>" + novaSenha + "</b><br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(usuario.getEmail(),
				"Envio de Nova Senha - Sistema GTicket", htmlMsg);

	}

	public static void enviarEmailNotificarDesenv(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getDesenvolvedor().getNome()

		+ "<br><br> Foram reportados ajustes referente ao ticket #"
				+ ticket.getId() + " testados por "
				+ ticket.getAnalistaTeste().getNome()
				+ ". Por favor verificar e realizar ajustes"
				+ "</b><br><br> att " + "<br><br>FKN Informática LTDA "
				+ "</html>";

		enviarHtmlEmail(ticket.getDesenvolvedor().getEmail(),
				"Notificação de Ajuste - Sistema GTicket", htmlMsg);

	}

	public static void enviarEmailNotificarTester(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getAnalistaTeste().getNome()

		+ "<br><br> Foram realizados os ajustes referente ao ticket #"
				+ ticket.getId() + " ajustados por por "
				+ ticket.getDesenvolvedor().getNome()
				+ ". Por favor verificar e realizar os testes"
				+ "</b><br><br> att " + "<br><br>FKN Informática LTDA "
				+ "</html>";

		enviarHtmlEmail(ticket.getAnalistaTeste().getEmail(),
				"Notificação de Ajuste - Sistema GTicket", htmlMsg);

	}

	public static void enviarEmailTicketFinalizado(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getContato().getNome()
				+ "<br><br> Foi finalizado o ticket #"
				+ String.valueOf(ticket.getId()) + " em "
				+ Util.dataEmString(ticket.getDataDeInclusao())
				+ " e em breve estará sendo enviado para você. "
				+ "<br><br>Situação: " + ticket.getSituacao()
				+ "<br><br>Categoria: " + ticket.getCategoria().getNome()
				+ "<br><br>Topico: " + ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br> Atendito por: "
				+ ticket.getUsuarioInclusao() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Abertura de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailAgenda(Agenda agenda) throws EmailException {

		String htmlMsg = "<html>Ola " + agenda.getUsuario().getNome()
				+ "<br><br> Foi criada uma agenda para dia "
				+ Util.dataEmString(agenda.getDataInicio()) + " a "
				+ Util.dataEmString(agenda.getDataFim()) + "<br><br>Título: "
				+ agenda.getTitulo() + "<br><br>Descrição: "
				+ agenda.getDescricao() + "<br><br> att "
				+ "<br><br>FKN Informática LTDA " + "</html>";

		enviarHtmlEmail(agenda.getUsuario().getEmail(), "Agenda realizada",
				htmlMsg);

	}
}

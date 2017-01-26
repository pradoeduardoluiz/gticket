package br.com.gticket.util;

import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.gticket.model.TicketContato;
import br.com.gticket.model.TicketDesenvolvimento;

public class EmailUtil {

	public static void enviarEmailTicketAbertura(TicketContato ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getContato().getNome()
				+ "<br><br> Foi aberto o ticket#"
				+ String.valueOf(ticket.getId()) + " em "
				+ Util.dataEmString(ticket.getDataDeInclusao())
				+ "<br><br>Situa��o: " + ticket.getSituacao()
				+ "<br><br>Categoria: " + ticket.getCategoria().getNome()
				+ "<br><br>Topico: " + ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>Resolu��o: "
				+ ticket.getResolucao() + "<br><br>Dura��o:"
				+ ticket.getTempoAtendimento() + "<br><br> Atendito por: "
				+ ticket.getUsuarioInclusao() + "<br><br> att "
				+ "<br><br>FKN Inform�tica LTDA " + "</html>";

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
				+ "<br><br>Situa��o: " + ticket.getSituacao()
				+ "<br><br>Categoria: " + ticket.getCategoria().getNome()
				+ "<br><br>Topico: " + ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br> Atendito por: "
				+ ticket.getUsuarioInclusao() + "<br><br> att "
				+ "<br><br>FKN Inform�tica LTDA " + "</html>";

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
				+ "<br><br>Situa��o: "
				+ ticket.getSituacao()
				+ "<br><br>Categoria: "
				+ ticket.getCategoria().getNome()
				+ "<br><br>Topico: "
				+ ticket.getTopico()
				+ "<br><br>Assunto: "
				+ ticket.getAssunto()
				+ "<br><br> Em breve ser� disponibilizado uma atualiza��o para voc�. "
				+ "<br><br> att " + "<br><br>FKN Inform�tica LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Aprova��o de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

		htmlMsg = "<html>Ola " + ticket.getDesenvolvedor().getNome()
				+ "<br><br> O ticket #" + String.valueOf(ticket.getId())
				+ " foi aprovado para voc�" + "<br><br>Situa��o: "
				+ ticket.getSituacao() + "<br><br>Categoria: "
				+ ticket.getCategoria().getNome() + "<br><br>Topico: "
				+ ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>An�lise: "
				+ ticket.getAnalise() + "<br><br> att "
				+ "<br><br>FKN Inform�tica LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getDesenvolvedor().getEmail(),
				"Aprova��o de Ticket de Desenvolvimento #"
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
				+ "<br><br>Situa��o: "
				+ ticket.getSituacao()
				+ "<br><br>Categoria: "
				+ ticket.getCategoria().getNome()
				+ "<br><br>Topico: "
				+ ticket.getTopico()
				+ "<br><br>Assunto: "
				+ ticket.getAssunto()
				+ "<br><br>Motivo: "
				+ ticket.getAnalise()
				+ "<br><br> Qualquer d�vida entre em contato com nosso suporte t�cnico. "
				+ "<br><br> att " + "<br><br>FKN Inform�tica LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getContato().getEmail(),
				"Reprova��o de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}

	public static void enviarEmailTicketTestes(TicketDesenvolvimento ticket)
			throws EmailException {

		String htmlMsg = "<html>Ola " + ticket.getAnalistaTeste().getNome()
				+ "<br><br> O ticket #" + String.valueOf(ticket.getId())
				+ " foi finalizado e passado para teste" + "<br><br>Situa��o: "
				+ ticket.getSituacao() + "<br><br>Categoria: "
				+ ticket.getCategoria().getNome() + "<br><br>Topico: "
				+ ticket.getTopico() + "<br><br>Assunto: "
				+ ticket.getAssunto() + "<br><br>An�lise: "
				+ ticket.getAnalise() + "<br><br>Programas Alterados: "
				+ ticket.getProgramas() + "<br><br>Coment�rios para testes: "
				+ ticket.getComentariosParaTeste() + "<br><br> att "
				+ "<br><br>FKN Inform�tica LTDA " + "</html>";

		enviarHtmlEmail(
				ticket.getAnalistaTeste().getEmail(),
				"Aprova��o de Ticket de Desenvolvimento #"
						+ String.valueOf(ticket.getId()), htmlMsg);

	}
}

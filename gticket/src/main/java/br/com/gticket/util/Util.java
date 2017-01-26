package br.com.gticket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.gticket.model.TicketContato;
import br.com.gticket.model.TicketDesenvolvimento;

public class Util {

	public static String calcularDiferencaHora(Date dataInicial, Date dataFinal) {

		if (dataFinal == null) {
			return "00:00:00";
		}

		long tempoSegundos = calcularTempo(dataInicial, dataFinal);

		return secundosEmHoras(tempoSegundos);

	}

	public static Integer calcularTempoDesenvolvimento(Integer integer,
			Date dataInicial, Date dataFinal) {

		if (integer == null) {
			integer = 0;
		}

		Integer tempo = integer;

		tempo = tempo + calcularTempo(dataInicial, dataFinal);

		return tempo;
	}

	private static Integer calcularTempo(Date dataInicial, Date dataFinal) {

		long tempoMilesegundos = dataFinal.getTime() - dataInicial.getTime();

		long tempoSegundos = TimeUnit.MILLISECONDS.toSeconds(tempoMilesegundos);

		return (int) tempoSegundos;

	}

	public static String secundosEmHoras(long tempoSegundos) {

		int hora = (int) (tempoSegundos / 3600);
		int sobra = (int) (tempoSegundos % 3600);
		int minuto = sobra / 60;
		int segundo = sobra % 60;

		String hms = String.format("%02d:%02d", hora, minuto);

		return hms;

	}

	public static Integer horasEmSegundos(String strHora) {

		int posicao = strHora.indexOf(":") - 2;

		String h = strHora.substring(posicao, posicao + 2);
		String m = strHora.substring(posicao + 3, posicao + 5);

		int hora = Integer.parseInt(h) * 3600;
		int minutos = Integer.parseInt(m) * 60;

		return hora + minutos;

	}

	public static void validarHora(String StringHora) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		sdf.setLenient(false);

		Date hora = sdf.parse(StringHora);

	}


	public static String dataEmString(Date data) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
		return sdf.format(data);

	}

}

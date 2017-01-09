package br.com.gticket.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Util {

	public static String calcularDiferencaHora(Date dataInicial, Date datafinal) {

		if (datafinal == null) {
			return "00:00:00";
		}

		long tempoMilesegundos = datafinal.getTime() - dataInicial.getTime();

		long tempoSegundos = TimeUnit.MILLISECONDS.toSeconds(tempoMilesegundos);

		int hora = (int) (tempoSegundos / 3600);
		int sobra = (int) (tempoSegundos % 3600);
		int minuto = sobra / 60;
		int segundo = sobra % 60;

		String horaString = String.valueOf(hora);
		String minutoString = String.valueOf(minuto);
		String segundoString = String.valueOf(segundo);

		if (segundo < 10) {
			segundoString = "0" + segundo;
		}

		if (minuto < 10) {
			minutoString = "0" + minuto;
		}

		if (hora < 10) {
			horaString = "0" + hora;
		}

		return (horaString + ":" + minutoString + ":" + segundoString);

	}

}

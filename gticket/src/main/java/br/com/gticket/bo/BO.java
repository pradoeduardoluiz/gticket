package br.com.gticket.bo;

import java.util.Date;

import br.com.gticket.model.Estado;

public class BO {

	protected boolean campoVazio(Date campo) {
		if (campo == null) {
			return true;
		}
		return false;
	}

	protected boolean campoVazio(Integer campo) {
		// TODO Auto-generated method stub

		if (campo == null || campo == 0) {
			return true;
		}
		return false;

	}

	protected boolean campoVazio(String campo) {

		if (campo == null || campo.isEmpty()) {
			return true;
		}
		return false;

	}

	protected boolean campoVazio(Estado estado) {

		if (estado == null || estado.equals("Selecione...")
				|| estado.equals("")) {
			return true;
		}
		return false;
	}

	protected boolean cpfValido(String strCpf) {

		String cpf = strCpf.replace(".", "").replace("-", "");

		if (cpf.equals("00000000000") || cpf.equals("11111111111")
				|| cpf.equals("22222222222") || cpf.equals("33333333333")
				|| cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777")
				|| cpf.equals("88888888888") || cpf.equals("99999999999")) {
			return false;
		}

		if (cpf.length() != 11) {
			return false;
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {

				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)

				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;

			}

			// converte no respectivo caractere
			// numerico

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			// Verifica se os digitos calculados conferem com os digitos
			// informados.

			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (true);
			} else {
				return (false);
			}

		} catch (Exception e) {
			return (false);
		}
	}

	protected boolean cnpjValido(String strCnpj) {

		String cnpj = strCnpj.replace(".", "").replace("-", "")
				.replace("/", "");

		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333")
				|| cnpj.equals("44444444444444")
				|| cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666")
				|| cnpj.equals("77777777777777")
				|| cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999")) {
			return false;
		}

		if (cnpj.length() != 14) {
			return false;
		}

		char dig13, dig14;
		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 2;

			// converte o i-�simo caractere do CNPJ em um n�mero:
			// por exemplo, transforma o caractere '0' no inteiro 0
			// (48 eh a posi��o de '0' na tabela ASCII)

			for (i = 11; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);

			// Verifica se os d�gitos calculados conferem com os d�gitos
			// informados.
			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (Exception erro) {
			return (false);
		}
	}

}

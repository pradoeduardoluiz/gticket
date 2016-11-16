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

}

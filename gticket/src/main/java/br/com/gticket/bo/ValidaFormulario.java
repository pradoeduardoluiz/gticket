package br.com.gticket.bo;

import java.util.Date;

import br.com.gticket.bo.exception.ValorEmBrancoException;

public interface ValidaFormulario {

	abstract void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException;

	abstract boolean inclusao(Object object);

	abstract boolean campoVazio(String string);

	abstract boolean campoVazio(Date data);

	abstract boolean campoVazio(Integer integer);

}

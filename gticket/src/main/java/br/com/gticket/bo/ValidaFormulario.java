package br.com.gticket.bo;

import br.com.gticket.bo.exception.ValorEmBrancoException;
import br.com.gticket.bo.exception.ValorInvalidoException;
import br.com.gticket.bo.exception.ValorZeradoException;

public interface ValidaFormulario {

	abstract void validaCamposObrigatorios(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException;

	abstract void validaCamposNaInclusao(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException;

	abstract void validaCamposUnicos(Object object)
			throws ValorEmBrancoException, ValorZeradoException,
			ValorInvalidoException;

	abstract boolean inclusao(Object object);

}

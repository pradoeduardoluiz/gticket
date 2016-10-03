package br.com.gticket.model.exception;

public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValorInvalidoException() {
	}

	public ValorInvalidoException(String s) {
		super(s);
	}

	public ValorInvalidoException(Throwable cause) {
		super(cause);
	}

	public ValorInvalidoException(String message, Throwable cause) {
		super(message, cause);
	}

}

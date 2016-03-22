package br.com.gvt.eng.paytv.ingest.exception;

public class NullIdException extends RuntimeException {

	private static final long serialVersionUID = -8211615237326372502L;

	public NullIdException() {
		super();
	}

	public NullIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullIdException(String message) {
		super(message);
	}

	public NullIdException(Throwable cause) {
		super(cause);
	}

}
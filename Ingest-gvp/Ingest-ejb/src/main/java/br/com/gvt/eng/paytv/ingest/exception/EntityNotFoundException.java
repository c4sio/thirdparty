package br.com.gvt.eng.paytv.ingest.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String MESSAGE = "Entity not found.";

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}

}
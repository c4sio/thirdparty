package br.com.gvt.eng.paytv.ingest.exception;

public class InconsistentEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InconsistentEntityException() {
		super();
	}

	public InconsistentEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InconsistentEntityException(String message) {
		super(message);
	}

	public InconsistentEntityException(Throwable cause) {
		super(cause);
	}

}
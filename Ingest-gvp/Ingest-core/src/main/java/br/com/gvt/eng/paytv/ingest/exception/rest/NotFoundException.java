package br.com.gvt.eng.paytv.ingest.exception.rest;

@SuppressWarnings("serial")
public class NotFoundException extends ApiException {
	public static final String MESSAGE = "The request could not be found.";

	public NotFoundException(String message) {
		super(message);
	}
}
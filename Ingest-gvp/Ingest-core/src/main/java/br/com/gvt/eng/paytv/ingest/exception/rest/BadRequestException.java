package br.com.gvt.eng.paytv.ingest.exception.rest;

@SuppressWarnings("serial")
public class BadRequestException extends ApiException {
	public static final String MESSAGE = "The request could not be completed due to incorrect syntax.";

	public BadRequestException(String message) {
		super(message);
	}
}
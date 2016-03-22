package br.com.gvt.eng.paytv.ingest.exception.rest;

@SuppressWarnings("serial")
public class ApiException extends Exception {
	public ApiException(String message) {
		super(message);
	}
}
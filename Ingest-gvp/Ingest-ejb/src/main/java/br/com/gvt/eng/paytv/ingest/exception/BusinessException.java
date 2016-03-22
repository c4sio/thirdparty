package br.com.gvt.eng.paytv.ingest.exception;

@SuppressWarnings("serial")
public class BusinessException extends Exception {
	public BusinessException(String message) {
		super(message);
	}
}
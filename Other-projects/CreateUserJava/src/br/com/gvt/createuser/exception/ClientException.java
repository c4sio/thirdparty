package br.com.gvt.createuser.exception;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "ClientException", targetNamespace = "http://www.telefonica.com/wsdl/UNICA/SOAP/common/v1/faults")
public class ClientException extends Exception {

	private ClientExceptionType clientException;

	public ClientException() {
		super();
	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(String message, ClientExceptionType clientException) {
		super(message);
		this.clientException = clientException;
	}

	public ClientException(String message, ClientExceptionType clientException,
			Throwable cause) {
		super(message, cause);
		this.clientException = clientException;
	}

	public ClientExceptionType getFaultInfo() {
		return this.clientException;
	}
}

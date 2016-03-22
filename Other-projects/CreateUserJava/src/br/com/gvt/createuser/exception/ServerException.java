package br.com.gvt.createuser.exception;

import javax.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "ServerException", targetNamespace = "http://www.telefonica.com/wsdl/UNICA/SOAP/common/v1/faults")
public class ServerException extends Exception {

	private ServerExceptionType serverException;

	public ServerException() {
		super();
	}

	public ServerException(String message) {
		super(message);
	}

	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerException(String message, ServerExceptionType serverException) {
		super(message);
		this.serverException = serverException;
	}

	public ServerException(String message, ServerExceptionType serverException,
			Throwable cause) {
		super(message, cause);
		this.serverException = serverException;
	}

	public ServerExceptionType getFaultInfo() {
		return this.serverException;
	}
}

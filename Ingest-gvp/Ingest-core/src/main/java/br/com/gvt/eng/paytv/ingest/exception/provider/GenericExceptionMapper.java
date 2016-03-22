package br.com.gvt.eng.paytv.ingest.exception.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.gvt.eng.paytv.ingest.exception.BusinessException;
import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

	// private static final Logger logger =
	// Logger.getLogger(GenericExceptionMapper.class);

	public Response toResponse(Exception exception) {
		if (exception instanceof WebApplicationException) {
			WebApplicationException e = (WebApplicationException) exception;
			return Response.status(e.getResponse().getStatus())
					.entity(new ApiExceptionResponse(exception.getMessage()))
					.build();
		} else if (exception instanceof EntityNotFoundException) {
			return Response.status(Status.NOT_FOUND)
					.entity(new ApiExceptionResponse(exception.getMessage()))
					.build();
		} else if (exception instanceof BusinessException) {
			return Response.status(Status.BAD_REQUEST)
					.entity(new ApiExceptionResponse(exception.getMessage()))
					.build();
		} else {
			// logger.error("Exception not parsed by GenericExceptionMapper",
			// exception);

			ApiExceptionResponse response = new ApiExceptionResponse(
					exception.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(response).build();
		}
	}
}
package br.com.gvt.eng.paytv.ingest.exception.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.exception.rest.NotFoundException;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

	// private static final Logger logger =
	// Logger.getLogger(ApiExceptionMapper.class);

	public Response toResponse(ApiException exception) {
		Status status;
		if (exception instanceof NotFoundException) {
			status = Status.NOT_FOUND;
		} else if (exception instanceof BadRequestException) {
			status = Status.BAD_REQUEST;
		} else if (exception instanceof NotFoundException) {
			status = Status.NOT_FOUND;
		} else {
			// logger.error("Exception not parsed by ApiExceptionMapper",
			// exception);
			status = Status.INTERNAL_SERVER_ERROR;
		}

		return Response.status(status)
				.entity(new ApiExceptionResponse(exception.getMessage()))
				.build();
	}
}
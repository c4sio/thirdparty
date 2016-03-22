package br.com.gvt.eng.paytv.ingest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.facade.IngestAssetFacade;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Stateless
@Path("/import")
@Api(value = "/import")
public class ImportController {

	@EJB
	private IngestAssetFacade assetFacade;

	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "List assets items", notes = "List assets items", response = IngestAssetVO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> findAllAssets() throws ApiException {
		return this.assetFacade.findAll();
	}

	@GET
	@Path("/ready")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "List assets items", notes = "List assets items", response = IngestAssetVO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> findReadyToSend() throws ApiException {
		return this.assetFacade.findReadyToSend();
	}

	@GET
	@Path("/status/{status}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "List assets items", notes = "List assets items", response = IngestAssetVO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> findByStatus(
			@ApiParam(name = "status", value = "Status value asset", required = true) @PathParam("status") String status)
			throws ApiException {
		return this.assetFacade.findByStatus(status);
	}

	@GET
	@Path("/{assetId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Get detail asset", notes = "Get detail asset", response = IngestAssetVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public IngestAssetVO findAssetByID(
			@ApiParam(name = "assetId", value = "Numeric value assetId", required = true) @PathParam("assetId") long assetId)
			throws ApiException {
		return this.assetFacade.find(assetId);
	}

}

package br.com.gvt.eng.paytv.ingest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.exception.rest.EmptyRequestBodyException;
import br.com.gvt.eng.paytv.ingest.facade.IngestAssetFacade;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Stateless
@Path("/asset")
@Api(value = "/asset")
public class AssetController {

	@EJB
	private IngestAssetFacade assetFacade;

	//@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update asset", notes = "Update asset", response = IngestAssetVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public IngestAssetVO updateAsset(@ApiParam IngestAssetVO ingestAssetVO)
			throws ApiException {

		if (ingestAssetVO == null) {
			throw new EmptyRequestBodyException();
		}

		return this.assetFacade.update(ingestAssetVO);
	}

}
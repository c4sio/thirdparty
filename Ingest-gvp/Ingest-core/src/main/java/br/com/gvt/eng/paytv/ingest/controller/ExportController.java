package br.com.gvt.eng.paytv.ingest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.File;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.exception.rest.EmptyRequestBodyException;
import br.com.gvt.eng.paytv.ingest.facade.CreateAssetXmlDomFacade;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;
import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

@Stateless
@Path("/export")
@Api(value = "/export")
public class ExportController {

	@EJB
	private CreateAssetXmlDomFacade createAssetXmlDomFacade;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create XML", notes = "Create the XML.", response = IngestFolderVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public boolean writeXML(@ApiParam IngestAssetVO ingestAssetVO)
			throws ApiException {

		if (ingestAssetVO == null) {
			throw new EmptyRequestBodyException();
		}

		File xmlFile = new File(ingestAssetVO.getIngestFolder().getUrlRootOut()
				+ "ADI.XML");

		return this.createAssetXmlDomFacade.writeXML(xmlFile, ingestAssetVO);
	}

}

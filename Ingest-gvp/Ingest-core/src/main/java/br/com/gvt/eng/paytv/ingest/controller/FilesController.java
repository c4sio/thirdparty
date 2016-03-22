package br.com.gvt.eng.paytv.ingest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.exception.rest.EmptyRequestBodyException;
import br.com.gvt.eng.paytv.ingest.facade.IngestFolderFacade;
import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

@Stateless
@Path("/file")
@Api(value = "/file")
public class FilesController {

	@EJB
	private IngestFolderFacade ingestFolderFacade;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "List media items", notes = "List folders items", response = IngestFolderVO.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestFolderVO> findAllFiles() throws ApiException {
		return this.ingestFolderFacade.findAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Store folder", notes = "Store the folder information.", response = IngestFolderVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public IngestFolderVO saveFile(@ApiParam IngestFolderVO ingestFolderVO)
			throws ApiException {
		if (ingestFolderVO == null) {
			throw new EmptyRequestBodyException();
		}
		return this.ingestFolderFacade.save(ingestFolderVO);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update folder", notes = "Update the folder information.", response = IngestFolderVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public IngestFolderVO updateFile(@ApiParam IngestFolderVO ingestFolderVO)
			throws ApiException {
		if (ingestFolderVO == null) {
			throw new EmptyRequestBodyException();
		}
		return this.ingestFolderFacade.update(ingestFolderVO);
	}

	@DELETE
	@Path("/{folderID:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Remove the folder", notes = "Remove the informed folder.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public void removeFolder(
			@ApiParam(name = "folderID", value = "Value folderID", required = true) @PathParam("folderID") Long folderID)
			throws ApiException {
		if (folderID == null) {
			throw new EmptyRequestBodyException();
		}
		this.ingestFolderFacade.removeFolder(folderID);
	}

}

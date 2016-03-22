package br.com.gvt.eng.paytv.ingest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiException;
import br.com.gvt.eng.paytv.ingest.exception.rest.ApiExceptionResponse;
import br.com.gvt.eng.paytv.ingest.exception.rest.BadRequestException;
import br.com.gvt.eng.paytv.ingest.exception.rest.EmptyRequestBodyException;
import br.com.gvt.eng.paytv.ingest.facade.IngestAssetFacade;
import br.com.gvt.eng.paytv.ingest.facade.ReadImportFileFacade;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Stateless
@Path("/upload")
@Api(value = "/upload")
public class UploadController {

	@EJB
	private IngestAssetFacade assetFacade;

	@EJB
	private ReadImportFileFacade readImportFileFacade;

	@PermitAll
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	@ApiOperation(value = "Store Upload Files", notes = "Store Upload Files", response = IngestAssetVO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> uploadFile(@ApiParam MultipartFormDataInput input)
			throws IOException, ApiException {
		// Verifica o arquivo enviado
		InputPart uploadFile = input.getFormDataMap().get("uploadedFile")
				.get(0);
		InputStream inputStream = uploadFile.getBody(InputStream.class, null);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream, StandardCharsets.ISO_8859_1));

		// Le o arquivo CVS
		List<IngestAssetVO> assetList = readImportFileFacade.readXLS(br);

		// Nao encontrou nenhuma referencia aos assets
		if (assetList.isEmpty()) {
			throw new EntityNotFoundException();
		}

		return this.assetFacade.save(assetList);
	}

	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/asset")
	@ApiOperation(value = "Store Upload File", notes = "Store Upload File", response = IngestAssetVO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> resendAsset(IngestAssetVO asset)
			throws ApiException {

		if (asset == null) {
			throw new EmptyRequestBodyException();
		}

		List<IngestAssetVO> assetList = new ArrayList<IngestAssetVO>();
		assetList.add(asset);

		return this.assetFacade.save(assetList);
	}

	@PermitAll
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Path("/list")
	@ApiOperation(value = "Store Upload List Files", notes = "Store Upload List Files", response = IngestAssetVO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = BadRequestException.MESSAGE, response = ApiExceptionResponse.class),
			@ApiResponse(code = 404, message = EntityNotFoundException.MESSAGE, response = ApiExceptionResponse.class) })
	public List<IngestAssetVO> resendAsset(List<IngestAssetVO> assetList)
			throws ApiException {

		if (assetList.isEmpty()) {
			throw new EmptyRequestBodyException();
		}

		return this.assetFacade.save(assetList);
	}
}

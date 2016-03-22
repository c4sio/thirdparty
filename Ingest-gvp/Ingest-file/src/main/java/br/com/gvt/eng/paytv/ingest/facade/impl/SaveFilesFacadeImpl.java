package br.com.gvt.eng.paytv.ingest.facade.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.dto.IngestFileDTO;
import br.com.gvt.eng.paytv.ingest.dto.IngestFolderDTO;
import br.com.gvt.eng.paytv.ingest.enums.EnumMediaType;
import br.com.gvt.eng.paytv.ingest.facade.SaveFilesFacade;
import br.com.gvt.eng.paytv.ingest.util.MD5Utils;
import br.com.gvt.eng.paytv.ingest.util.Util;

import com.google.gson.Gson;

public class SaveFilesFacadeImpl implements SaveFilesFacade {

	static Logger logger = Logger
			.getLogger(SaveFilesFacadeImpl.class.getName());

	private HttpClient httpClient;

	@Override
	public boolean saveFiles(List<File> listFiles, String pathFileIn,
			String pathFileOut, String folderRoot) {
		boolean saveOk = false;
		try {
			this.httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost(Util.getURIIngest() + "file");
			postRequest.addHeader("content-type", "application/json");

			IngestFolderDTO dto = new IngestFolderDTO();
			dto.setUrlRootIn(pathFileIn);
			dto.setUrlRootOut(pathFileOut);
			dto.setFolderReference(folderRoot);

			List<IngestFileDTO> listFileDto = new ArrayList<IngestFileDTO>();
			IngestFileDTO fileDto = null;

			for (File file : listFiles) {
				fileDto = new IngestFileDTO();
				fileDto.setName(file.getName());
				fileDto.setFileSize(file.length());

				// Verifica o Checksum
				// fileDto.setChecksum(verifyChecksum(file));
				
				// Alterado para inclusao de valor default
				fileDto.setChecksum("1234abc");

				// Verifica o tipo de arquivo
				fileDto.setMediaType(verifyMediaType(file.getName()));
				listFileDto.add(fileDto);
			}

			dto.setIngestFiles(listFileDto);

			Gson gson = new Gson();
			String data = gson.toJson(dto);
			System.out.println("Atualiza dados arquivos Ingest: " + data);
			logger.info("Atualiza dados arquivos Ingest: " + data);
			postRequest.setEntity(new StringEntity(data, ContentType.create(
					"application/json", "UTF-8")));

			// Executa a acao
			HttpResponse response = httpClient.execute(postRequest);
			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK
					&& response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			saveOk = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar registros na base do Ingest: ", e);
		}
		return saveOk;

	}

	/**
	 * @param name
	 * @return String
	 */
	@SuppressWarnings("unused")
	private String verifyChecksum(File file) {
		String result = null;
		try {
			MD5Utils md5Utils = new MD5Utils();
			result = md5Utils.getMD5Checksum(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao verificar Checksum: ", e);
		}
		return result;
	}

	/**
	 * @param name
	 * @return EnumMediaType
	 */
	private EnumMediaType verifyMediaType(String name) {
		try {
			Pattern pMovie = Pattern.compile(Util.getMovieFiles());
			Matcher movie = pMovie.matcher(name.toLowerCase());
			if (movie.matches()) {
				return EnumMediaType.MOVIE;
			}

			Pattern pImage = Pattern.compile(Util.getImagensFiles());
			Matcher image = pImage.matcher(name.toLowerCase());
			if (image.matches()) {
				return EnumMediaType.POSTER;
			}

			Pattern pSubs = Pattern.compile(Util.getSubtitleFiles());
			Matcher subtitle = pSubs.matcher(name.toLowerCase());
			if (subtitle.matches()) {
				return EnumMediaType.SUBTITLE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao verificar o tipo de midia: ", e);
		}
		return null;
	}
}

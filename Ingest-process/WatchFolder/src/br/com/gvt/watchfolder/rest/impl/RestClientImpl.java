package br.com.gvt.watchfolder.rest.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import br.com.gvt.watchfolder.json.JSONObject;
import br.com.gvt.watchfolder.rest.RestClient;
import br.com.gvt.watchfolder.util.Util;
import br.com.gvt.watchfolder.vo.IpvodIngestStage;
import br.com.gvt.watchfolder.vo.IpvodIngestType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class RestClientImpl implements RestClient {

	static Logger logger = Logger.getLogger(RestClientImpl.class.getName());

	@Override
	public IpvodIngestStage saveIngest(String adicionalInfo, String assetInfo,
			int result, int stage, String ftpPathfile, String assetName) {
		IpvodIngestType ingestType = new IpvodIngestType();
		IpvodIngestStage ingest = new IpvodIngestStage();
		try {
			logger.info("Executando o saveIngest - assetInfo: " + assetInfo);
			ingest.setResult(result);
			ingestType.setId(stage);
			ingest.setAssetInfo(assetInfo);
			ingest.setStageType(ingestType);
			ingest.setAdicionalInfo(adicionalInfo);
			ingest.setPriority(5);
			ingest.setFtpPath(ftpPathfile);
			ingest.setAssetName(assetName);
			// Salva os dados
			ingest = processSaveRequest(ingest);
		} catch (Exception e) {
			logger.error("Erro ao salvar dados Ingest: ", e);
		}
		return ingest;
	}

	/**
	 * Executa a chamada do servico para salvar os dados
	 * 
	 * @param stage
	 */
	private IpvodIngestStage processSaveRequest(IpvodIngestStage stage) {
		IpvodIngestStage ipvodIngestStage = new IpvodIngestStage();
		try {
			logger.info("Executando o processSaveRequest");
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(Util.getUriIngest());
			request.addHeader("content-type", "application/json");

			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();
			String data = gson.toJson(stage);
			System.out.println("Salva dados: " + data);
			logger.info("Salva dados: " + data);

			StringEntity userEntity = new StringEntity(data);
			request.setEntity(userEntity);
			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
				// response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				return ipvodIngestStage;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			String json = sb.toString();
			JSONObject jsonObject = new JSONObject(json);
			ipvodIngestStage = gson.fromJson(jsonObject.toString(),
					IpvodIngestStage.class);

		} catch (IOException e) {
			logger.error("Erro ao executar o processSaveRequest: ", e);
		}
		return ipvodIngestStage;
	}

	@Override
	public void updateStage(String assetInfo, int result, int stage,
			String adicionalInfo, IpvodIngestStage ipvodIngestStage,
			String ftpPathfile, String assetName) {
		try {
			logger.info("Executando o updateStage");
			IpvodIngestType ingestType = new IpvodIngestType();
			ipvodIngestStage.setResult(result);
			ingestType.setId(stage);
			ipvodIngestStage.setAssetInfo(assetInfo);
			ipvodIngestStage.setStageType(ingestType);
			ipvodIngestStage.setAdicionalInfo(adicionalInfo);
			ipvodIngestStage.setPriority(5);
			ipvodIngestStage.setFtpPath(ftpPathfile);
			ipvodIngestStage.setAssetName(assetName);
			// Atualiza os dados
			processUpdateRequest(ipvodIngestStage);
		} catch (Exception e) {
			logger.error("Erro ao atualizar dados Ingest: ", e);
		}
	}

	/**
	 * Executa a chamada do servico para atualizar os dados
	 * 
	 * @param stage
	 */
	private void processUpdateRequest(IpvodIngestStage stage) {
		try {
			logger.info("Executando o processUpdateRequest - IngestID: "
					+ stage.getId());
			HttpClient client = HttpClientBuilder.create().build();
			HttpPut request = new HttpPut(Util.getUriIngest());
			request.addHeader("content-type", "application/json");

			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();

			String data = gson.toJson(stage);
			System.out.println("Dados Update: " + data);
			logger.info("Dados Update: " + data);

			StringEntity userEntity = new StringEntity(data);
			request.setEntity(userEntity);
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			logger.error("Erro ao executar o processUpdateRequest: ", e);
		}
	}

	@Override
	public void delete(Long ingestId) {
		try {
			logger.info("Deletando registro Ingest - ID: " + ingestId);

			HttpClient client = HttpClientBuilder.create().build();
			HttpDelete deleteRequest = new HttpDelete(Util.getUriIngest()
					+ "/delete/" + ingestId);
			deleteRequest.addHeader("Content-Type", "application/json");

			// Executa a acao
			HttpResponse response = client.execute(deleteRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
				// response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			logger.error("Erro ao deletar dados do Ingest na base: ", e);
		}
	}

	@Override
	public IpvodIngestStage findDataWhitErrorByAssetInfo(String assetInfo) {
		IpvodIngestStage ingest = new IpvodIngestStage();
		try {
			logger.info("Executando a busca de registro com erro pelo assetInfo: "
					+ assetInfo);
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getUriIngest() + "/"
					+ assetInfo + "/assetInfo");
			getRequest.addHeader("content-type", "application/json");
			// Executa a acao
			HttpResponse response = client.execute(getRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				// response.getEntity().getContent().close();
				logger.info("Nao encontrou nenhum registro na base ["
						+ assetInfo + "]");
				return ingest;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			String json = sb.toString();
			JSONObject jsonObject = new JSONObject(json);
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();
			ingest = gson.fromJson(jsonObject.toString(),
					IpvodIngestStage.class);
		} catch (Exception e) {
			logger.error(
					"Erro ao buscar dados Ingest com error por assetInfo: ", e);
		}
		return ingest;
	}

	@Override
	public IpvodIngestStage findDataByFilePath(String filePath) {
		IpvodIngestStage ingest = new IpvodIngestStage();
		try {
			logger.info("Executando a busca de registro por filePath: "
					+ filePath);
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getUriIngest() + filePath
					+ "/pathfile");
			getRequest.addHeader("content-type", "application/json");
			// Executa a acao
			HttpResponse response = client.execute(getRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.info("Nao encontrou nenhum registro na base ["
						+ filePath + "]");
				return ingest;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			String json = sb.toString();
			JSONObject jsonObject = new JSONObject(json);
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();
			ingest = gson.fromJson(jsonObject.toString(),
					IpvodIngestStage.class);
		} catch (Exception e) {
			logger.error("Erro ao buscar dados Ingest por filePath: ", e);
		}
		return ingest;
	}

	@Override
	public IpvodIngestStage findDataByAssetInfo(String assetInfo) {
		IpvodIngestStage ingest = new IpvodIngestStage();
		try {
			logger.info("Executando a busca de registro por assetInfo: "
					+ assetInfo);
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getUriIngest() + "/find/"
					+ assetInfo);
			getRequest.addHeader("content-type", "application/json");
			// Executa a acao
			HttpResponse response = client.execute(getRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.info("Nao encontrou nenhum registro na base ["
						+ assetInfo + "]");
				return ingest;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			String json = sb.toString();
			JSONObject jsonObject = new JSONObject(json);
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();
			ingest = gson.fromJson(jsonObject.toString(),
					IpvodIngestStage.class);
		} catch (Exception e) {
			logger.error("Erro ao buscar dados Ingest por assetInfo: ", e);
		}
		return ingest;
	}

	/**
	 * Adapater para data - Json
	 */
	private static class DateTypeAdapter implements JsonSerializer<Date>,
			JsonDeserializer<Date> {
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return typeOfT == Date.class ? new Date(json.getAsLong())
					: new java.sql.Timestamp(json.getAsLong());
		}

		public JsonElement serialize(Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}
	}
}
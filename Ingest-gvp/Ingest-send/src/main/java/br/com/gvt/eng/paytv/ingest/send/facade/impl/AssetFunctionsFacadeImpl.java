package br.com.gvt.eng.paytv.ingest.send.facade.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.send.facade.AssetFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.send.json.JSONArray;
import br.com.gvt.eng.paytv.ingest.send.json.JSONObject;
import br.com.gvt.eng.paytv.ingest.send.utils.Util;
import br.com.gvt.eng.paytv.ingest.send.vo.IngestAssetVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class AssetFunctionsFacadeImpl implements AssetFunctionsFacade {

	Logger logger = Logger.getLogger(AssetFunctionsFacadeImpl.class.getName());

	private HttpClient httpClient;

	@Override
	public List<IngestAssetVO> findByStatus(String status) {
		List<IngestAssetVO> listAssets = new ArrayList<IngestAssetVO>();
		try {
			this.httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getURIIngest()
					+ "import/status/" + status);
			getRequest.addHeader("Content-Type", "application/json");

			// Executa a acao
			HttpResponse response = httpClient.execute(getRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				return listAssets;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

			String json = sb.toString();
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject = null;

			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();

			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				listAssets.add(gson.fromJson(jsonObject.toString(),
						IngestAssetVO.class));
			}
		} catch (Exception e) {
			logger.error("Erro buscar Assets - " + e);
		}
		return listAssets;
	}

	@Override
	public IngestAssetVO update(IngestAssetVO ingestAssetVO) {
		IngestAssetVO returnVO = new IngestAssetVO();
		try {
			this.httpClient = HttpClientBuilder.create().build();
			HttpPut putRequest = new HttpPut(Util.getURIIngest() + "asset");
			putRequest.addHeader("Content-Type", "application/json");

			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();

			String data = gson.toJson(ingestAssetVO);
			System.out.println("Salva Dados: " + data);
			logger.info("Salva Dados: " + data);
			putRequest.setEntity(new StringEntity(data, ContentType.create(
					"application/json", "UTF-8")));

			// Executa a acao
			HttpResponse response = httpClient.execute(putRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
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
			returnVO = gson
					.fromJson(jsonObject.toString(), IngestAssetVO.class);
		} catch (Exception e) {
			logger.error("Erro ao atualizar status Asset - "
					+ ingestAssetVO.getOriginalTitle() + " - " + e);
		}
		return returnVO;
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

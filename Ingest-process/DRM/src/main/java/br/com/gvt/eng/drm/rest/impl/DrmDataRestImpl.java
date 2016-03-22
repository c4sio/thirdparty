package br.com.gvt.eng.drm.rest.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import br.com.gvt.eng.drm.EncryptorDrm;
import br.com.gvt.eng.drm.RegisterDrm;
import br.com.gvt.eng.drm.impl.EncryptorDrmImpl;
import br.com.gvt.eng.drm.impl.RegisterDrmImpl;
import br.com.gvt.eng.drm.json.JSONArray;
import br.com.gvt.eng.drm.json.JSONObject;
import br.com.gvt.eng.drm.rest.DrmDataRest;
import br.com.gvt.eng.drm.util.Util;
import br.com.gvt.eng.drm.vo.IpvodDrmData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut;

public class DrmDataRestImpl implements DrmDataRest {

	static Logger logger = Logger.getLogger(DrmDataRestImpl.class.getName());

	@Override
	public void saveDrmData(IpvodDrmData ipvodDrmData) {
		try {
			logger.info("Salvando dados de arquivo "
					+ ipvodDrmData.getNameFile());

			RegisterDrm registerDrm = new RegisterDrmImpl();
			EncryptorDrm encryptorDrm = new EncryptorDrmImpl();

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPut putRequest = new HttpPut(Util.getURIIpvod() + "drm/save");
			putRequest.addHeader("Content-Type", "application/json");

			Gson gson = new Gson();
			String data = gson.toJson(ipvodDrmData);
			System.out.println("Salva Dados: " + data);
			logger.info("Salva Dados: " + data);
			putRequest.setEntity(new StringEntity(data, "application/json",
					"UTF-8"));

			// Executa a acao
			HttpResponse response = httpClient.execute(putRequest);

			// Verifica o retorno
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_CREATED) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());

				// Verifica os dados do processo
				HeartbeatOut heartbeatOut = encryptorDrm
						.checkStatusEncryptor(ipvodDrmData.getCookieDrm());

				// Se encontrou os dados do processo
				if (heartbeatOut != null) {
					// Remove processo do DRM
					encryptorDrm
							.AbortEncryption(heartbeatOut.getClientCookie()
									.toString(), heartbeatOut.getJobNumber()
									.toString());
				}

				// Remove o Cookie, caso ocorra um erro ao salvar o registro
				registerDrm.unRegisterClientByCookie(ipvodDrmData
						.getCookieDrm());

				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao salvar dados do DRM na base: ", e);
		}
	}

	@Override
	public IpvodDrmData update(IpvodDrmData ipvodDrmData) {
		IpvodDrmData drmData = new IpvodDrmData();
		try {
			logger.info("Atualizando dados de arquivo "
					+ ipvodDrmData.getNameFile());

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPut putRequest = new HttpPut(Util.getURIIpvod() + "drm/update");
			putRequest.addHeader("Content-Type", "application/json");

			Gson gson = new Gson();
			String data = gson.toJson(ipvodDrmData);
			System.out.println("Atualiza Dados: " + data);
			logger.info("Atualiza Dados: " + data);
			putRequest.setEntity(new StringEntity(data, "application/json",
					"UTF-8"));

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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao atualizar dados do DRM na base: ", e);
		}
		return drmData;
	}

	@Override
	public void delete(Long drmId) {
		try {
			logger.info("Deletando registro DRM - ID: " + drmId);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpDelete deleteRequest = new HttpDelete(Util.getURIIpvod()
					+ "drm/delete/" + drmId);
			deleteRequest.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(deleteRequest);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_NO_CONTENT) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao deletar dados do DRM na base: ", e);
		}
	}

	@Override
	public List<IpvodDrmData> findAllLessCompleted() {
		List<IpvodDrmData> listDrmData = new ArrayList<IpvodDrmData>();
		try {
			logger.info("Buscar registros do DRM na base.");

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getURIIpvod() + "drm/execute");
			getRequest.addHeader("Content-Type", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				return listDrmData;
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
				listDrmData.add(gson.fromJson(jsonObject.toString(),
						IpvodDrmData.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao buscar dados do DRM na base: ", e);
		}
		return listDrmData;
	}

	@Override
	public IpvodDrmData find(Long entityID) {
		IpvodDrmData ipvodDrmData = new IpvodDrmData();

		try {
			logger.info("Buscar registro do DRM na base - ID: " + entityID);

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getURIIpvod() + "drm/"
					+ entityID);
			getRequest.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
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
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(
					Date.class, new DateTypeAdapter()).create();
			ipvodDrmData = gson.fromJson(jsonObject.toString(),
					IpvodDrmData.class);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao buscar registro do DRM na base: ", e);
		}
		return ipvodDrmData;
	}

	@Override
	public boolean checkEndOfProcess(Long ingestID) {
		List<IpvodDrmData> listDrmData = new ArrayList<IpvodDrmData>();
		try {
			logger.info("Buscar registro do Drm na base - ingestID: "
					+ ingestID);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet getRequest = new HttpGet(Util.getURIIpvod() + "drm/"
					+ ingestID + "/ingest");
			getRequest.addHeader("Content-Type", "application/json");
			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				response.getEntity().getContent().close();
				logger.error("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				return false;
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
				listDrmData.add(gson.fromJson(jsonObject.toString(),
						IpvodDrmData.class));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao buscar registro do DRM na base: ", e);
			return false;
		}
		return verifyAllRegisters(listDrmData);
	}

	/**
	 * @param listDrmData
	 * @return boolean
	 */
	private boolean verifyAllRegisters(List<IpvodDrmData> listDrmData) {
		boolean finished = true;
		try {
			logger.info("Verifica se todos os registros com mesmo IngestId foram executados com sucesso.");
			for (IpvodDrmData ipvodDrmData : listDrmData) {
				if (!ipvodDrmData.getStatusDrm().equalsIgnoreCase("Completed")) {
					finished = false;
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao verificar final do processo arquivos DRM: ",
					e);
		}
		return finished;
	}

	/**
	 * @author GVT
	 * 
	 */
	private static class DateTypeAdapter implements JsonSerializer<Date>,
			JsonDeserializer<Date> {
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return typeOfT == Date.class ? new Date(json.getAsLong())
					: new java.sql.Date(json.getAsLong());
		}

		public JsonElement serialize(Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			return new JsonPrimitive(src.getTime());
		}
	}

}
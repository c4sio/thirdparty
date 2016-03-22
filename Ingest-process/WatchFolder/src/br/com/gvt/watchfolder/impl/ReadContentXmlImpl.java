package br.com.gvt.watchfolder.impl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import br.com.gvt.watchfolder.ReadContentXml;
import br.com.gvt.watchfolder.json.JSONArray;
import br.com.gvt.watchfolder.json.JSONObject;
import br.com.gvt.watchfolder.json.XML;
import br.com.gvt.watchfolder.util.MD5Utils;
import br.com.gvt.watchfolder.util.Util;

public class ReadContentXmlImpl implements ReadContentXml {

	static Logger logger = Logger.getLogger(ReadContentXmlImpl.class.getName());

	@Override
	public JSONArray readContent(String xmlValues) {
		JSONArray jsonArray = new JSONArray();
		try {
			logger.info("Lendo o conteudo do XML");
			// Seta os objetos Json
			JSONObject xmlJSONObj = XML.toJSONObject(new String(xmlValues
					.replaceAll("&", "&amp;")));
			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			JSONObject object = new JSONObject(jsonPrettyPrintString);
			JSONObject adiObject = object.getJSONObject("ADI");
			JSONObject assetObject = adiObject.getJSONObject("Asset");
			jsonArray = assetObject.getJSONArray("Asset");
		} catch (Exception e) {
			logger.error("Erro ao ler conteudo do XML - " + e);
		}
		return jsonArray;
	}

	@Override
	public String getAssetInfoByContent(String xmlValues) {
		String assetInfo = null;
		try {
			logger.info("Lendo AssetInfo no XML");
			// Seta os objetos Json
			JSONObject xmlJSONObj = XML.toJSONObject(new String(xmlValues
					.replaceAll("&", "&amp;")));
			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			JSONObject object = new JSONObject(jsonPrettyPrintString);
			JSONObject adiObject = object.getJSONObject("ADI");
			JSONObject medataData = adiObject.getJSONObject("Metadata");
			JSONObject AMS = medataData.getJSONObject("AMS");
			assetInfo = AMS.getString("Asset_ID").trim();
			// assetInfo = AMS.getString("Asset_Name")
			// .replaceAll("[^a-zA-ZZ-Z0-9_]", "").replaceAll(" ", "_")
			// .trim();
		} catch (Exception e) {
			logger.error("Erro ao busca AssetInfo do conteudo do XML - " + e);
		}
		return assetInfo;
	}

	@Override
	public String getAssetTileByContent(String xmlValues) {
		String assetTitle = null;
		String assetProvider = null;
		String value = null;
		try {
			logger.info("Lendo Asset Title no XML");
			// Seta os objetos Json
			JSONObject xmlJSONObj = XML.toJSONObject(new String(xmlValues
					.replaceAll("&", "&amp;")));
			String jsonPrettyPrintString = xmlJSONObj.toString(4);
			// Busca nome Provider
			JSONObject object = new JSONObject(jsonPrettyPrintString);
			JSONObject adiObject = object.getJSONObject("ADI");
			JSONObject medataData = adiObject.getJSONObject("Metadata");
			JSONObject AMS = medataData.getJSONObject("AMS");
			// Seta o nome do Provider
			assetProvider = AMS.getString("Provider").trim();

			// Busca nome Movie
			JSONObject assetObject = adiObject.getJSONObject("Asset");
			JSONObject metadataObject = assetObject.getJSONObject("Metadata");
			JSONArray jsonArrayAppData = metadataObject
					.getJSONArray("App_Data");

			String name = null;
			for (int j = 0; j < jsonArrayAppData.length(); j++) {
				JSONObject appDataDetailsJson = jsonArrayAppData
						.getJSONObject(j);
				name = appDataDetailsJson.get("Name").toString();
				if (name.equalsIgnoreCase("Title")) {
					// Seta o nome do Movie
					assetTitle = appDataDetailsJson.get("Value").toString();
				}
			}

			// Gera o Asset Title - dado mostrado apenas com erro MD5
			value = assetProvider + " - " + assetTitle;
		} catch (Exception e) {
			logger.error("Erro ao busca Asset Title do conteudo do XML - " + e);
		}
		return value;
	}

	@Override
	public boolean validateMD5(String pathfile, JSONArray objectValues) {
		boolean md5IsValid = false;
		int count = 0;
		try {
			logger.info("Lendo AppData no XML");
			String name = null;
			String xmlMD5Info = null;
			MD5Utils md5Utils = null;
			String MD5File = null;

			// Verifica os registros
			for (int i = 0; i < objectValues.length(); i++) {
				JSONObject appDataJson = objectValues.getJSONObject(i);
				JSONObject metadataObject = appDataJson
						.getJSONObject("Metadata");
				JSONObject contentObject = appDataJson.getJSONObject("Content");
				JSONArray jsonArrayAppData = metadataObject
						.getJSONArray("App_Data");
				for (int j = 0; j < jsonArrayAppData.length(); j++) {
					JSONObject appDataDetailsJson = jsonArrayAppData
							.getJSONObject(j);
					name = appDataDetailsJson.get("Name").toString();
					if (name.equalsIgnoreCase("Content_CheckSum")) {
						xmlMD5Info = appDataDetailsJson.get("Value").toString();
						md5Utils = new MD5Utils();
						MD5File = md5Utils.getMD5Checksum(pathfile
								+ contentObject.get("Value"));
						logger.info("File: " + MD5File + " XMLINFO: "
								+ xmlMD5Info);
						System.out.println("File: " + MD5File + " XMLINFO: "
								+ xmlMD5Info.toLowerCase());
						if (!xmlMD5Info.toLowerCase().equalsIgnoreCase(MD5File)) {
							count++;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao busca AppData do conteudo do XML - " + e);
		}

		// Se não encontrou nenhum erro nos dados valida o MD5
		if (count == 0) {
			md5IsValid = true;
		}

		return md5IsValid;
	}

	@Override
	public ArrayList<String> findNameFiles(JSONArray objectValues) {
		ArrayList<String> listFiles = new ArrayList<String>();
		try {
			logger.info("Verificando nome dos arquivos no XML");
			String fileName = null;
			Pattern p = Pattern.compile(Util.getWFFiles());
			for (int i = 0; i < objectValues.length(); i++) {
				JSONObject appDataJson = objectValues.getJSONObject(i);
				JSONObject contentObject = appDataJson.getJSONObject("Content");
				fileName = contentObject.get("Value").toString().toLowerCase();
				Matcher m = p.matcher(fileName.toLowerCase());
				if (m.matches()) {
					logger.info("Nome do arquivo para validacao: "
							+ contentObject.get("Value").toString());
					System.out.println("Nome do arquivo para validacao: "
							+ contentObject.get("Value").toString());
					listFiles.add(fileName);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao verificar nome dos arquivos no XML - " + e);
		}
		return listFiles;
	}
}
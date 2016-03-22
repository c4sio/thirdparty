package com.gvt.eng.ipvod.proccess.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.gvt.eng.vod.model.IpvodAsset;
import br.com.gvt.eng.vod.model.IpvodAssetType;
import br.com.gvt.eng.vod.model.IpvodCategory;
import br.com.gvt.eng.vod.model.IpvodMediaAsset;
import br.com.gvt.eng.vod.model.IpvodMediaType;

import com.google.gson.Gson;
import com.gvt.eng.ipvod.proccess.dao.AssetTypeDAO;
import com.gvt.eng.ipvod.proccess.dao.CategoryAssetDAO;
import com.gvt.eng.ipvod.proccess.dao.ContentProviderDAO;
import com.gvt.eng.ipvod.proccess.dao.LanguageDAO;
import com.gvt.eng.ipvod.proccess.dao.MediaTypeDAO;
import com.gvt.eng.ipvod.proccess.dao.RatingDAO;
import com.gvt.eng.ipvod.proccess.file.FileFunctions;
import com.gvt.eng.ipvod.proccess.json.util.JSONArray;
import com.gvt.eng.ipvod.proccess.json.util.JSONObject;
import com.gvt.eng.ipvod.proccess.json.util.XML;
import com.gvt.eng.ipvod.proccess.util.AssetTypeEnum;
import com.gvt.eng.ipvod.proccess.util.MediaTypeEnum;
import com.gvt.eng.ipvod.proccess.util.Util;
import com.gvt.eng.ipvod.proccess.vo.Ams;
import com.gvt.eng.ipvod.proccess.vo.Asset;
import com.gvt.eng.ipvod.proccess.vo.Content;
import com.gvt.eng.ipvod.proccess.vo.Metadata;
import com.gvt.eng.ipvod.proccess.vo.MetadataPackage;
import com.gvt.eng.ipvod.proccess.vo.PackAsset;

public class AssetConverter {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	private ApplicationContext ctx = null;
	Logger logger = Logger.getLogger(AssetConverter.class);

	public AssetConverter() {
		try {
			ctx = new ClassPathXmlApplicationContext(Util.getSpringConfig());
			Util.setError(new ArrayList<String>());
		} catch (Exception e) {
			logger.error("Erro ao buscar o contexto: " + e.getMessage());
		}
	}

	/**
	 * @param xml
	 * @param pack
	 * @throws Exception
	 */
	public void jsonToAssetPack(StringBuffer xml, PackAsset pack)
			throws Exception {
		logger.info("Entrou no metodo jsonToAssetPack!");
		Gson converter = new Gson();
		JSONObject xmlJSONObj = XML.toJSONObject(new String(xml).replaceAll(
				"&", "&amp;"));
		String jsonPrettyPrintString = xmlJSONObj
				.toString(PRETTY_PRINT_INDENT_FACTOR);
		JSONObject object = new JSONObject(jsonPrettyPrintString);

		// Se o arquivo estiver vazio gera um erro
		if (object.length() == 0) {
			logger.info("Atencao XML vazio!");
			Util.getError().add("Atencao XML vazio!");
			throw new NullPointerException();
		}

		List<Asset> assets = new ArrayList<Asset>();
		Asset assetObject = null;

		try {
			// GET ROOT TAG
			logger.info("Get ROOT TAG.");
			JSONObject adiObject = object.getJSONObject("ADI");

			// GET ASSET (title/cover/movie/preview)
			logger.info("Get ASSET (title/cover/movie/preview).");
			JSONObject asset = adiObject.getJSONObject("Asset");
			JSONObject assetsMetadata = asset.getJSONObject("Metadata");
			Metadata assetsMetadataObject = converter.fromJson(
					assetsMetadata.toString(), Metadata.class);
			pack.setAssetsMetadata(assetsMetadataObject);

			// GET ASSET METADATA (provider)
			logger.info("Get ASSET METADATA (provider).");
			JSONArray jsonArray = asset.getJSONArray("Asset");

			// GET PACKAGE METADATA
			logger.info("Get PACKAGE METADATA.");
			JSONObject adiMetadata = adiObject.getJSONObject("Metadata");
			MetadataPackage packMetadata = converter.fromJson(
					adiMetadata.toString(), MetadataPackage.class);
			pack.setMetadata(packMetadata);

			for (int i = 0; i < jsonArray.length(); i++) {
				assetObject = new Asset();
				JSONObject appDataJson = jsonArray.getJSONObject(i);
				JSONObject metadataObject = appDataJson
						.getJSONObject("Metadata");
				JSONObject contentObject = appDataJson.getJSONObject("Content");
				Metadata assetMetadata = converter.fromJson(
						metadataObject.toString(), Metadata.class);
				Content content = converter.fromJson(contentObject.toString(),
						Content.class);
				assetObject.setMetadata(assetMetadata);
				assetObject.setContent(content);
				assets.add(assetObject);
			}
		} catch (Exception e) {
			logger.error("Erro ao ler dados package - " + e);
		}
		pack.setAsset(assets);
	}

	/**
	 * @param pack
	 * @param file
	 * @return
	 */
	public IpvodAsset toIpvodAsset(PackAsset pack, File file) {
		logger.info("Entrou no metodo toIpvodAsset!");
		IpvodAsset ipvodAsset = new IpvodAsset();
		ipvodAsset.setCreationDate(new Date());
		ipvodAsset.setIsRevised(false);
		List<Asset> assets = pack.getAsset();
		MetadataPackage metadataPackage = pack.getMetadata();
		Ams amsPackage = metadataPackage.getAMS();

		try {
			if (amsPackage.getProduct() == null
					|| amsPackage.getProduct().equals("")) {
				ipvodAsset.setProduct("ERROR");
				logger.info("Atencao XML sem PRODUCT!");
				Util.getError().add("Atencao XML sem PRODUCT!");
			} else {
				ipvodAsset.setProduct(amsPackage.getProduct());
			}

			// Busca dados provider
			logger.info("Busca dados provider - " + amsPackage.getProvider()
					+ " - " + amsPackage.getProvider_ID() + "!");

			ipvodAsset.setIpvodContentProvider(ctx.getBean(
					ContentProviderDAO.class).findContendProviderByParam(
					amsPackage.getProvider(), amsPackage.getProvider_ID()));

			// Erro ao encontrar o Provider
			if (ipvodAsset.getIpvodContentProvider() == null) {
				logger.info("Erro ao buscar dados provider - Provider: "
						+ amsPackage.getProvider() + " - ProviderId: "
						+ amsPackage.getProvider_ID() + "!");
				Util.getError().add(
						"Missing value of Provider in @IpvodContentProvider - Provider: "
								+ amsPackage.getProvider() + " ProviderId: "
								+ amsPackage.getProvider_ID());
			}

			// Setando o Asset Info - Package
			// ipvodAsset.setAssetInfo(metadataPackage.getAMS().getAsset_Name()
			// .replaceAll("[^a-zA-ZZ-Z0-9_]", "").replaceAll(" ", "_")
			// .trim());
			ipvodAsset.setAssetInfo(metadataPackage.getAMS().getAsset_ID());

			// Popula ipvodAsset com dados xml
			extractInfoFromHash(ipvodAsset, pack.getAssetsMetadata()
					.getApp_Data());

			List<IpvodMediaAsset> medias = new ArrayList<IpvodMediaAsset>();

			for (Asset asset : assets) {
				Ams ams = asset.getMetadata().getAMS();
				// Content content = asset.getContent();
				List<HashMap<String, String>> appDataProperties = asset
						.getMetadata().getApp_Data();

				// Inclui o valor com erro na lista de erros
				if (ams.getAsset_Class() == null
						|| ams.getAsset_Class().isEmpty())
					Util.getError().add("Xml sem dados de Media");

				// Atualizar as informacoes do asset com propriedades do movie
				if (ams.getAsset_Class().equalsIgnoreCase("movie")) {
					logger.info("Busca dados movie");
					extractInfoFromHash(ipvodAsset, appDataProperties);
				}

				IpvodMediaAsset media = new IpvodMediaAsset();
				// 1 Poster 2 Trailer 3 Movie 4 Icon 5 Subtitle
				logger.info("Busca dados mediaType - "
						+ Enum.valueOf(MediaTypeEnum.class,
								ams.getAsset_Class().toString()).getDescricao()
						+ "!");
				IpvodMediaType mediaType = new IpvodMediaType();
				mediaType = ctx.getBean(MediaTypeDAO.class)
						.findMediaTypeByName(
								Enum.valueOf(MediaTypeEnum.class,
										ams.getAsset_Class().toString())
										.getDescricao());

				media.setUrl(asset.getContent().getValue());
				media.setIpvodMediaType(mediaType);
				media.setIpvodAsset(ipvodAsset);
				medias.add(media);
			}

			// Busca a legenda e inclui na @IpvodMediaAsset
			FileFunctions fileFunctions = new FileFunctions();
			List<File> filtered = new ArrayList<File>();
			filtered = fileFunctions.getFilesFromPathByExtension(
					file.getParent(), "srt");
			if (!filtered.isEmpty()) {
				IpvodMediaAsset media = null;
				IpvodMediaType mediaType = null;
				for (File valueFile : filtered) {
					media = new IpvodMediaAsset();
					mediaType = new IpvodMediaType();
					mediaType = ctx.getBean(MediaTypeDAO.class)
							.findMediaTypeByName(
									Enum.valueOf(MediaTypeEnum.class,
											"subtitle").getDescricao());
					media.setUrl(valueFile.getName());
					media.setIpvodMediaType(mediaType);
					media.setIpvodAsset(ipvodAsset);
				}
				medias.add(media);
			}

			ipvodAsset.setIpvodMediaAssets(medias);
		} catch (Exception e) {
			logger.error("Erro ao ler dados media - " + e);
		}
		return ipvodAsset;
	}

	/**
	 * @param ipvodAsset
	 * @param data
	 */
	@SuppressWarnings("unchecked")
	private void extractInfoFromHash(IpvodAsset ipvodAsset,
			List<HashMap<String, String>> data) {

		logger.info("Entrou no metodo extractInfoFromHash!");
		IpvodCategory category = null;
		IpvodAssetType assetType = null;
		HashMap<String, String> nameValueHash = createNameValueHash(data);

		// Inclui no log os campos que estao sendo lidos
		for (HashMap<String, String> hashMap : data) {
			logger.info(hashMap.values());
		}

		// Carrega a lista de campos obrigatorios
		List<String> requiredFields = (List<String>) ctx
				.getBean("requiredFields");

		// Valida se existe algum campo obrigatorio e vazio
		for (String s : requiredFields) {
			if (nameValueHash.containsKey(s)
					&& Util.isEmptyOrNull(nameValueHash.get(s))) {
				Util.getError().add("Missing value of " + s);
			}
		}

		// Imprime o campo vazio
		logger.info("Error size: " + Util.getError().size());
		for (String string : Util.getError()) {
			logger.info(string.toString());
		}

		try {
			if (!Util.isEmptyOrNull(nameValueHash.get("Rating"))) {
				// Verifica se o filme e para Adultos
				boolean isAdult = nameValueHash.get("Rating").equalsIgnoreCase(
						"X") ? true : false;
				ipvodAsset.setRating(ctx.getBean(RatingDAO.class)
						.findRating(
								nameValueHash.get("Rating").equalsIgnoreCase(
										"X") ? "18" : nameValueHash
										.get("Rating"), isAdult));
			}

			if (!Util.isEmptyOrNull(nameValueHash.get("Languages")))
				ipvodAsset.setLanguages(ctx.getBean(LanguageDAO.class)
						.setCodeLanguageByName(
								nameValueHash.get("Languages").toUpperCase()));

			if (!Util.isEmptyOrNull(nameValueHash.get("Subtitle_Languages")))
				ipvodAsset.setSubtitles(ctx.getBean(LanguageDAO.class)
						.setCodeLanguageByName(
								nameValueHash.get("Subtitle_Languages")
										.toUpperCase()));

			if (!Util.isEmptyOrNull(nameValueHash.get("Dubbed_Languages")))
				ipvodAsset.setDubbedLanguage(ctx.getBean(LanguageDAO.class)
						.setCodeLanguageByName(
								nameValueHash.get("Dubbed_Languages")
										.toUpperCase()));

			if (!Util.isEmptyOrNull(nameValueHash.get("Screen_Format")))
				ipvodAsset.setScreenFormat(nameValueHash.get("Screen_Format"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Country_of_Origin")))
				ipvodAsset.setCountry(nameValueHash.get("Country_of_Origin"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Country_Of_Origin")))
				ipvodAsset.setCountry(nameValueHash.get("Country_Of_Origin"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Summary_Medium")))
				ipvodAsset.setDescription(nameValueHash.get("Summary_Medium"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Director_Display")))
				ipvodAsset.setDirector(nameValueHash.get("Director_Display"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Actors_Display")))
				ipvodAsset.setActors(nameValueHash.get("Actors_Display")
						.replace(";", ","));

			if (!Util.isEmptyOrNull(nameValueHash.get("Episode")))
				ipvodAsset.setEpisode(nameValueHash.get("Episode"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Episode_Name")))
				ipvodAsset.setEpisodeName(nameValueHash.get("Episode_Name"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Suggested_Price")))
				ipvodAsset.setPrice(Double.parseDouble(nameValueHash
						.get("Suggested_Price")));

			if (!Util
					.isEmptyOrNull(nameValueHash.get("Licensing_Window_Start")))
				ipvodAsset.setLicenseWindowStart(Util
						.stringToData(nameValueHash
								.get("Licensing_Window_Start")));

			if (!Util.isEmptyOrNull(nameValueHash.get("Licensing_Window_End")))
				ipvodAsset.setLicenseWindowEnd(Util.stringToData(nameValueHash
						.get("Licensing_Window_End")));

			if (!Util.isEmptyOrNull(nameValueHash.get("Title")))
				ipvodAsset.setTitle(nameValueHash.get("Title"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Run_Time")))
				ipvodAsset.setTotalTime(Util.changeHourToSecond(nameValueHash
						.get("Run_Time")));

			if (!Util.isEmptyOrNull(nameValueHash.get("Genre"))) {
				category = new IpvodCategory();
				category.setCategoryId(ctx.getBean(CategoryAssetDAO.class)
						.findCategoryByName(nameValueHash.get("Genre")));
				ipvodAsset.setIpvodCategory1(category);
			}

			if (!Util.isEmptyOrNull(nameValueHash.get("Year")))
				ipvodAsset.setReleaseYear(Integer.parseInt(nameValueHash
						.get("Year")));

			if (!Util.isEmptyOrNull(nameValueHash.get("Billing_ID")))
				ipvodAsset.setBillingID(nameValueHash.get("Billing_ID").trim());

			if (!Util.isEmptyOrNull(nameValueHash.get("Screen_Format")))
				ipvodAsset.setScreenFormat(nameValueHash.get("Screen_Format"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Audio_Type")))
				ipvodAsset.setAudioType(nameValueHash.get("Audio_Type"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Type"))) {
				String type = nameValueHash.get("Type");
				if (!type.equalsIgnoreCase("title")
						&& !type.equalsIgnoreCase("poster")) {
					assetType = new IpvodAssetType();
					assetType = ctx.getBean(AssetTypeDAO.class)
							.findAssetTypeByName(
									Enum.valueOf(AssetTypeEnum.class, type)
											.getDescricao());
					ipvodAsset.setIpvodAssetType(assetType);
				}
			}

			if (!Util
					.isEmptyOrNull(nameValueHash.get("Viewing_Can_Be_Resumed"))) {
				ipvodAsset.setCanResume(nameValueHash.get(
						"Viewing_Can_Be_Resumed").equalsIgnoreCase("Y") ? true
						: false);
			}

			if (!Util.isEmptyOrNull(nameValueHash.get("HDContent"))) {
				ipvodAsset.setHD(nameValueHash.get("HDContent")
						.equalsIgnoreCase("Y") ? true : false);
			}

			if (!Util.isEmptyOrNull(nameValueHash.get("Content_FileSize")))
				ipvodAsset.setFileSize(nameValueHash.get("Content_FileSize"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Content_CheckSum")))
				ipvodAsset.setChecksum(nameValueHash.get("Content_CheckSum"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Bit_Rate")))
				ipvodAsset.setBitrate(nameValueHash.get("Bit_Rate"));

			if (!Util.isEmptyOrNull(nameValueHash.get("Title_Brief")))
				ipvodAsset
						.setTitleAlternative(nameValueHash.get("Title_Brief"));
		} catch (Exception e) {
			e.printStackTrace();
			Util.getError().add("Missing value of " + e);
			logger.error("Erro ao ler dados de Asset - " + e);
		}
	}

	/**
	 * @param data
	 * @return HashMap
	 */
	private HashMap<String, String> createNameValueHash(
			List<HashMap<String, String>> data) {
		HashMap<String, String> nameValueHash = new HashMap<String, String>();
		String name = null;
		for (HashMap<String, String> hashMap : data) {
			name = hashMap.get("Name");
			if (!nameValueHash.containsKey(name)) {
				nameValueHash.put(name, hashMap.get("Value").trim());
			} else {
				nameValueHash.put(name, nameValueHash.get(name) + ","
						+ hashMap.get("Value").trim());
			}
		}
		return nameValueHash;
	}
}
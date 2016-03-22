package br.com.gvt.eng.paytv.ingest.facade.imp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.gvt.eng.paytv.ingest.facade.CreateAssetXmlDomFacade;
import br.com.gvt.eng.paytv.ingest.model.IngestAsset;
import br.com.gvt.eng.paytv.ingest.model.IngestFile;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumMediaType;
import br.com.gvt.eng.paytv.ingest.utils.ParserUtils;
import br.com.gvt.eng.paytv.ingest.utils.Util;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Stateless
public class CreateAssetXmlDomFacadeImpl implements CreateAssetXmlDomFacade {

	public static final String PACKAGE = "_00_Package";
	public static final String METADATA = "_01_Metadata";
	public static final String MOVIE = "_02_Movie";
	public static final String POSTER = "_04_Poster";
	public static final String SUBTITLE = "_05_Subtitle";

	@Override
	public boolean writeXML(File outFile, IngestAssetVO ingestAssetVO) {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		boolean isCreate = false;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();

			// Parse IngestAssetVO to IngestAsset
			IngestAsset ingestAsset = ParserUtils.parseIngest(ingestAssetVO);

			// Create XML
			createXML(doc, ingestAsset);

			// output DOM XML to console
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount",
					Integer.toString(3));
			
			DOMSource source = new DOMSource(doc);
			StreamResult console = new StreamResult(outFile);
			transformer.transform(source, console);
			isCreate = true;
			System.out.println("\nXML DOM Created Successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCreate;
	}

	/**
	 * @param doc
	 * @param ingestAsset
	 */
	private void createXML(Document doc, IngestAsset ingestAsset) {
		Element adi = doc.createElement("ADI");

		Node metadata = getMetadataTag(doc);

		doc.appendChild(adi);
		adi.appendChild(metadata);

		// Package
		metadata.appendChild(getAMS(doc, ingestAsset, PACKAGE));
		metadata.appendChild(getAppData(doc, "Metadata_Spec_Version",
				"CableLabsVOD1.0"));

		// Asset metadata
		Element asset = getAsset(doc, ingestAsset);
		adi.appendChild(asset);

		// Movie
		asset.appendChild(getMovie(doc, ingestAsset));

		// Poster
		Element poster = getPoster(doc, ingestAsset);
		if (poster != null)
			asset.appendChild(poster);

		// Subtitle
		Element subtitle = getSubtitle(doc, ingestAsset);
		if (subtitle != null)
			asset.appendChild(subtitle);
	}

	/**
	 * @param doc
	 * @return Element
	 */
	private static Element getMetadataTag(Document doc) {
		return doc.createElement("Metadata");
	}

	/**
	 * @param doc
	 * @param a
	 * @param type
	 * @return Node
	 */
	private static Node getAMS(Document doc, IngestAsset ingestAsset,
			String type) {
		Element ams = doc.createElement("AMS");
		try {
			String assetName = "BRA" + Util.getStringDate();
			String originalTitle = ingestAsset.getOriginalTitle();
			originalTitle = originalTitle.replaceAll(" ", "").replace("'", "");

			// Asset name character limit
			if (originalTitle.length() + "BRA".length()
					+ Util.getStringDate().length() + type.length() <= 50) {
				assetName += originalTitle;
			} else {
				assetName += originalTitle.substring(0, 50 - ("BRA".length()
						+ Util.getStringDate().length() + type.length()));
			}

			assetName += type;

			ams.setAttribute("Asset_Name", assetName);
			ams.setAttribute("Description", "BRA" + Util.getStringDate()
					+ ingestAsset.getOriginalTitle().replaceAll(" ", "")
					+ "Description");
			ams.setAttribute("Product", "MOD");

			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			ams.setAttribute("Creation_Date", sdf.format(new Date()));
			ams.setAttribute("Asset_ID", ingestAsset.getAssetReference());
			ams.setAttribute("Version_Major", "1");
			ams.setAttribute("Version_Minor", "0");
			ams.setAttribute("Provider_ID", ingestAsset.getContentProviderId());
			ams.setAttribute("Provider", ingestAsset.getContentProvider());
			String assetClass = "";
			switch (type) {
			case PACKAGE:
				assetClass = "package";
				break;
			case METADATA:
				assetClass = "title";
				break;
			case MOVIE:
				assetClass = "movie";
				break;
			case POSTER:
				assetClass = "poster";
				break;
			case SUBTITLE:
				assetClass = "subtitle";
				break;
			default:
				break;
			}
			ams.setAttribute("Asset_Class", assetClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ams;
	}

	/**
	 * @param doc
	 * @param name
	 * @param value
	 * @return Element
	 */
	private static Element getAppData(Document doc, String name, String value) {
		Element appData = doc.createElement("App_Data");
		try {
			appData.setAttribute("Name", name);
			appData.setAttribute("Value", value);
			appData.setAttribute("App", "MOD");
			if ("Rating".equals(name)) {
				appData.setAttribute("Target_Country", "br");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appData;
	}

	/**
	 * @param doc
	 * @param ingestAsset
	 * @return Element
	 */
	private static Element getAsset(Document doc, IngestAsset ingestAsset) {
		Element asset = doc.createElement("Asset");
		try {
			Node metadata = asset.appendChild(getMetadataTag(doc));
			metadata.appendChild(getAMS(doc, ingestAsset, METADATA));
			metadata.appendChild(getAppData(doc, "Type", "title"));
			metadata.appendChild(getAppData(doc, "Content_Category",
					ingestAsset.getCategory()));
			metadata.appendChild(getAppData(doc, "Commercial_Type",
					ingestAsset.getBusinessModel()));
			metadata.appendChild(getAppData(doc, "Original_Title",
					ingestAsset.getOriginalTitle()));
			metadata.appendChild(getAppData(doc, "Rating",
					ingestAsset.getRating()));
			metadata.appendChild(getAppData(doc, "Run_Time",
					ingestAsset.getDuration()));
			metadata.appendChild(getAppData(doc, "Year",
					ingestAsset.getReleaseYear()));
			metadata.appendChild(getAppData(doc, "Country_of_Origin",
					ingestAsset.getCountry()));
			metadata.appendChild(getAppData(doc, "Studio",
					ingestAsset.getContentProviderId()));
			metadata.appendChild(getAppData(doc, "Studio_Name",
					ingestAsset.getContentProviderId()));

			// Encoding Profile
			metadata.appendChild(getAppData(doc, "Encoding_Profile",
					"widescreen"));
			metadata.appendChild(getAppData(doc, "Genre",
					ingestAsset.getGenre1(), "pt-BR"));
			metadata.appendChild(getAppData(doc, "Licensing_Window_Start",
					ingestAsset.getWindowStart()));
			metadata.appendChild(getAppData(doc, "Licensing_Window_End",
					ingestAsset.getWindowEnd()));

			// Languages
			metadata.appendChild(getAppData(doc, "Title",
					ingestAsset.getOriginalTitle(), "en-US"));
			metadata.appendChild(getAppData(doc, "Title",
					ingestAsset.getPtTitle(), "pt-BR"));
			metadata.appendChild(getAppData(doc, "Summary_Short",
					ingestAsset.getSynopsis(), "pt-BR"));
			metadata.appendChild(getAppData(doc, "Summary_Medium",
					ingestAsset.getSynopsis(), "pt-BR"));
			if (ingestAsset.getDirector() != null
					&& !ingestAsset.getDirector().equals("")) {
				metadata.appendChild(getAppData(doc, "Director",
						ingestAsset.getDirector()));
			}

			// Actors
			getActors(doc, metadata, ingestAsset.getActors());

			// Devices
			getDevices(doc, metadata, ingestAsset);
			if (ingestAsset.getEpisodeNumber() != null) {
				metadata.appendChild(getAppData(doc, "Movie_Type", "Episode"));
				metadata.appendChild(getAppData(doc, "Series_Asset_Id",
						ingestAsset.getSeriesId()));
				if (ingestAsset.getSeasonId() != null
						&& !ingestAsset.getSeasonId().equals("")) {
					metadata.appendChild(getAppData(doc, "Season_Asset_Id",
							ingestAsset.getSeasonId()));
				}
				metadata.appendChild(getAppData(doc, "Order",
						ingestAsset.getEpisodeNumber()));
			} else {
				metadata.appendChild(getAppData(doc, "Movie_Type", "Movie"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asset;
	}

	/**
	 * @param doc
	 * @param name
	 * @param value
	 * @return Element
	 */
	private static Element getAppData(Document doc, String name, Number value) {
		Element appData = doc.createElement("App_Data");
		try {
			appData.setAttribute("Name", name);
			if (value != null) {
				appData.setAttribute("Value", value.toString());
			} else {
				appData.setAttribute("Value", "");
			}
			appData.setAttribute("App", "MOD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appData;
	}

	/**
	 * @param doc
	 * @param name
	 * @param value
	 * @param language
	 * @return Element
	 */
	private static Element getAppData(Document doc, String name, String value,
			String language) {
		Element appData = doc.createElement("App_Data");
		try {
			appData.setAttribute("Name", name);
			appData.setAttribute("Value", value);
			appData.setAttribute("App", "MOD");
			appData.setAttribute("Target_Language", language);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appData;
	}

	/**
	 * @param doc
	 * @param metadata
	 * @param actors
	 */
	private static void getActors(Document doc, Node metadata, String actors) {
		try {
			if (actors == null || actors.equals("")) {
				return;
			}
			String[] actorsList = actors.split(",");
			for (String actor : actorsList) {
				metadata.appendChild(getAppData(doc, "Actors", actor.trim()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param doc
	 * @param metadata
	 * @param ingestAsset
	 */
	private static void getDevices(Document doc, Node metadata,
			IngestAsset ingestAsset) {
		try {
			if (ingestAsset.getGames()) {
				metadata.appendChild(getAppDataDevice(doc, "games"));
			}
			if (ingestAsset.getMobile()) {
				metadata.appendChild(getAppDataDevice(doc, "mobile"));
			}
			if (ingestAsset.getPc()) {
				metadata.appendChild(getAppDataDevice(doc, "pc"));
			}
			if (ingestAsset.getStbOtt()) {
				metadata.appendChild(getAppDataDevice(doc, "stb_ott"));
			}
			if (ingestAsset.getTablet()) {
				metadata.appendChild(getAppDataDevice(doc, "tablet"));
			}
			if (ingestAsset.getConectTV()) {
				metadata.appendChild(getAppDataDevice(doc, "conectTV"));
			}
			if (ingestAsset.getMediaroom()) {
				metadata.appendChild(getAppDataDevice(doc, "stb_iptv"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param doc
	 * @param value
	 * @return Node
	 */
	private static Node getAppDataDevice(Document doc, String value) {
		Element appData = doc.createElement("App_Data");
		try {
			appData.setAttribute("App", "MOD");
			appData.setAttribute("Name", "Device_Group_Inclusion");
			appData.setAttribute("Value", value);
			appData.setAttribute("Instance", "25");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appData;
	}

	/**
	 * @param doc
	 * @param ingestAsset
	 * @return Element
	 */
	private static Element getMovie(Document doc, IngestAsset ingestAsset) {
		boolean found = false;
		IngestFile file = null;
		Iterator<IngestFile> it = ingestAsset.getIngestFolder()
				.getIngestFiles().iterator();
		while (it.hasNext() && !found) {
			file = it.next();
			if (file.getMediaType().equals(EnumMediaType.MOVIE)) {
				found = true;
			}
		}
		if (!found)
			return null;
		Element asset = doc.createElement("Asset");
		Element metadata = doc.createElement("Metadata");
		metadata.appendChild(getAMS(doc, ingestAsset, METADATA));
		metadata.appendChild(getAppData(doc, "Type", "movie"));
		if (ingestAsset.getAudio51() == null) {
			ingestAsset.setAudio51("Stereo");
		}
		metadata.appendChild(getAppData(doc, "Audio_Type",
				ingestAsset.getAudio51()));
		metadata.appendChild(getAppData(doc, "Screen_Format", file.getFormat()));
		metadata.appendChild(getAppData(doc, "Languages", file.getLanguage()));
		metadata.appendChild(getAppData(doc, "Content_FileSize",
				file.getFileSize()));
		metadata.appendChild(getAppData(doc, "Content_CheckSum",
				file.getChecksum()));
		// Data file
		asset.appendChild(metadata);
		asset.appendChild(getContent(doc, file.getName()));
		return asset;
	}

	/**
	 * @param doc
	 * @param value
	 * @return Element
	 */
	private static Element getContent(Document doc, String value) {
		Element appData = doc.createElement("Content");
		appData.setAttribute("Value", value);
		return appData;
	}

	/**
	 * @param doc
	 * @param ingestAsset
	 * @return Element
	 */
	private static Element getPoster(Document doc, IngestAsset ingestAsset) {
		boolean found = false;
		IngestFile file = null;
		Iterator<IngestFile> it = ingestAsset.getIngestFolder()
				.getIngestFiles().iterator();
		while (it.hasNext() && !found) {
			file = it.next();
			if (file.getMediaType().equals(EnumMediaType.POSTER)) {
				found = true;
			}
		}

		if (!found)
			return null;

		Element asset = doc.createElement("Asset");
		Element metadata = doc.createElement("Metadata");
		metadata.appendChild(getAMS(doc, ingestAsset, POSTER));
		metadata.appendChild(getAppData(doc, "Type", "poster"));
		metadata.appendChild(getAppData(doc, "Image_Aspect_Ratio",
				file.getFormat()));
		metadata.appendChild(getAppData(doc, "Content_FileSize",
				file.getFileSize()));
		metadata.appendChild(getAppData(doc, "Content_CheckSum",
				file.getChecksum()));
		asset.appendChild(metadata);
		asset.appendChild(getContent(doc, file.getName()));
		return asset;
	}

	/**
	 * @param doc
	 * @param ingestAsset
	 * @return Element
	 */
	private static Element getSubtitle(Document doc, IngestAsset ingestAsset) {
		boolean found = false;
		IngestFile file = null;
		Iterator<IngestFile> it = ingestAsset.getIngestFolder()
				.getIngestFiles().iterator();
		while (it.hasNext() && !found) {
			file = it.next();
			if (file.getMediaType().equals(EnumMediaType.SUBTITLE)) {
				found = true;
			}
		}

		if (!found)
			return null;

		Element asset = doc.createElement("Asset");
		Element metadata = doc.createElement("Metadata");
		metadata.appendChild(getAMS(doc, ingestAsset, SUBTITLE));
		metadata.appendChild(getAppData(doc, "Type", "subtitle"));
		metadata.appendChild(getAppData(doc, "Languages", file.getLanguage()));
		metadata.appendChild(getAppData(doc, "Content_FileSize",
				file.getFileSize()));
		asset.appendChild(metadata);
		asset.appendChild(getContent(doc, file.getName()));
		return asset;
	}
}
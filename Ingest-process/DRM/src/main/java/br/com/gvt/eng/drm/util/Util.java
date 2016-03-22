package br.com.gvt.eng.drm.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import br.com.gvt.eng.drm.properties.PropertiesConfig;

public class Util {

	/**
	 * @return URI IPVOD
	 * @throws IOException
	 */
	public static String getURIIpvod() {
		// Busca arquivo de properties
		String inFilesPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			inFilesPath = prop.getProperty("uri.ipvod");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inFilesPath;
	}

	/**
	 * @return In Files Path
	 * @throws IOException
	 */
	public static String getInFilesPath() {
		// Busca arquivo de properties
		String inFilesPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			inFilesPath = prop.getProperty("url.in.files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inFilesPath;
	}

	/**
	 * @return Out Files Path
	 * @throws IOException
	 */
	public static String getOutFilesPath() {
		// Busca arquivo de properties
		String outFilesPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			outFilesPath = prop.getProperty("url.out.files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFilesPath;
	}

	/**
	 * @return Out Files trailer Path
	 * @throws IOException
	 */
	public static String getOutFilesTrailerPath() {
		// Busca arquivo de properties
		String outFilesTrailerPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			outFilesTrailerPath = prop.getProperty("url.out.trailer");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFilesTrailerPath;
	}

	/**
	 * @return Out Files Path
	 * @throws IOException
	 */
	public static String getSourceLocFilesPath() {
		// Busca arquivo de properties
		String sourceLocFilesPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			sourceLocFilesPath = prop.getProperty("url.sourceLoc.drm");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sourceLocFilesPath;
	}

	/**
	 * @return EndoPoint DRM
	 * @throws IOException
	 */
	public static String getEndPointDrm() {
		// Busca arquivo de properties
		String enpoint = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			enpoint = prop.getProperty("drm.endpoint");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return enpoint;
	}

	/**
	 * @return status initial ingest/drm
	 * @throws IOException
	 */
	public static String getStatusIniDrm() {
		// Busca arquivo de properties
		String statusIni = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			statusIni = prop.getProperty("status.init");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return statusIni;
	}

	/**
	 * @return status finished ingest/drm
	 * @throws IOException
	 */
	public static String getStatusFinishedDrm() {
		// Busca arquivo de properties
		String statusFinished = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			statusFinished = prop.getProperty("status.finished");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return statusFinished;
	}

	/**
	 * Retorna a data atual
	 * 
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getCurrentDate() throws ParseException {
		// Setando a data atual
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = df.parse(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
				.format(new Date()));
		return date;
	}

	/**
	 * Verifica se o campo e nulo ou vazio
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmptyOrNull(String s) {
		return s == null || s.equals("");
	}

}

package br.com.gvt.eng.clean.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.gvt.eng.clean.properties.PropertiesConfig;

public class Util {

	static Logger logger = Logger.getLogger(Util.class.getName());

	/**
	 * @return URI IPVOD
	 * @throws IOException
	 */
	public static String getURIIpvod() {
		// Busca arquivo de properties
		String uriIpvod = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			uriIpvod = prop.getProperty("uri.ipvod");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uriIpvod;
	}

	/**
	 * @return path DRM
	 * @throws IOException
	 */
	public static String getPathDrm() {
		// Busca arquivo de properties
		String pathDrm = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathDrm = prop.getProperty("path.drm");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathDrm;
	}
	
	/**
	 * @return path DRM
	 * @throws IOException
	 */
	public static String getPathConvoy() {
		// Busca arquivo de properties
		String pathConvoy = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathConvoy = prop.getProperty("path.convoy");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathConvoy;
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
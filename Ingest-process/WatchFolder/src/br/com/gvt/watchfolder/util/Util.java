package br.com.gvt.watchfolder.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.gvt.watchfolder.properties.PropertiesConfig;

public class Util {

	static Logger logger = Logger.getLogger(Util.class.getName());

	/**
	 * @return URI IPVOD Ingest
	 * @throws IOException
	 */
	public static String getUriIngest() {
		// Busca arquivo de properties
		String saveIngest = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			saveIngest = prop.getProperty("uri.ipvod.ingest");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saveIngest;
	}

	/**
	 * @return Path file
	 * @throws IOException
	 */
	public static String getPathFile() {
		// Busca arquivo de properties
		String pathFile = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathFile = prop.getProperty("wf.path.file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathFile;
	}

	/**
	 * @return Out Import file
	 * @throws IOException
	 */
	public static String getPathOutImport() {
		// Busca arquivo de properties
		String outImport = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			outImport = prop.getProperty("wf.out.import");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outImport;
	}

	public static Integer actionReadXml() {
		// Busca arquivo de properties
		Integer readXml = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			readXml = Integer.parseInt(prop.getProperty("wf.read.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readXml;
	}

	public static Integer actionMd5Validate() {
		// Busca arquivo de properties
		Integer md5Validate = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			md5Validate = Integer.parseInt(prop.getProperty("wf.md5.validate"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return md5Validate;
	}

	public static Integer actionImportXml() {
		// Busca arquivo de properties
		Integer importXml = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			importXml = Integer.parseInt(prop.getProperty("wf.import.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return importXml;
	}

	public static Integer actionFinishedOk() {
		// Busca arquivo de properties
		Integer finishedOk = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			finishedOk = Integer.parseInt(prop.getProperty("wf.finished.ok"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finishedOk;
	}

	public static Integer actionFinishedError() {
		// Busca arquivo de properties
		Integer finishedError = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			finishedError = Integer.parseInt(prop
					.getProperty("wf.finished.error"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return finishedError;
	}

	/**
	 * @return String inFiles
	 */
	public static String getWFFiles() {
		// Busca arquivo de properties
		String inFiles = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			inFiles = prop.getProperty("wf.files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inFiles;
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

package br.com.gvt.eng.paytv.ingest.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.properties.PropertiesConfig;

public class Util {

	static Logger logger = Logger.getLogger(Util.class.getName());

	/**
	 * @return URI IPVOD
	 * @throws IOException
	 */
	public static String getURIIngest() {
		// Busca arquivo de properties
		String uriIgest = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			uriIgest = prop.getProperty("uri.ingest");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uriIgest;
	}

	/**
	 * @return Input Path for files
	 * @throws IOException
	 */
	public static String getTypeInFiles() {
		// Busca arquivo de properties
		String inFiles = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			inFiles = prop.getProperty("type.files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inFiles;
	}

	/**
	 * @return Output Path for files
	 * @throws IOException
	 */
	public static String getOutFilesPath() {
		// Busca arquivo de properties
		String outFilesPath = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			outFilesPath = prop.getProperty("out.search.files");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFilesPath;
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
		return s == null || s.equalsIgnoreCase("");
	}

	/**
	 * @return typeMovie
	 */
	public static String getMovieFiles() {
		// Busca arquivo de properties
		String typeMovie = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			typeMovie = prop.getProperty("movie.file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return typeMovie;
	}

	/**
	 * @return typeImage
	 */
	public static String getImagensFiles() {
		// Busca arquivo de properties
		String typeImage = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			typeImage = prop.getProperty("poster.file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return typeImage;
	}

	/**
	 * @return typeSubtitle
	 */
	public static String getSubtitleFiles() {
		// Busca arquivo de properties
		String typeSubtitle = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			typeSubtitle = prop.getProperty("subtitle.file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return typeSubtitle;
	}

	/**
	 * @return gvpFile
	 */
	public static String getGvpFile() {
		// Busca arquivo de properties
		String gvpFile = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			gvpFile = prop.getProperty("gvp.file");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gvpFile;
	}

}
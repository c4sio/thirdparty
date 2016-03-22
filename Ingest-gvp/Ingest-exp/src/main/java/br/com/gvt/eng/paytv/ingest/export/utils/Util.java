package br.com.gvt.eng.paytv.ingest.export.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.export.properties.PropertiesConfig;

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
	 * @return String
	 */
	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		return sdf.format(new Date());
	}

}
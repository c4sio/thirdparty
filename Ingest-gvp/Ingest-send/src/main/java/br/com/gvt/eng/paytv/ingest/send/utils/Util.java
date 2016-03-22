package br.com.gvt.eng.paytv.ingest.send.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.send.properties.PropertiesConfig;

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
	 * @return Password SSH
	 */
	public static String getPassSsh() {
		// Busca arquivo de properties
		String passwordSSH = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			passwordSSH = prop.getProperty("pass.ascp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return passwordSSH;
	}

	/**
	 * @return Password SSH
	 */
	public static String getPathShellScript() {
		// Busca arquivo de properties
		String pathShell = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathShell = prop.getProperty("path.shell");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathShell;
	}

	/**
	 * @return Command ASCP 1
	 */
	public static String getCommandAscp1() {
		// Busca arquivo de properties
		String cmdAscp1 = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			cmdAscp1 = prop.getProperty("command.ascp1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmdAscp1;
	}

	/**
	 * @return Command ASCP 2
	 */
	public static String getCommandAscp2() {
		// Busca arquivo de properties
		String cmdAscp2 = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			cmdAscp2 = prop.getProperty("command.ascp2");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cmdAscp2;
	}

	/**
	 * @return Password SSH
	 */
	public static String getDirPrefixAscp() {
		// Busca arquivo de properties
		String dirPrefix = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			dirPrefix = prop.getProperty("dir.prefix");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirPrefix;
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
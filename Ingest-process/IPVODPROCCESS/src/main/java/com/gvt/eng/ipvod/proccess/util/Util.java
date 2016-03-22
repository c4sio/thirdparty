package com.gvt.eng.ipvod.proccess.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gvt.eng.ipvod.proccess.properties.PropertiesConfig;

public class Util {

	private static final String SPRING_CONFIG = "classpath*:/resources/spring-config.xml";

	Logger logger = Logger.getLogger(Util.class);

	private static List<String> error = new ArrayList<String>();

	/**
	 * Lista de erros na leitura do XML de entrada
	 * 
	 * @return List
	 */
	public static List<String> getError() {
		return error;
	}

	public static void setError(List<String> error) {
		Util.error = error;
	}

	/**
	 * @return SPRING_CONFIG
	 */
	public static String getSpringConfig() {
		return SPRING_CONFIG;
	}

	/**
	 * @param strHor
	 * @return Hours in minutes
	 * @throws ParseException
	 */
	public static long changeHourToSecond(String strHor) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(strHor));
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		long value = (hours * 3600) + minute * 60 + second;
		return value;
	}

	/**
	 * @param strData
	 * @return date
	 * @throws Exception
	 */
	public static Date stringToData(String strData) throws Exception {
		if (strData == null || strData.equals(""))
			return null;

		Date data = null;
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			data = formatter.parse(strData);
			System.out.println(data);
			System.out.println(formatter.format(data));
		} catch (ParseException e) {
			throw e;
		}
		return data;
	}

	/**
	 * Verifica se o campo e nulo ou vazio
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean isEmptyOrNull(String s) {
		return s == null || s.equals("") || s.equals("null");
	}

	/**
	 * @return pathImportImg
	 */
	public static String getPathImportImg() {
		// Busca arquivo de properties
		String pathImportImg = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathImportImg = prop.getProperty("out.import.img");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathImportImg;
	}

	/**
	 * @return pathClusterImg
	 */
	public static String getPathClusterImg() {
		// Busca arquivo de properties
		String pathClusterImg = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			pathClusterImg = prop.getProperty("out.cluster.img");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pathClusterImg;
	}

	/**
	 * @return scpPort
	 */
	public static int getScpPort() {
		// Busca arquivo de properties
		String scpPort = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			scpPort = prop.getProperty("scp.port").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(scpPort);
	}

	/**
	 * @return scpUser
	 */
	public static String getScpUser() {
		// Busca arquivo de properties
		String scpUser = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			scpUser = prop.getProperty("scp.user").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scpUser;
	}

	/**
	 * @return scpPassword
	 */
	public static String getScpPassword() {
		// Busca arquivo de properties
		String scpPassword = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			scpPassword = prop.getProperty("scp.password").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scpPassword;
	}

	/**
	 * @return listHost
	 */
	public static List<String> getScpHost() {
		// Busca arquivo de properties
		List<String> listHost = new ArrayList<String>();
		String[] tokens = null;
		Properties prop = null;
		try {
			prop = PropertiesConfig.getProp();
			tokens = prop.getProperty("scp.host").trim().split(";");
			for (String token : tokens) {
				listHost.add(token);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listHost;
	}

}

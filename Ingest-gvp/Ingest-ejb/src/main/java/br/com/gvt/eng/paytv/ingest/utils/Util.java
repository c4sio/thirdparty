package br.com.gvt.eng.paytv.ingest.utils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	/**
	 * @return String
	 */
	public static String getStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYMMdd");
		return sdf.format(new Date());
	}

	public static String formatDate(String date) {
		try {
			String result = date.substring(6);
			result += "-";
			result += date.substring(3, 5);
			result += "-";
			result += date.substring(0, 2);
			return result;
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static String setEncoding(String value) {
		String encode = null;
		if (value != null) {
			encode = new String(value.getBytes(), StandardCharsets.UTF_8);
		}
		return encode;
	}

}

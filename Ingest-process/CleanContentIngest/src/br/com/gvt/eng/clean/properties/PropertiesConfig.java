package br.com.gvt.eng.clean.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesConfig {

	static Logger logger = Logger.getLogger(PropertiesConfig.class.getName());

	/**
	 * @return Properties
	 * @throws IOException
	 */
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("dados.properties");
		props.load(file);
		return props;
	}
}

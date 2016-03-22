package br.com.gvt.watchfolder.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

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

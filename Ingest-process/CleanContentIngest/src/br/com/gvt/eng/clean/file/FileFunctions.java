package br.com.gvt.eng.clean.file;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileFunctions {

	static Logger logger = Logger.getLogger(FileFunctions.class.getName());

	/**
	 * remove os arquivos que ja foram processados
	 * 
	 * @param resource
	 * @throws IOException
	 */
	public void removeFiles(File resource) throws IOException {
		if (resource.isDirectory()) {
			File[] childFiles = resource.listFiles();
			for (File child : childFiles) {
				removeFiles(child);
			}
		}
		resource.delete();
		logger.info("Arquivo [" + resource.getName()
				+ "] deletado com sucesso.");
	}
}

package br.com.gvt.watchfolder.shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ExecUtils {

	static Logger logger = Logger.getLogger(ExecUtils.class.getName());

	/**
	 * @param cmd
	 * @return String
	 */
	public String execShellCmd(String cmd) {
		logger.info("Executando o execShellCmd");
		String result = "";
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(new String[] { "/bin/bash", "-c",
					cmd });

			int exitValue = process.waitFor();
			System.out.println("exit value: " + exitValue);
			logger.info("Exit value: " + exitValue);

			BufferedReader buf = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";

			while ((line = buf.readLine()) != null) {
				result += "exec response: " + line;
			}
		} catch (Exception e) {
			System.out.println(e);
			logger.error("Erro: " + e);
		}
		return result;
	}
}
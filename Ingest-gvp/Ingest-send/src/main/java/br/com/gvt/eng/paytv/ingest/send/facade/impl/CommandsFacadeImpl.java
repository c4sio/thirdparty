package br.com.gvt.eng.paytv.ingest.send.facade.impl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.send.facade.CommandsFacade;
import br.com.gvt.eng.paytv.ingest.send.utils.Util;
import br.com.gvt.eng.paytv.ingest.send.vo.IngestAssetVO;

public class CommandsFacadeImpl implements CommandsFacade {

	Logger logger = Logger.getLogger(CommandsFacadeImpl.class.getName());

	@Override
	public boolean sendFiles(IngestAssetVO ingestAssetVO) throws IOException,
			InterruptedException {
		ArrayList<String> commands = new ArrayList<String>();

		// Verifica a posição
		int pos = ingestAssetVO.getPathAssetIn().indexOf("_");

		// commands.add("/bin/bash");
		commands.add(Util.getCommandAscp1() + " "
				+ ingestAssetVO.getIngestFolder().getUrlRootOut() + "ADI.xml"
				+ " " + Util.getCommandAscp2() + " " + Util.getDirPrefixAscp()
				+ ingestAssetVO.getPathAssetIn().substring(0, pos) + "/"
				+ Util.getDirPrefixAscp() + ingestAssetVO.getPathAssetIn()
				+ "/" + ingestAssetVO.getIngestFolder().getFolderReference()
				+ " &");

		System.out.println("comando 1: " + commands.toString());

		boolean isOk = false;
		BufferedReader br = null;
		try {
			ProcessBuilder p = new ProcessBuilder(commands);
			System.out.println("Run command");
			Process process = p.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("Retorno do comando = [" + line + "]");
			}

			int errCode = process.waitFor();

			System.out.println("errCode: " + errCode);
			System.out.println("Comando executado com erro? "
					+ (errCode == 0 ? "No" : "Yes"));

			if (errCode == 0) {
				isOk = true;
			}

			// TODO: Necessario enviar o password
			// Util.getPassSsh();

		} catch (IOException ioe) {
			logger.error("Erro ao executar comando shell" + ioe.getMessage());
			throw ioe;
		} finally {
			secureClose(br);
		}
		return isOk;
	}

	@Override
	public boolean sendFilesExec(IngestAssetVO ingestAssetVO) {
		BufferedReader reader = null;
		boolean isOk = false;
		try {
			if (ingestAssetVO.getPathAssetIn() == null || ingestAssetVO.getPathAssetIn().length() == 0) {
				System.out.println("Asset: " + ingestAssetVO.getAssetId() + " sem parametro de pathAssetIn");
				throw new RuntimeException("pathAssetIn missing");
			}

			int pos = ingestAssetVO.getPathAssetIn().indexOf("_");
			String command = Util.getPathShellScript() + " "
					+ ingestAssetVO.getIngestFolder().getUrlRootOut() + " "
					+ Util.getCommandAscp2()
					+ Util.getDirPrefixAscp()
					+ ingestAssetVO.getPathAssetIn().substring(0, pos) + "/"
					+ Util.getDirPrefixAscp()
					+ ingestAssetVO.getPathAssetIn()
					+ "/"
					+ ingestAssetVO.getIngestFolder().getFolderReference()
					+ " &";

			System.out.println("comando 2: " + command);

			Process process = Runtime.getRuntime().exec(command);
			reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int errCode = process.waitFor();
			if (errCode == 0) {
				isOk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao executar comando shell " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isOk;
	}

	@Override
	public boolean sendFilesToBatch(IngestAssetVO ingestAssetVO) {
		BufferedReader reader = null;
		boolean isOk = false;
		try {
			// Verifica a posição
			int pos = ingestAssetVO.getPathAssetIn().indexOf("_");
			
			// Cria comando
			String command = Util.getPathShellScript() + " "
					+ ingestAssetVO.getIngestFolder().getUrlRootOut() + " "
					+ Util.getDirPrefixAscp()
					+ ingestAssetVO.getPathAssetIn().substring(0, pos) + "/"
					+ Util.getDirPrefixAscp() + ingestAssetVO.getPathAssetIn()
					+ "/"
					+ ingestAssetVO.getIngestFolder().getFolderReference()
					+ " &";

			System.out.println("comando 3: " + command);

			Process process = Runtime.getRuntime().exec(command);
			reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int errCode = process.waitFor();
			if (errCode == 0) {
				isOk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao executar comando shell " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isOk;
	}

	/**
	 * @param resource
	 */
	private void secureClose(final Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException ex) {
			logger.error("Erro = " + ex.getMessage());
		}
	}

}
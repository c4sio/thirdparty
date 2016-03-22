package br.com.gvt.eng.clean.start;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.eng.clean.rest.IngestStageRest;
import br.com.gvt.eng.clean.rest.impl.IngestStageRestImpl;
import br.com.gvt.eng.clean.thread.CleanUpThreads;
import br.com.gvt.eng.clean.thread.impl.CleanUpThreadsImpl;
import br.com.gvt.eng.clean.vo.IpvodIngestStage;

public class StartCleanContentIngest {

	static Logger logger = Logger.getLogger(StartCleanContentIngest.class
			.getName());

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");
			StartCleanContentIngest startCleanContentIngest = new StartCleanContentIngest();
			startCleanContentIngest.execute();
		} catch (Exception e) {
			logger.error(
					"Erro ao inicializar processamento Clean Content Ingest: ",
					e);
		}
	}

	private void execute() {
		System.out.println(">> STARTING THE CLEAN CONTENT INGEST <<");
		logger.info("STARTING THE CLEAN CONTENT INGEST");
		// Variavel utilizada para calcular o tempo de execucao
		long tempoInicio = System.currentTimeMillis();
		try {
			IngestStageRest ingestStageRest = new IngestStageRestImpl();
			List<IpvodIngestStage> listDataIngest = new ArrayList<IpvodIngestStage>();
			listDataIngest = ingestStageRest.findFilesForCleanUp();

			logger.info("Encontrou " + listDataIngest.size()
					+ " registros para limpeza.");
			System.out.println("Encontrou " + listDataIngest.size()
					+ " registros para limpeza.");

			// Se encontrou algum registro verifica os caminhos
			if (!listDataIngest.isEmpty()) {
				boolean cleanUp = false;
				CleanUpThreads cleanUpThreads = new CleanUpThreadsImpl();
				cleanUp = cleanUpThreads.executeCleanUp(listDataIngest);
				System.out.println("Limpou as pastas: " + cleanUp);
			}
		} catch (Exception e) {
			logger.error("Erro no processamento Clean Content Ingest: ", e);
		}
		// Mostra o tempo de execucao no display
		System.out
				.println("Tempo Total processamento limpeza de pastas ingest: "
						+ new SimpleDateFormat("mm:ss").format(new Date(System
								.currentTimeMillis() - tempoInicio)));
		// Mostra o tempo de execucao
		logger.info("Tempo Total processamento limpeza de pastas ingest: "
				+ new SimpleDateFormat("mm:ss").format(new Date(System
						.currentTimeMillis() - tempoInicio)));
		System.out.println(">> ENDING THE CLEAN CONTENT INGEST <<");
		logger.info("ENDING THE CLEAN CONTENT INGEST");
	}
}

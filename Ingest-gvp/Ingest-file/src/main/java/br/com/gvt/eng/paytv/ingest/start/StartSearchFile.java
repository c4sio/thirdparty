package br.com.gvt.eng.paytv.ingest.start;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.eng.paytv.ingest.facade.FileFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.facade.SaveFilesFacade;
import br.com.gvt.eng.paytv.ingest.facade.impl.FileFunctionsFacadeImpl;
import br.com.gvt.eng.paytv.ingest.facade.impl.SaveFilesFacadeImpl;
import br.com.gvt.eng.paytv.ingest.util.Util;

public class StartSearchFile {

	static Logger logger = Logger.getLogger(StartSearchFile.class.getName());

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");

			// Declara a variaveis
			String action = args[0];
			String filePath = args[1];

			// String action = "verify";
			// String filePath = "C:\\Temp\\XML_TEST\\teste_in_import";

			if (args.length <= 0) {
				throw new Exception(
						"Variaveis nao declaradas na inicializacao do processo!");
			}

			// Remove o nome do arquivo da url
			String pathfile = filePath + File.separator;

			// Verifica se a acao esta correta
			if (action.equalsIgnoreCase("verify")) {
				StartSearchFile startSearchFile = new StartSearchFile();
				pathfile = pathfile.split("!!!")[0];
				if (!Util.isEmptyOrNull(pathfile)) {
					startSearchFile.execute(pathfile);
				} else {
					logger.info("Path indevido - " + pathfile);
					System.out.println("Path indevido - " + pathfile);
				}
			} else {
				logger.info("Acao indevida - Action = " + action);
				System.out.println("Acao indevida - Action = " + action);
			}

		} catch (Exception e) {
			logger.error(
					"Erro ao inicializar processamento Ingest Search Files: ",
					e);
		}
	}

	/**
	 * Executa as acoes do search Ingest
	 * 
	 * @param pathfile
	 */
	private static void execute(String pathfile) {
		System.out
				.println(">> STARTING THE INTEGRATION INGEST - SEARCH FILES <<");
		logger.info("STARTING THE INTEGRATION INGEST - SEARCH FILES");
		try {
			// Variavel utilizada para calcular o tempo de execucao
			long tempoInicio = System.currentTimeMillis();

			FileFunctionsFacade fileFunctions = new FileFunctionsFacadeImpl();

			List<File> listFiles = fileFunctions.searchFiles(pathfile);

			logger.info("Encontrou " + listFiles.size()
					+ " arquivos para processar.");
			System.out.println("Encontrou " + listFiles.size()
					+ " arquivos para processar.");

			// Verifica se existe registro para leitura
			if (!listFiles.isEmpty()) {
				// Variaveis
				SaveFilesFacade saveFilesFacade = new SaveFilesFacadeImpl();
				List<File> saveFiles = new ArrayList<File>();
				String currentPathFile = null;
				String folderRoot = null;
				Pattern pMovie = Pattern.compile(Util.getMovieFiles());
				Matcher movie = null;

				// Executa a leitura dos registros
				for (File file : listFiles) {
					// Busca o nome do arquivo para criar no destino
					movie = pMovie.matcher(file.getName().toLowerCase());
					if (movie.matches()) {
						folderRoot = FilenameUtils.removeExtension(file
								.getName());
					}

					// Verifica se o caminho da imagem mudou
					if (currentPathFile != null
							&& !file.getParent().equals(currentPathFile)) {
						// Salva os dados
						System.out
								.println("Arquivos verificados, salvando dados no banco");
						boolean saveOk = saveFilesFacade.saveFiles(saveFiles,
								currentPathFile + File.separator,
								currentPathFile + File.separator, folderRoot);
						if (saveOk) {
							System.out
									.println("Criando arquivo de confirmacao de leitura");
							logger.info("Criando arquivo de confirmacao de leitura");
							fileFunctions.createFileReadOk(currentPathFile);
						}

						// limpa as variaveis
						saveFiles = new ArrayList<File>();
						currentPathFile = null;
						saveFiles.add(file);
					} else {
						saveFiles.add(file);
					}
					// Atribui o caminho dos arquivos
					currentPathFile = file.getParent();
				}

				// Verifica se restou algum resgistro sem salvar
				if (!saveFiles.isEmpty()) {
					System.out
							.println("Arquivos verificados, salvando dados no banco");
					boolean saveOk = saveFilesFacade.saveFiles(saveFiles,
							currentPathFile + File.separator, currentPathFile
									+ File.separator, folderRoot);
					if (saveOk) {
						System.out
								.println("Criando arquivo de confirmacao de leitura");
						logger.info("Criando arquivo de confirmacao de leitura");
						fileFunctions.createFileReadOk(currentPathFile);
					}
				}
			}

			// Mostra o tempo de execucao no display
			System.out
					.println("Tempo Total processamento Ingest Search Files: "
							+ new SimpleDateFormat("mm:ss").format(new Date(
									System.currentTimeMillis() - tempoInicio)));
			// Mostra o tempo de execucao
			logger.info("Tempo Total processamento Ingest Search Files: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			logger.error("Erro no processamento Ingest Search Files: ", e);
		}
		System.out
				.println(">> ENDING THE INTEGRATION INGEST - SEARCH FILES <<");
		logger.info("ENDING THE INTEGRATION INGEST - SEARCH FILES");
	}
}
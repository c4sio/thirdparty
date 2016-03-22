package br.com.gvt.watchfolder.start;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.watchfolder.MoveFiles;
import br.com.gvt.watchfolder.ReadContentXml;
import br.com.gvt.watchfolder.file.FileFunctions;
import br.com.gvt.watchfolder.impl.MoveFilesImpl;
import br.com.gvt.watchfolder.impl.ReadContentXmlImpl;
import br.com.gvt.watchfolder.json.JSONArray;
import br.com.gvt.watchfolder.rest.RestClient;
import br.com.gvt.watchfolder.rest.impl.RestClientImpl;
import br.com.gvt.watchfolder.util.Util;
import br.com.gvt.watchfolder.vo.IpvodIngestStage;

public class StartWatchFolder {

	static Logger logger = Logger.getLogger(StartWatchFolder.class.getName());

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");

			// Declara a variaveis
			// String action = args[0];
			// String fileName = args[1];

			String action = "verify";
			String fileName = "C:\\Temp\\XML_TEST\\in_import\\786959";

			// Remove o nome do arquivo da url
			File file = new File(fileName);
			String pathfile = file.getParent() + File.separator;

			// Verifica se a acao esta correta
			if (action.equalsIgnoreCase("verify")) {
				StartWatchFolder startWatchFolder = new StartWatchFolder();
				pathfile = pathfile.split("!!!")[0];
				if (!Util.isEmptyOrNull(pathfile)) {
					startWatchFolder.execute(pathfile);
				} else {
					logger.info("Path indevido - " + pathfile);
					System.out.println("Path indevido - " + pathfile);
				}
			} else {
				logger.info("Acao indevida - Action = " + action);
				System.out.println("Acao indevida - Action = " + action);
			}
		} catch (Exception e) {
			logger.error("Erro ao inicializar processamento Watch Folder: ", e);
		}
	}

	/**
	 * Executa watch folder
	 * 
	 * @param pathfile
	 */
	private static void execute(String pathfile) {
		logger.info("STARTING THE WATCH FOLDER");
		System.out.println("STARTING THE WATCH FOLDER");
		try {
			// Variavel utilizada para calcular o tempo de execucao
			long tempoInicio = System.currentTimeMillis();

			RestClient restClient = new RestClientImpl();
			FileFunctions fileFunctions = new FileFunctions();

			// Lista os arquivos para leitura
			List<File> listFiles = fileFunctions.searchFiles(pathfile);

			logger.info("Encontrou " + listFiles.size()
					+ " registros para verificar.");
			System.out.println("Encontrou " + listFiles.size()
					+ " registros para verificar.");

			// Verifica se existe registro para leitura
			if (!listFiles.isEmpty()) {
				// Declara a variaveis
				StringBuffer xmlValues = null;
				String assetInfo = null;
				String assetFileName = null;

				IpvodIngestStage ipvodIngestStage = null;
				JSONArray objectValues = null;

				boolean rename = false;
				boolean md5IsValid = false;
				boolean nextStep = false;
				boolean finish = false;
				boolean filesTSOk = false;

				ReadContentXml readContentXml = new ReadContentXmlImpl();
				MoveFiles moveFiles = new MoveFilesImpl();
				ArrayList<String> listValidateFiles = null;

				// Verifica os registros encontrados
				for (File file : listFiles) {
					// Lendo conteudo XML de entrada
					xmlValues = new StringBuffer();
					xmlValues = fileFunctions.readXmlFileContent(file);

					// Verifica pelo path se o registro ja existe na base
					ipvodIngestStage = new IpvodIngestStage();
					ipvodIngestStage = restClient
							.findDataByFilePath(file.getPath()
									.replace("/", "_").replaceFirst("_", "/"));

					// Erro caso o XML esteja vazio
					if (xmlValues.length() == 0) {
						if (ipvodIngestStage.getId() == null) {
							// Salva registro
							ipvodIngestStage = restClient.saveIngest(
									file.getPath().replace("/", "_")
											.replaceFirst("_", "/"),
									"Erro ao ler xml.",
									Util.actionFinishedError(),
									Util.actionReadXml(), pathfile, "");
						} else {
							// Atualiza registro
							restClient.updateStage(
									file.getPath().replace("/", "_")
											.replaceFirst("_", "/"),
									Util.actionFinishedError(),
									Util.actionReadXml(), "Erro ao ler xml.",
									ipvodIngestStage, pathfile, "");
						}

						// Altera o fim do arquivo com erro
						rename = fileFunctions.renameXML(file, ".error");

						if (!rename) {
							logger.error("Erro ao renomear o arquivo "
									+ file.getName() + " para error!");
						}
					} else {
						assetInfo = readContentXml
								.getAssetInfoByContent(new String(xmlValues));

						System.out.println("Lendo assetInfo: " + assetInfo);
						logger.info("Lendo assetInfo: " + assetInfo);

						assetFileName = readContentXml
								.getAssetTileByContent(new String(xmlValues));

						System.out.println("Lendo assetFileName: "
								+ assetFileName);
						logger.info("Lendo assetFileName: " + assetFileName);

						// Busca o registro na @IpvodIngestStage para verificar
						// se o mesmo ja foi executado e esta em outro stagetype
						// > 3
						ipvodIngestStage = new IpvodIngestStage();
						ipvodIngestStage = restClient
								.findDataByAssetInfo(assetInfo);
						// Se encontrou o registro e o stageType > 3 o arquivo
						// eh
						// renomeado para done e o processo finalizado.
						if (ipvodIngestStage.getId() != null
								&& ipvodIngestStage.getStageType().getId() > 3) {
							// Altera o fim do arquivo com done
							rename = fileFunctions.renameXML(file, ".done");
							if (!rename) {
								logger.error("Erro ao renomear o arquivo "
										+ file.getName() + " para done!");
							}
							break;
						}

						if (ipvodIngestStage.getId() == null) {
							// Verifica pelo assetInfo se o registro ja existe
							// na base e o result e igual a zero (erro) para
							// reprocessar
							ipvodIngestStage = new IpvodIngestStage();
							ipvodIngestStage = restClient
									.findDataWhitErrorByAssetInfo(assetInfo);
						}

						if (ipvodIngestStage.getId() != null) {
							logger.info("Atualizando os dados ID: "
									+ ipvodIngestStage.getId() + " - ReadXml.");
							// Atualiza registro
							restClient.updateStage(assetInfo,
									Util.actionFinishedOk(),
									Util.actionReadXml(), "READ XML OK",
									ipvodIngestStage, pathfile, assetFileName);
						}

						// Le o arquivo XML
						objectValues = new JSONArray();
						objectValues = readContentXml.readContent(new String(
								xmlValues));

						// Se encontrou as informacoes le os dados
						if (objectValues.length() > 0) {
							// Verifica o conteudo do XML
							listValidateFiles = new ArrayList<String>();
							listValidateFiles = readContentXml
									.findNameFiles(objectValues);

							// Verifica se os registros *.TS estao no diretorio
							if (!listValidateFiles.isEmpty()) {
								filesTSOk = fileFunctions
										.verifyMovieFilesFromPath(pathfile,
												listValidateFiles);
								// Se nao encontrou os arquivos *.TS no
								// diretorio, para a execucao
								if (!filesTSOk) {
									// Remove o registro criado na leitura do
									// xml da base
									if (ipvodIngestStage.getId() != null) {
										restClient.delete(ipvodIngestStage
												.getId());
									}
									break;
								}
							}

							// Valida os dados MD5
							md5IsValid = readContentXml.validateMD5(pathfile,
									objectValues);

							if (md5IsValid) {
								// Executa o proximo passo
								nextStep = true;
							} else {
								// Atualiza registro
								restClient.updateStage(assetInfo,
										Util.actionFinishedError(),
										Util.actionMd5Validate(), "MD5 ERROR",
										ipvodIngestStage, pathfile,
										assetFileName);

								// Altera o fim do arquivo com erro
								rename = fileFunctions
										.renameXML(file, ".error");

								// Nao deve executar o proximo passo
								nextStep = false;

								if (!rename) {
									logger.error("Erro ao renomear o arquivo "
											+ file.getName() + " para error!");
								}
							}
						}

						if (nextStep) {
							// Move os dados para o novo diretorio - Entrada do
							// 4Balancer
							finish = moveFiles.moveFilesToExecute(assetInfo,
									pathfile);
							if (finish) {
								// Grava dados Ingest - Import dos arquivos com
								// sucesso
								restClient.updateStage(assetInfo,
										Util.actionFinishedOk(),
										Util.actionImportXml(),
										Util.getPathFile() + assetInfo + "/",
										ipvodIngestStage, pathfile,
										assetFileName);
								// Altera o fim do arquivo com sucesso
								rename = fileFunctions.renameXML(file, ".done");
							} else {
								// Atualiza os dados Ingest - Import dos
								// arquivos com erro
								restClient.updateStage(assetInfo,
										Util.actionFinishedError(),
										Util.actionImportXml(),
										"Erro ao executar import",
										ipvodIngestStage, pathfile,
										assetFileName);
								// Altera o fim do arquivo com erro
								rename = fileFunctions
										.renameXML(file, ".error");
							}
						}
					}
				}
			} else {
				System.out.println("Have found no file for reading!");
				logger.info("Have found no file for reading!");
			}

			// Mostra o tempo de execucao no display
			System.out.println("Tempo Total processamento WatchFolder: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
			// Mostra o tempo de execucao
			logger.info("Tempo Total processamento WatchFolder: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
		} catch (Exception e) {
			logger.error("Erro ao executar watchfoler: ", e);
			System.out
					.println("Erro ao executar watchfoler: " + e.getMessage());
		}
		logger.info("ENDING THE WATCH FOLDER");
		System.out.println("STARTING THE WATCH FOLDER");
	}
}
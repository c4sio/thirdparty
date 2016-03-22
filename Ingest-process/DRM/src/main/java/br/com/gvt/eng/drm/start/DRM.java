package br.com.gvt.eng.drm.start;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.eng.drm.EncryptorDrm;
import br.com.gvt.eng.drm.RegisterDrm;
import br.com.gvt.eng.drm.file.FileFunctions;
import br.com.gvt.eng.drm.impl.EncryptorDrmImpl;
import br.com.gvt.eng.drm.impl.RegisterDrmImpl;
import br.com.gvt.eng.drm.rest.DrmDataRest;
import br.com.gvt.eng.drm.rest.IngestStageRest;
import br.com.gvt.eng.drm.rest.impl.DrmDataRestImpl;
import br.com.gvt.eng.drm.rest.impl.IngestStageRestImpl;
import br.com.gvt.eng.drm.util.StatusCodeDrmEnum;
import br.com.gvt.eng.drm.util.Util;
import br.com.gvt.eng.drm.vo.IpvodDrmData;
import br.com.gvt.eng.drm.vo.IpvodIngestStage;
import br.com.gvt.eng.drm.vo.IpvodMediaAsset;

import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut;

public class DRM {

	static Logger logger = Logger.getLogger(DRM.class.getName());

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");

			System.out.println(">> STARTING THE INTEGRATION DRM <<");
			logger.info("STARTING THE INTEGRATION DRM");

			List<IpvodDrmData> listDataDrm = new ArrayList<IpvodDrmData>();
			DrmDataRest drmDataRest = new DrmDataRestImpl();
			EncryptorDrm encryptorDrm = new EncryptorDrmImpl();
			RegisterDrm registerDrm = new RegisterDrmImpl();

			// 1) Busca dados para verificar - @IpvodDrmData
			listDataDrm = drmDataRest.findAllLessCompleted();
			logger.info("Encontrou " + listDataDrm.size()
					+ " registros para processar.");

			// Se a lista nao estiver vazia executa a consulta de status
			if (!listDataDrm.isEmpty()) {
				IngestStageRest ingestStageRest = new IngestStageRestImpl();
				IpvodIngestStage ipvodIngestStage = null;
				HeartbeatOut heartbeatOut = null;
				// Varre todos os registros
				for (IpvodDrmData ipvodDrmData : listDataDrm) {
					// 2) Verificar o status do arquivo - DRM
					heartbeatOut = encryptorDrm
							.checkStatusEncryptor(ipvodDrmData.getCookieDrm());

					// Se encontrou informacao atualiza os dados
					if (!Util.isEmptyOrNull(heartbeatOut.getStatusCode()
							.toString())) {
						// 3) Atualizar o status na base @IpvodDrmData
						switch (heartbeatOut.getStatusCode().intValue()) {
						case 3:
							logger.error("Erro ao executar DRM: "
									+ heartbeatOut.getStatusDetails());
							System.out.println("Erro ao executar DRM: "
									+ heartbeatOut.getStatusDetails());

							// Remove o cookie do DRM
							registerDrm.unRegisterClientByCookie(ipvodDrmData
									.getCookieDrm());
							// Remove da base os registros de arquivos com erro
							// para reenvio no DRM
							drmDataRest.delete(ipvodDrmData.getDrmId());
							break;
						case 4:
							// Atualiza os arquivos com sucesso
							ipvodDrmData.setStatusDrm(StatusCodeDrmEnum
									.porCodigo(
											heartbeatOut.getStatusCode()
													.intValue()).name());
							ipvodDrmData.setPercentCompDrm(new Double(100));
							drmDataRest.update(ipvodDrmData);

							// Remove o cookie do DRM
							registerDrm.unRegisterClientByCookie(ipvodDrmData
									.getCookieDrm());

							// Verifica se todos os registros foram processados
							// com sucesso antes de atualizar a Ingest
							if (drmDataRest.checkEndOfProcess(ipvodDrmData
									.getIpvodIngestStage().getId())) {
								// Atualiza o status da tabela Ingest
								ipvodIngestStage = new IpvodIngestStage();
								ipvodIngestStage = ingestStageRest
										.findIngestById(ipvodDrmData
												.getIpvodIngestStage().getId());
								ingestStageRest.updateIngest(ipvodIngestStage);
							}
							break;
						default:
							// Atualiza o status e percentual do job
							ipvodDrmData.setStatusDrm(StatusCodeDrmEnum
									.porCodigo(
											heartbeatOut.getStatusCode()
													.intValue()).name());
							ipvodDrmData.setPercentCompDrm(heartbeatOut
									.getPercentageComplete().doubleValue());
							drmDataRest.update(ipvodDrmData);
							break;
						}
					}
				}
			} else {
				// Infelizmente a plataforma DRM somente aceita um registro por
				// vez, sendo assim somente deve ler um novo registro quando a
				// execucao do anterior terminar

				// 4) Busca novos dados para processar - @IpvodIngestStage
				IngestStageRest ingestStageRest = new IngestStageRestImpl();
				List<IpvodIngestStage> listaData = new ArrayList<IpvodIngestStage>();
				List<IpvodIngestStage> listaDataFinal = null;

				// Busca dados com status = 5
				listaData = ingestStageRest.findFilesToExecute(Long
						.parseLong(Util.getStatusIniDrm()));

				// Variaveis
				IpvodDrmData ipvodDrmData = null;
				EncryptContentOut encryptContentOut = null;
				FileFunctions fileFunctions = new FileFunctions();
				List<File> listFiles = null;

				// Se a lista contem registros continua o processo de DRM
				if (!listaData.isEmpty()) {
					listaDataFinal = new ArrayList<IpvodIngestStage>(0);

					// Necessario esta logica para mandar apenas um registro
					// para a plataforma
					listaDataFinal.add(listaData.get(0));

					for (IpvodIngestStage ipvodIngest : listaDataFinal) {
						// Faz a leitura dos arquivos - movie/trailer
						for (IpvodMediaAsset ipvodMediaAsset : ipvodIngest
								.getIpvodAsset().getIpvodMediaAssets()) {

							// Iniciando as variaveis
							listFiles = new ArrayList<File>();

							switch ((int) ipvodMediaAsset.getIpvodMediaType()
									.getMediaTypeId()) {
							// Trailer deve ser movido para o Convoy e nao
							// enviado para o DRM
							case 2:
								// 5) Busca Arquivos de trailer no diretorio de
								// entrada do servidor
								listFiles = fileFunctions
										.searchFiles(ipvodIngest.getAssetInfo()
												+ "_"
												+ ipvodMediaAsset
														.getIpvodMediaType()
														.getDescription());

								// Verifica se encontrou algum arquivo
								if (!listFiles.isEmpty()) {
									for (File file : listFiles) {
										// Move todos os arquivos do diretorio
										fileFunctions
												.copyDirectory(
														new File(file
																.getParent()),
														new File(
																Util.getOutFilesTrailerPath()
																		+ "/"
																		+ ipvodIngest
																				.getIpvodAsset()
																				.getAssetId()
																		+ "_"
																		+ ipvodMediaAsset
																				.getIpvodMediaType()
																				.getDescription()));

										String fileOld = Util
												.getOutFilesTrailerPath()
												+ "/"
												+ ipvodIngest.getIpvodAsset()
														.getAssetId()
												+ "_"
												+ ipvodMediaAsset
														.getIpvodMediaType()
														.getDescription()
												+ "/"
												+ "index.m3u8";

										String fileNew = Util
												.getOutFilesTrailerPath()
												+ "/"
												+ ipvodIngest.getIpvodAsset()
														.getAssetId()
												+ "_"
												+ ipvodMediaAsset
														.getIpvodMediaType()
														.getDescription()
												+ "/"
												+ ipvodIngest.getIpvodAsset()
														.getAssetId()
												+ "_"
												+ ipvodMediaAsset
														.getIpvodMediaType()
														.getDescription()
												+ ".m3u8";

										// Renomear o arquivo movido
										new File(fileOld).renameTo(new File(
												fileNew));

										// Alterar as permissoes da pasta
										fileFunctions
												.changePermissions(new File(
														Util.getOutFilesTrailerPath()
																+ "/"
																+ ipvodIngest
																		.getIpvodAsset()
																		.getAssetId()
																+ "_"
																+ ipvodMediaAsset
																		.getIpvodMediaType()
																		.getDescription()));
									}
								} else {
									logger.error("Nao encontrou nenhum arquivo index.m3u8 na pasta de trailer "
											+ ipvodIngest.getAssetInfo()
											+ "_"
											+ ipvodMediaAsset
													.getIpvodMediaType()
													.getDescription());
								}
								break;
							// Cria o job para o movie
							case 3:
								// 5) Busca Arquivos de movie no diretorio de
								// entrada do servidor
								listFiles = fileFunctions
										.searchFiles(ipvodIngest.getAssetInfo()
												+ "_"
												+ ipvodMediaAsset
														.getIpvodMediaType()
														.getDescription());

								// Se encontrou algum registro executa a criacao
								// do JobId e grava os dados na @IpvodDrmData
								if (!listFiles.isEmpty()) {
									for (File file : listFiles) {
										// Inicializando o objeto
										encryptContentOut = new EncryptContentOut();
										// 6) Envia o arquivo para Criptografia
										// - DRM
										encryptContentOut = encryptorDrm
												.encryptFile(
														ipvodIngest,
														file,
														ipvodMediaAsset
																.getIpvodMediaType()
																.getDescription());

										// Populando o objeto DrmData
										if (!Util.isEmptyOrNull(String
												.valueOf(encryptContentOut
														.getJobNumber()))) {
											ipvodDrmData = new IpvodDrmData();
											ipvodDrmData
													.setJobIdDrm(encryptContentOut
															.getJobNumber()
															.toString());
											ipvodDrmData
													.setStatusDrm(StatusCodeDrmEnum
															.porCodigo(6)
															.name());
											ipvodDrmData
													.setIpvodIngestStage(ipvodIngest);
											ipvodDrmData
													.setPercentCompDrm(new Double(
															0));
											ipvodDrmData.setNameFile(file
													.getName());
											ipvodDrmData
													.setCookieDrm(encryptContentOut
															.getClientCookie()
															.toString());

											// 7) Grava dados na base
											// @IpvodDrmData
											drmDataRest
													.saveDrmData(ipvodDrmData);
										}
									}
								}
								break;
							default:
								break;
							}
						}
					}
				} else {
					System.out.println("Have found no file for reading!");
					logger.info("Have found no file for reading!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			logger.error("Erro no processamento Integration DRM: ", e);
		}
		System.out.println(">> ENDING THE INTEGRATION DRM <<");
		logger.info("ENDING THE INTEGRATION DRM");
	}
}
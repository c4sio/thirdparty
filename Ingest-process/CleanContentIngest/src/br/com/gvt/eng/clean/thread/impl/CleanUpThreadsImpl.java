package br.com.gvt.eng.clean.thread.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.gvt.eng.clean.file.FileFunctions;
import br.com.gvt.eng.clean.rest.IngestStageRest;
import br.com.gvt.eng.clean.rest.impl.IngestStageRestImpl;
import br.com.gvt.eng.clean.thread.CleanUpThreads;
import br.com.gvt.eng.clean.util.Util;
import br.com.gvt.eng.clean.vo.IpvodIngestStage;

public class CleanUpThreadsImpl implements CleanUpThreads {

	static Logger logger = Logger.getLogger(CleanUpThreadsImpl.class.getName());

	@Override
	public boolean executeCleanUp(List<IpvodIngestStage> listIpvodIngestStage) {
		boolean success = false;
		try {
			IngestStageRest ingestStageRest = new IngestStageRestImpl();
			for (IpvodIngestStage ipvodIngestStage : listIpvodIngestStage) {
				// Limpa as pastas nos diretorios
				CleanUpConvoy(ipvodIngestStage.getAssetId());
				CleanUpDrm(ipvodIngestStage.getAssetInfo());
				clean4balancer(ipvodIngestStage.getAdicionalInfo());
				cleanFtp(ipvodIngestStage.getFtpPath());
				// Atualiza os dados de @IpvodIngestStage
				ingestStageRest.updateIngest(ipvodIngestStage);
			}
			success = true;
		} catch (Exception e) {
			logger.error("Erro ao remover pastas 4Bbalancer ", e);
		}
		return success;
	}

	/**
	 * Remove diretorios do FTP
	 * 
	 * @param ftpPath
	 */
	private void cleanFtp(final String ftpPath) {
		System.out.println("\nREMOVE FTP START =============");
		try {
			Runnable cleanFtp = new Runnable() {
				@Override
				public void run() {
					System.out.println("Removendo diretorio: " + ftpPath);
					logger.info("Removendo diretorio: " + ftpPath);
					FileFunctions fileFunctions = new FileFunctions();
					try {
						fileFunctions.removeFiles(new File(ftpPath));
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("Pasta nao encontrada ", e);
					}
				}
			};

			Thread thCleanFtp = new Thread(cleanFtp);
			thCleanFtp.start();

			while (thCleanFtp.isAlive()) {
				// Espera terminar
			}
			System.out.println("REMOVE FTP END ===============\n");
		} catch (Exception e) {
			logger.error("Erro ao remover pastas Ftp ", e);
		}
	}

	/**
	 * Remove diretorios do 4Balancer
	 * 
	 * @param pathFile
	 */
	private void clean4balancer(final String pathFile) {
		System.out.println("\nREMOVE 4BALANCER START =============");
		try {
			Runnable cleanUp4Balancer = new Runnable() {
				@Override
				public void run() {
					System.out.println("Removendo diretorio: " + pathFile);
					logger.info("Removendo diretorio: " + pathFile);
					FileFunctions fileFunctions = new FileFunctions();
					try {
						fileFunctions.removeFiles(new File(pathFile));
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("Pasta nao encontrada ", e);
					}
				}
			};

			Thread thCleanUp4Balancer = new Thread(cleanUp4Balancer);
			thCleanUp4Balancer.start();

			while (thCleanUp4Balancer.isAlive()) {
				// Espera terminar
			}
			System.out.println("REMOVE 4BALANCER END ===============\n");
		} catch (Exception e) {
			logger.error("Erro ao remover pastas 4Bbalancer ", e);
		}
	}

	/**
	 * Remove diretorios do DRM
	 * 
	 * @param assetInfo
	 */
	private void CleanUpDrm(final String assetInfo) {
		System.out.println("REMOVE DRM START ===================");
		try {
			Runnable cleanUpDrm = new Runnable() {
				@Override
				public void run() {
					String[] typeFile = { "Movie", "Trailer" };
					FileFunctions fileFunctions = new FileFunctions();
					for (String valueType : typeFile) {
						System.out.println("Removendo diretorio: "
								+ Util.getPathDrm() + assetInfo + "_"
								+ valueType);
						logger.info("Removendo diretorio: " + Util.getPathDrm()
								+ assetInfo + "_" + valueType);
						try {
							fileFunctions
									.removeFiles(new File(Util.getPathDrm()
											+ assetInfo + "_" + valueType));
						} catch (IOException e) {
							e.printStackTrace();
							logger.error("Pasta nao encontrada ", e);
						}
					}
				}
			};

			Thread thCleanUpDrm = new Thread(cleanUpDrm);
			thCleanUpDrm.start();

			while (thCleanUpDrm.isAlive()) {
				// Espera terminar
			}
			System.out.println("REMOVE DRM END =====================\n");
		} catch (Exception e) {
			logger.error("Erro ao remover pastas DRM ", e);
		}
	}

	/**
	 * Remove diretorios do Convoy
	 * 
	 * @param assetId
	 */
	private void CleanUpConvoy(final Long assetId) {
		System.out.println("REMOVE CONVOY START ===================");
		try {
			Runnable cleanUpConvoy = new Runnable() {
				@Override
				public void run() {
					String[] typeFile = { "Movie", "Trailer" };
					FileFunctions fileFunctions = new FileFunctions();
					for (String valueType : typeFile) {
						System.out.println("Removendo diretorio: "
								+ Util.getPathConvoy() + assetId + "_"
								+ valueType);
						logger.info("Removendo diretorio: "
								+ Util.getPathConvoy() + assetId + "_"
								+ valueType);
						try {
							fileFunctions.removeFiles(new File(Util
									.getPathConvoy()
									+ assetId
									+ "_"
									+ valueType));
						} catch (IOException e) {
							e.printStackTrace();
							logger.error("Pasta nao encontrada ", e);
						}
					}
				}
			};

			Thread thConvoy = new Thread(cleanUpConvoy);
			thConvoy.start();

			while (thConvoy.isAlive()) {
				// Espera terminar
			}
			System.out.println("REMOVE CONVOY END =====================\n");
		} catch (Exception e) {
			logger.error("Erro ao remover pastas Convoy ", e);
		}
	}
}
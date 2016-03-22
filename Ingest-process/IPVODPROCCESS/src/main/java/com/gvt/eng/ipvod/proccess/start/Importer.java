package com.gvt.eng.ipvod.proccess.start;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import br.com.gvt.eng.vod.model.IpvodAsset;
import br.com.gvt.eng.vod.model.IpvodIngestStage;

import com.gvt.eng.ipvod.proccess.converter.AssetConverter;
import com.gvt.eng.ipvod.proccess.dao.AssetDAO;
import com.gvt.eng.ipvod.proccess.dao.IngestDAO;
import com.gvt.eng.ipvod.proccess.file.FileFunctions;
import com.gvt.eng.ipvod.proccess.util.Util;
import com.gvt.eng.ipvod.proccess.vo.PackAsset;

@Component
public class Importer {

	@Autowired
	private AssetDAO assetDao;

	@Autowired
	private IngestDAO ingestDao;

	static Logger logger = Logger.getLogger(Importer.class.getName());

	@SuppressWarnings("resource")
	public static void main(final String[] args) throws Exception {
		configureExternalFiles();
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				Util.getSpringConfig());
		logger.info("Arquivo de contexto inicializado.");
		Importer standalone = ctx.getBean(Importer.class);
		standalone.startStandalone();
	}

	private static void configureExternalFiles() {
		PropertyConfigurator.configure("log4j.properties");
	}

	private void startStandalone() {
		System.out.println("STARTING THE iPVoDbOx");
		logger.info("STARTING THE iPVoDbOx");

		try {
			// Variaveis
			PackAsset pack = new PackAsset();
			FileFunctions reader = new FileFunctions();
			AssetConverter assetConverter = new AssetConverter();
			String assetId = null;
			IpvodAsset ipvodAsset = null;
			List<File> xmls = null;
			StringBuffer xml = null;

			// Busca registros na @IpvodIngestStage
			List<IpvodIngestStage> listIngest = new ArrayList<IpvodIngestStage>();
			listIngest = ingestDao.findFilesToExecute();

			// Executa as acoes se encontrou registros
			if (!listIngest.isEmpty()) {
				for (IpvodIngestStage ipvodIngestStage : listIngest) {
					logger.info("Buscando os arquivos no caminho: "
							+ ipvodIngestStage.getAdicionalInfo());
					xmls = new ArrayList<File>();
					xmls = reader.searchFiles(ipvodIngestStage
							.getAdicionalInfo());
					logger.info("XML founds to import: " + xmls.size());
					System.out.println("XML founds to import: " + xmls.size());

					if (!xmls.isEmpty()) {
						for (File file : xmls) {
							System.out.println("Lendo arquivo: "
									+ file.getAbsolutePath());
							logger.info("Lendo arquivo: "
									+ file.getAbsolutePath());
							try {
								xml = new StringBuffer();
								xml = reader.readXmlFileContent(file);
								pack = new PackAsset();
								assetConverter.jsonToAssetPack(xml, pack);
								assetId = pack.getMetadata().getAMS()
										.getAsset_ID();
								// assetId = pack.getMetadata().getAMS()
								// .getAsset_Name();
								ipvodAsset = new IpvodAsset();
								ipvodAsset = assetConverter.toIpvodAsset(pack,
										file);

								logger.info("Encontrou "
										+ Util.getError().size()
										+ " erros no arquivo " + file.getName());

								if (Util.getError().size() > 0) {
									reader.createFileError(file,
											Util.getError());
									reader.renameXMLToXMLError(file);
								} else {
									if (assetDao.createAsset(ipvodAsset)) {
										reader.renameXMLToXMLDone(file);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								logger.error("Erro  ao efetuar o importe do arquivo: "
										+ e);
								reader.createFileError(file, Util.getError());
								reader.renameXMLToXMLError(file);
							} finally {
								Util.getError().clear();
							}
						}
						// Copia os arquivos para a pasta de IPVOD
						reader.copyJPGFiles(assetId,
								ipvodIngestStage.getAdicionalInfo());
					} else {
						System.out
								.println("Have found no file for reading in path "
										+ ipvodIngestStage.getAdicionalInfo());
						logger.info("Have found no file for reading in path "
								+ ipvodIngestStage.getAdicionalInfo());
					}
				}
			} else {
				System.out.println("Have found no file for reading!");
				logger.info("Have found no file for reading!");
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivos: " + e);
		}
		logger.info("ENDING THE iPVoDbOx");
		System.out.println("ENDING THE iPVoDbOx");
	}
}
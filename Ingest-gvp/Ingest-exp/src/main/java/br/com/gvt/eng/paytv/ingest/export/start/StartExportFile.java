package br.com.gvt.eng.paytv.ingest.export.start;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.eng.paytv.ingest.export.facade.AssetFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.export.facade.FileFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.export.facade.impl.AssetFunctionsFacadeImpl;
import br.com.gvt.eng.paytv.ingest.export.facade.impl.FileFunctionsFacadeImpl;
import br.com.gvt.eng.paytv.ingest.export.utils.StatusImportXLSConstants;
import br.com.gvt.eng.paytv.ingest.export.vo.IngestAssetVO;

public class StartExportFile {

	static Logger logger = Logger.getLogger(StartExportFile.class.getName());

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");
			StartExportFile startExportFile = new StartExportFile();
			startExportFile.execute();
		} catch (Exception e) {
			logger.error(
					"Erro ao inicializar processamento Ingest Export Files: ",
					e);
		}
	}

	/**
	 * 
	 */
	private void execute() {
		System.out
				.println(">> STARTING THE INTEGRATION INGEST - EXPORT FILES <<");
		logger.info("STARTING THE INTEGRATION INGEST - EXPORT FILES");

		try {
			// Variavel utilizada para calcular o tempo de execucao
			long tempoInicio = System.currentTimeMillis();

			List<IngestAssetVO> listAssets = new ArrayList<IngestAssetVO>();
			AssetFunctionsFacade assetFunctionsFacade = new AssetFunctionsFacadeImpl();
			FileFunctionsFacade fileFunctionsFacade = new FileFunctionsFacadeImpl();
			// Lista registros para exportar
			listAssets = assetFunctionsFacade
					.findByStatus(StatusImportXLSConstants.XML_READY);

			logger.info("Encontrou " + listAssets.size()
					+ " registros para exportar.");
			System.out.println("Encontrou " + listAssets.size()
					+ " registros para exportar.");

			if (!listAssets.isEmpty()) {
				boolean isCreate = false;
				for (IngestAssetVO ingestAssetVO : listAssets) {
					isCreate = fileFunctionsFacade
							.createXmlLocal(ingestAssetVO);
					if (isCreate) {
						ingestAssetVO
								.setStatus(StatusImportXLSConstants.XML_GENERATED);
						assetFunctionsFacade.update(ingestAssetVO);
					}
				}
			}

			// Mostra o tempo de execucao no display
			System.out
					.println("Tempo Total processamento Ingest Export Files: "
							+ new SimpleDateFormat("mm:ss").format(new Date(
									System.currentTimeMillis() - tempoInicio)));
			// Mostra o tempo de execucao
			logger.info("Tempo Total processamento Ingest Export Files: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			logger.error("Erro no processamento Ingest Export Files: ", e);
		}

		System.out
				.println(">> ENDING THE INTEGRATION INGEST - EXPORT FILES <<");
		logger.info("ENDING THE INTEGRATION INGEST - EXPORT FILES");
	}
}

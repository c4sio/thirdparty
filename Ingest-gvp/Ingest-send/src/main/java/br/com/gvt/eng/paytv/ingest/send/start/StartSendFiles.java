package br.com.gvt.eng.paytv.ingest.send.start;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.gvt.eng.paytv.ingest.send.facade.AssetFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.send.facade.CommandsFacade;
import br.com.gvt.eng.paytv.ingest.send.facade.impl.AssetFunctionsFacadeImpl;
import br.com.gvt.eng.paytv.ingest.send.facade.impl.CommandsFacadeImpl;
import br.com.gvt.eng.paytv.ingest.send.utils.StatusImportXLSConstants;
import br.com.gvt.eng.paytv.ingest.send.vo.IngestAssetVO;

public class StartSendFiles {

	static Logger logger = Logger.getLogger(StartSendFiles.class.getName());

	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");
			StartSendFiles startSendFiles = new StartSendFiles();
			startSendFiles.execute();
		} catch (Exception e) {
			logger.error(
					"Erro ao inicializar processamento Ingest Send Files: ", e);
		}
	}

	private void execute() {
		System.out
				.println(">> STARTING THE INTEGRATION INGEST - SEND FILES <<");
		logger.info("STARTING THE INTEGRATION INGEST - SEND FILES");
		try {
			// Variavel utilizada para calcular o tempo de execucao
			long tempoInicio = System.currentTimeMillis();

			CommandsFacade CommandsFacade = new CommandsFacadeImpl();
			AssetFunctionsFacade assetFunctionsFacade = new AssetFunctionsFacadeImpl();
			List<IngestAssetVO> listAssets = new ArrayList<IngestAssetVO>();

			// Lista registros para exportar
			listAssets = assetFunctionsFacade
					.findByStatus(StatusImportXLSConstants.XML_GENERATED);

			logger.info("Encontrou " + listAssets.size()
					+ " registros para enviar.");
			System.out.println("Encontrou " + listAssets.size()
					+ " registros para enviar.");

			if (!listAssets.isEmpty()) {
				boolean sendOk = false;
				for (IngestAssetVO ingestAssetVO : listAssets) {
					// TODO: UTILIZAR ESTA CHAMADA PARA O NOVO PROCESSO - VIA
					// NOVOS VALORES - NECESSÁRIO ALTERAR O BATCH sendASCP.sh -
					// Em Desenvolvimento
					// sendOk = CommandsFacade.sendFilesToBatch(ingestAssetVO);

					// Envia arquivos para servidor Ingest - VIA BATCH -
					// utilizado atualmente - Comentar este trecho para efetuar
					// o teste da nova chamada
					sendOk = CommandsFacade.sendFilesExec(ingestAssetVO);
					
					
					if (sendOk) {
						// Atualiza o Status do registro
						ingestAssetVO
								.setStatus(StatusImportXLSConstants.FILES_SEND);
						assetFunctionsFacade.update(ingestAssetVO);
					} else {
						System.out
								.println("Erro ao enviar arquivos para Aspera - "
										+ ingestAssetVO.getIngestFolder()
												.getUrlRootOut());
						logger.error("Erro ao enviar arquivos para Aspera - "
								+ ingestAssetVO.getIngestFolder()
										.getUrlRootOut());
					}
				}
			}

			// Mostra o tempo de execucao no display
			System.out.println("Tempo Total processamento Ingest Send Files: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
			// Mostra o tempo de execucao
			logger.info("Tempo Total processamento Ingest Send Files: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			logger.error("Erro no processamento Ingest Send Files: ", e);
		}
		System.out.println(">> ENDING THE INTEGRATION INGEST - SEND FILES <<");
		logger.info("ENDING THE INTEGRATION INGEST - SEND FILES");
	}
}
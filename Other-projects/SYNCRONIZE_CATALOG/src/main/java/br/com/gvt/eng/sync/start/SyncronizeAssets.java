package br.com.gvt.eng.sync.start;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import br.com.gvt.eng.sync.dao.AssetSyncDAO;

/**
 * @author GVT
 * 
 */
@Component
public class SyncronizeAssets {

	static Logger logger = Logger.getLogger(SyncronizeAssets.class.getName());

	@Autowired
	private AssetSyncDAO assetSyncDAO;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("Arquivo de log inicializado.");
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"classpath*:/spring-config.xml");
			logger.info("Arquivo de contexto inicializado.");
			// inicializando o construtor
			SyncronizeAssets syncronize = ctx.getBean(SyncronizeAssets.class);
			syncronize.execute();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao inicializar processamento Syncronize: ", e);
		}
	}

	/**
	 * Executa a leitura e import dos dados.
	 */
	private void execute() {
		System.out.println(">> STARTING THE SYNCRONIZE <<");
		logger.info("STARTING THE SYNCRONIZE");
		try {
			// Variavel utilizada para calcular o tempo de execucao
			long tempoInicio = System.currentTimeMillis();

			// Executa o metodo de busca e insert
			boolean toFinish = assetSyncDAO.findAndExecute();

			// Verifica se terminou com sucesso
			if (toFinish) {
				logger.info("Executado com sucesso!");
			} else {
				logger.info("Erro na execucao!");
			}

			// Mostra o tempo de execucao no display
			System.out.println("Tempo Total processamento Syncronize: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));

			// Mostra o tempo de execucao
			logger.info("Tempo Total processamento Syncronize: "
					+ new SimpleDateFormat("mm:ss").format(new Date(System
							.currentTimeMillis() - tempoInicio)));
		} catch (Exception e) {
			logger.error("Erro ao executar o processo de sincronizacao: " + e);
		}
		System.out.println(">> ENDING THE SYNCRONIZE <<");
		logger.info("ENDING THE SYNCRONIZE");
	}
}
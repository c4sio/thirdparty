package br.com.gvt.eng.sync.elasticsearch;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author GVT
 * 
 */
public class ConectionElasticSearch {

	static Logger logger = Logger.getLogger(ConectionElasticSearch.class
			.getName());

	// Variavel para identificar a host de conexao
	private static final String HOST = "127.0.0.1";

	// Variavel para identificar a porta de conexao - TransportClient
	private static final int PORT = 9300;

	/**
	 * Classe responsavel pela conexao com o servidor do elasticsearch.
	 * 
	 * @return connection
	 */
	@SuppressWarnings("resource")
	public static Client getConnection() {
		Client client = null;
		try {
			// Propriedades da conexao
			Settings settings = ImmutableSettings.settingsBuilder()
					.put("client.transport.sniff", true).build();

			// Efetua a conexao
			client = new TransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(HOST,
							PORT));

			logger.info(String.format("Setup transport client %s : %d", HOST,
					PORT));
		} catch (Exception e) {
			logger.error("Erro ao executar conexao com host: " + HOST, e);
		}
		return client;
	}
}
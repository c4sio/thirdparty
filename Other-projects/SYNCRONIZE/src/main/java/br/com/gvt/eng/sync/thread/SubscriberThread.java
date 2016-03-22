package br.com.gvt.eng.sync.thread;

import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;

import br.com.gvt.eng.sync.model.Subscriber;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author GVT
 * 
 */
public class SubscriberThread implements Runnable {

	Logger logger = Logger.getLogger(SubscriberThread.class.getName());

	// Variavel para identificar o client elasticsearch
	private Client client;

	// Variavel para identificar o entity
	private List<Subscriber> listSubscribers;

	/**
	 * Cria o construtor com os dados para leitura, recebe a lista de dados e a
	 * conex�o com o servidor do elasticsearch.
	 * 
	 * @param listSubscribers
	 * @param client
	 */
	public SubscriberThread(List<Subscriber> listSubscribers, Client client) {
		this.listSubscribers = listSubscribers;
		this.client = client;
	}

	public void run() {
		try {
			logger.info("Registros na thread: " + listSubscribers.size());

			// Instancia o Gson
			Gson gson = new GsonBuilder().serializeNulls().create();
			String json = null;
			BulkRequestBuilder bulkBuilder = client.prepareBulk();

			for (Subscriber subs : listSubscribers) {
				// Carrega os dados para execucaoo em lote
				json = gson.toJson(subs);
				bulkBuilder.add(client.prepareIndex("portal", "users",
						subs.getId()).setSource(json));
			}

			// Grava os dados
			BulkResponse bulkRes = bulkBuilder.execute().actionGet();

			// Grava o log caso de erro na execucao
			if (bulkRes.hasFailures()) {
				logger.error(String.format("Error indexing file %s",
						bulkRes.buildFailureMessage()));
			}

		} catch (Exception e) {
			logger.error("Erro ao gravar dados ", e);
		}
	}
}

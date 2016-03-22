package br.com.gvt.eng.sync.thread;

import java.util.List;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;

import br.com.gvt.eng.sync.model.AssetSyncVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author GVT
 * 
 */
public class AssetSyncThread implements Runnable {

	Logger logger = Logger.getLogger(AssetSyncThread.class.getName());

	// Variavel para identificar o client elasticsearch
	private Client client;

	// Variavel para identificar o entity
	private List<AssetSyncVO> assetList;

	/**
	 * Cria o construtor com os dados para leitura, recebe a lista de dados e a
	 * conexao com o servidor do elasticsearch.
	 * 
	 * @param assetList
	 * @param client
	 */
	public AssetSyncThread(List<AssetSyncVO> assetList, Client client) {
		this.assetList = assetList;
		this.client = client;
	}

	public void run() {
		try {
			logger.info("Registros na thread: " + assetList.size());

			// Instancia o Gson
			Gson gson = new GsonBuilder().serializeNulls().create();
			String json = null;
			BulkRequestBuilder bulkBuilder = client.prepareBulk();

			for (AssetSyncVO asset : assetList) {
				// Carrega os dados para execucaoo em lote
				json = gson.toJson(asset);
				bulkBuilder.add(client.prepareIndex("catalog", "asset",
						String.valueOf(asset.getAssetId())).setSource(json));
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

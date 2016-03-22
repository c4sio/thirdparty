package br.com.gvt.eng.sync.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gvt.eng.sync.dao.AssetSyncDAO;
import br.com.gvt.eng.sync.elasticsearch.ConnectionElasticSearch;
import br.com.gvt.eng.sync.model.AssetSyncVO;
import br.com.gvt.eng.sync.thread.AssetSyncThread;

/**
 * @author GVT
 * 
 */
@Repository
@Transactional
public class AssetSyncDAOImpl implements AssetSyncDAO {

	Logger logger = Logger.getLogger(AssetSyncDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public boolean findAndExecute() {

		logger.info("Iniciando o processo de leitura e gravacao dos registros.");

		// Variavel pool de threads
		ExecutorService threadPool = null;

		// Abre conexao para gravacao de dados
		Client client = ConnectionElasticSearch.getConnection();

		try {
			// NUmero de threads serao criadas dentro do thread pool
			int tamanhoDoPool = 15;

			// Cria o numero de threads para gerenciamento
			threadPool = Executors.newFixedThreadPool(tamanhoDoPool);

			// Array para executar multiplas tarefas
			AssetSyncThread tarefas = null;

			// Busca a sessao
			Session session = sessionFactory.getCurrentSession();
			
			// Busca numero de registros na base
			long countNumRegisters = (Long) session.createQuery(
					"SELECT COUNT(a) FROM AssetSyncVO a").uniqueResult();

			System.out.println("Encontrou " + countNumRegisters
					+ " para processar!");
			logger.info("Encontrou " + countNumRegisters + " para processar!");
			logger.info("Numero de Threads " + tamanhoDoPool);

			// Efetuando a consulta na base
			ScrollableResults scrollableResults = session
					.createQuery("from AssetSyncVO")
					.setFetchSize(2000)
					.setCacheable(false).setReadOnly(true)
					.scroll(ScrollMode.FORWARD_ONLY);
			
			// Variaveis auxiliares
			List<AssetSyncVO> listAssets= new ArrayList<AssetSyncVO>();
			int regLidos = 0;
			int toReadForThread = (int) (countNumRegisters / tamanhoDoPool);

			// Verifica o numero de registros, caso menor que o tamanho de
			// threads modifica o numero de threads
			if (tamanhoDoPool > countNumRegisters) {
				toReadForThread = (int) countNumRegisters;
			}

			// Lendo o resultSet
			while (scrollableResults.next()) {
				// Carregando o objeto 
				AssetSyncVO asset = (AssetSyncVO) scrollableResults.get(0);

				// Adiciona os registros a lista para leitura na thread
				listAssets.add(asset);

				// Soma os registros lidos para validacao
				regLidos++;

				// Envia os dados para a execucao na thread
				if (listAssets.size() == toReadForThread
						|| (regLidos == countNumRegisters && listAssets
								.size() < toReadForThread)) {
					// Instanciando a thread
					tarefas = new AssetSyncThread(listAssets, client);
					// Adicionar a thread no thread pool
					threadPool.execute(tarefas);
					// Instancia uma nova lista
					listAssets = new ArrayList<AssetSyncVO>();
				}
				// Remove o objeto do cache
				session.evict(asset);
			}
			logger.info("Registros lidos: " + regLidos);
		} catch (Exception e) {
			logger.error("Erro na leitura e processamento dos dados ", e);
			e.printStackTrace();
			return false;
		} finally {
			try {
				// Desativar o threadpool
				threadPool.shutdown();
				while (!threadPool.isTerminated()) {
					// Espera ate que todas as threads terminem
				}
				// Fecha a gravacao de dados
				client.close();
			} catch (Exception ex) {
				logger.error("Erro ao encerrar as threads ", ex);
			}
		}
		logger.info("Finalizado o processo de leitura e processamento dos registros.");
		return true;
	}
}

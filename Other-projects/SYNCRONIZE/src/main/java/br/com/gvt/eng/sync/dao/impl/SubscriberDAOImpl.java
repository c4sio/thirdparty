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

import br.com.gvt.eng.sync.dao.SubscriberDAO;
import br.com.gvt.eng.sync.elasticsearch.ConectionElasticSearch;
import br.com.gvt.eng.sync.model.Subscriber;
import br.com.gvt.eng.sync.thread.SubscriberThread;

/**
 * @author GVT
 * 
 */
@Repository
@Transactional
public class SubscriberDAOImpl implements SubscriberDAO {

	Logger logger = Logger.getLogger(SubscriberDAOImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	public boolean findAndExecuteSubscriber() {

		logger.info("Iniciando o processo de leitura e gravacao dos registros.");

		// Variavel pool de threads
		ExecutorService threadPool = null;

		// Abre conexao para gravacao de dados
		Client client = ConectionElasticSearch.getConnection();

		try {
			// NUmero de threads serao criadas dentro do thread pool
			int tamanhoDoPool = 15;

			// Cria o numero de threads para gerenciamento
			threadPool = Executors.newFixedThreadPool(tamanhoDoPool);

			// Array para executar multiplas tarefas
			SubscriberThread tarefas = null;

			// Busca a sessao
			Session session = sessionFactory.getCurrentSession();

			// Busca numero de registros na base
			long countNumRegisters = (Long) session.createQuery(
					"SELECT COUNT(e) FROM Subscriber e").uniqueResult();

			System.out.println("Encontrou " + countNumRegisters
					+ " para processar!");
			logger.info("Encontrou " + countNumRegisters + " para processar!");
			logger.info("Numero de Threads " + tamanhoDoPool);

			// Efetuando a consulta na base
			ScrollableResults scrollableResults = session
					.createQuery("from Subscriber").setFetchSize(2000)
					.setCacheable(false).setReadOnly(true)
					.scroll(ScrollMode.FORWARD_ONLY);

			// Variaveis auxiliares
			List<Subscriber> listSubscribers = new ArrayList<Subscriber>();
			int regLidos = 0;
			int toReadForThread = (int) (countNumRegisters / tamanhoDoPool);

			// Verifica o numero de registros, caso menor que o tamanho de
			// threads modifica o numero de threads
			if (tamanhoDoPool > countNumRegisters) {
				toReadForThread = (int) countNumRegisters;
			}

			// Lendo o resultSet
			while (scrollableResults.next()) {
				// Carregando o objeto Subscriber
				Subscriber subscriber = (Subscriber) scrollableResults.get(0);

				// Adiciona os registros a lista para leitura na thread
				listSubscribers.add(subscriber);

				// Soma os registros lidos para validacao
				regLidos++;

				// Envia os dados para a execucao na thread
				if (listSubscribers.size() == toReadForThread
						|| (regLidos == countNumRegisters && listSubscribers
								.size() < toReadForThread)) {
					// Instanciando a thread
					tarefas = new SubscriberThread(listSubscribers, client);
					// Adicionar a thread no thread pool
					threadPool.execute(tarefas);
					// Instancia uma nova lista
					listSubscribers = new ArrayList<Subscriber>();
				}
				// Remove o objeto do cache
				session.evict(subscriber);
			}
			logger.info("Registros lidos: " + regLidos);
		} catch (Exception e) {
			logger.error("Erro na leitura e processamento dos dados ", e);
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

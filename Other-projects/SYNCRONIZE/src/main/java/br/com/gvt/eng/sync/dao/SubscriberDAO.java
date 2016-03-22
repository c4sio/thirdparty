package br.com.gvt.eng.sync.dao;

import javax.ejb.Local;

/**
 * @author GVT
 * 
 */
@Local
public interface SubscriberDAO {

	/**
	 * Busca dados tabela e executada o processamento em threads.
	 * 
	 * @return boolean
	 */
	public boolean findAndExecuteSubscriber();
}

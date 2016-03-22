package br.com.gvt.eng.clean.thread;

import java.util.List;

import br.com.gvt.eng.clean.vo.IpvodIngestStage;

public interface CleanUpThreads {

	/**
	 * Remove pastas dos diretorios
	 * 
	 * @param listIpvodIngestStage
	 * @return boolean
	 */
	public abstract boolean executeCleanUp(
			List<IpvodIngestStage> listIpvodIngestStage);

}

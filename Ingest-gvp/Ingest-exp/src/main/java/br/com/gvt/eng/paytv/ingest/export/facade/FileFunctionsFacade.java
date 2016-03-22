package br.com.gvt.eng.paytv.ingest.export.facade;

import br.com.gvt.eng.paytv.ingest.export.vo.IngestAssetVO;

public interface FileFunctionsFacade {

	/**
	 * Cria o arquivo XML no servidor de aplicacao
	 * 
	 * @param ingestAssetVO
	 * @return boolean
	 */
	public abstract boolean createXmlByService(IngestAssetVO ingestAssetVO);

	/**
	 * Cria o arquivo XML no local de execucao do processo (*.jar)
	 * 
	 * @param ingestAssetVO
	 * @return boolean
	 */
	public abstract boolean createXmlLocal(IngestAssetVO ingestAssetVO);

}

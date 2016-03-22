package br.com.gvt.eng.paytv.ingest.send.facade;

import java.io.IOException;

import br.com.gvt.eng.paytv.ingest.send.vo.IngestAssetVO;

public interface CommandsFacade {

	/**
	 * Envia dados para servidor Ingest
	 * 
	 * @param ingestAssetVO
	 * @return boolean
	 * @throws IOException
	 */
	public abstract boolean sendFiles(IngestAssetVO ingestAssetVO)
			throws IOException, InterruptedException;

	/**
	 * Envia dados para servidor Ingest
	 * 
	 * @param ingestAssetVO
	 * @return boolean
	 */
	public abstract boolean sendFilesExec(IngestAssetVO ingestAssetVO);

	/**
	 * Envia dados para servidor Ingest
	 * 
	 * @param ingestAssetVO
	 * @return boolean
	 */
	public abstract boolean sendFilesToBatch(IngestAssetVO ingestAssetVO);

}

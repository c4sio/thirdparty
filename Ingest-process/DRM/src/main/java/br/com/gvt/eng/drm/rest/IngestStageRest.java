package br.com.gvt.eng.drm.rest;

import java.util.List;

import br.com.gvt.eng.drm.vo.IpvodIngestStage;

public interface IngestStageRest {

	/**
	 * Busca dados na tabela IPVOD_INGEST_STAGE qua ainda não foram criptografados
	 * 
	 * @return List<IpvodIngestStage>
	 */
	public abstract List<IpvodIngestStage> findFilesToExecute(long stageId);

	/**
	 * Atualiza dados na tabela IPVOD_INGEST_STAGE
	 * 
	 * @param ipvodIngestStage
	 */
	public abstract void updateIngest(IpvodIngestStage ipvodIngestStage);

	/**
	 * Busca dados na tabela IPVOD_INGEST_STAGE
	 * 
	 * @param ingestId
	 * 
	 * @return IpvodIngestStage
	 */
	public abstract IpvodIngestStage findIngestById(long ingestId);

}

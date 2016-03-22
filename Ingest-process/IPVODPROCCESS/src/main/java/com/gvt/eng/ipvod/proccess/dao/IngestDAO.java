package com.gvt.eng.ipvod.proccess.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodIngestStage;

@Local
public interface IngestDAO {

	public IpvodIngestStage findIngestByAssetInfo(String assetInfo);

	public void update(IpvodIngestStage ipvodIngestStage);

	/**
	 * Busca dados na tabela IPVOD_INGEST_STAGE que estao com status igual a
	 * ENCODING (3)
	 * 
	 * @return List<IpvodIngestStage>
	 */
	public abstract List<IpvodIngestStage> findFilesToExecute();

}

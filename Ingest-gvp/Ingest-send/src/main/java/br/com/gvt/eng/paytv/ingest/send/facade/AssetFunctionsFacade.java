package br.com.gvt.eng.paytv.ingest.send.facade;

import java.util.List;

import br.com.gvt.eng.paytv.ingest.send.vo.IngestAssetVO;

public interface AssetFunctionsFacade {
	
	/**
	 * Busca dados para processar
	 * 
	 * @param status
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> findByStatus(String status);

	/**
	 * Atualiza dados Asset
	 * 
	 * @param ingestAssetVO
	 * @return IngestAssetVO
	 */
	public abstract IngestAssetVO update(IngestAssetVO ingestAssetVO);

}

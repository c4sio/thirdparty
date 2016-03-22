package br.com.gvt.eng.paytv.ingest.facade;

import java.util.List;

import javax.ejb.Local;

import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Local
public interface IngestAssetFacade {

	/**
	 * @param IngestAssetVO
	 */
	public abstract IngestAssetVO save(IngestAssetVO ingestAssetVO);

	/**
	 * @param ingestAsset
	 * @return IngestAssetVO
	 */
	public abstract IngestAssetVO update(IngestAssetVO ingestAssetVO);

	/**
	 * @param entityID
	 * @return IngestAssetVO
	 */
	public abstract IngestAssetVO find(Long entityID);

	/**
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> findAll();

	/**
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> findReadyToSend();

	/**
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> findByStatus(String status);

	/**
	 * @param list
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> save(List<IngestAssetVO> list);

}

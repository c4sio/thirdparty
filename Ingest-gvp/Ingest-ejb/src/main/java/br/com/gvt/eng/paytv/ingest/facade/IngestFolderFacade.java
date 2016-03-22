package br.com.gvt.eng.paytv.ingest.facade;

import java.util.List;

import javax.ejb.Local;

import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

@Local
public interface IngestFolderFacade {

	/**
	 * @param mediaAsset
	 */
	public abstract IngestFolderVO save(IngestFolderVO folderVO);

	/**
	 * @param mediaAsset
	 */
	public abstract void delete(IngestFolderVO folderVO);

	/**
	 * @param ipvodAsset
	 * @return IngestMediaAssetVO
	 */
	public abstract IngestFolderVO update(IngestFolderVO folderVO);

	/**
	 * @param entityID
	 * @return IngestMediaAssetVO
	 */
	public abstract IngestFolderVO find(Long entityID);

	/**
	 * @return List<IngestMediaAssetVO>
	 */
	public abstract List<IngestFolderVO> findAll();

	/**
	 * @param folderID
	 */
	public abstract void removeFolder(Long folderID);

}

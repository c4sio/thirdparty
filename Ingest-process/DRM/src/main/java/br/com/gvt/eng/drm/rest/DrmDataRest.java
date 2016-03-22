package br.com.gvt.eng.drm.rest;

import java.util.List;

import br.com.gvt.eng.drm.vo.IpvodDrmData;

public interface DrmDataRest {

	/**
	 * Salva os dados na base
	 * 
	 * @param ipvodDrmData
	 */
	public abstract void saveDrmData(IpvodDrmData ipvodDrmData);

	/**
	 * @param ipvodDrmData
	 * @return IpvodDrmData
	 */
	public abstract IpvodDrmData update(IpvodDrmData ipvodDrmData);

	/**
	 * Remove registro da base
	 * 
	 * @param ipvodDrmData
	 */
	public abstract void delete(Long drmId);

	/**
	 * Busca registros dos dados enviados para o DRM que ainda nao foram
	 * finalizados
	 * 
	 * @return List<IpvodDrmData>
	 */
	public abstract List<IpvodDrmData> findAllLessCompleted();

	/**
	 * Busca dados de DRM na base
	 * 
	 * @param entityID
	 * @return IpvodDrmData
	 */
	public abstract IpvodDrmData find(Long entityID);

	/**
	 * Busca dados de DRM na base com o mesmo IngestID
	 * 
	 * @param ingestID
	 * @return boolean
	 */
	public abstract boolean checkEndOfProcess(Long ingestID);

}

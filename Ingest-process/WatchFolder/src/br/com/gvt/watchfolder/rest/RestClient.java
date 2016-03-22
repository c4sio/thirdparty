package br.com.gvt.watchfolder.rest;

import br.com.gvt.watchfolder.vo.IpvodIngestStage;

public interface RestClient {

	/**
	 * Salva dados @IpvodIngestStage
	 * 
	 * @param adicionalInfo
	 * @param assetInfo
	 * @param result
	 * @param stage
	 * @param ftpPathfile
	 * @param assetName
	 * @return IpvodIngestStage
	 */
	public abstract IpvodIngestStage saveIngest(String adicionalInfo,
			String assetInfo, int result, int stage, String ftpPathfile,
			String assetName);

	/**
	 * Atualiza os dados de @IpvodIngestStage
	 * 
	 * @param assetInfo
	 * @param result
	 * @param stage
	 * @param adicionalInfo
	 * @param ipvodIngestStage
	 * @param ftpPathfile
	 * @param assetName
	 */
	public abstract void updateStage(String assetInfo, int result, int stage,
			String adicionalInfo, IpvodIngestStage ipvodIngestStage,
			String ftpPathfile, String assetName);

	/**
	 * Busca dados de @IpvodIngestStage pelo assetInfo onde result igual a ZERO
	 * 
	 * @param assetInfo
	 * @return IpvodIngestStage
	 */
	public IpvodIngestStage findDataWhitErrorByAssetInfo(String assetInfo);

	/**
	 * Busca dados de @IpvodIngestStage pelo filePath
	 * 
	 * @param filePath
	 * @return IpvodIngestStage
	 */
	public IpvodIngestStage findDataByFilePath(String filePath);

	/**
	 * Remove registro da base
	 * 
	 * @param ingestId
	 */
	public abstract void delete(Long ingestId);

	/**
	 * Busca registro na @IpvodIngestStage para verificar se o stageType e > 3
	 * 
	 * @param assetInfo
	 * @return IpvodIngestStage
	 */
	public abstract IpvodIngestStage findDataByAssetInfo(String assetInfo);

}

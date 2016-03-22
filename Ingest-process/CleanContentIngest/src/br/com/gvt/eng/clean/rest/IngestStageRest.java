package br.com.gvt.eng.clean.rest;

import java.util.List;

import br.com.gvt.eng.clean.vo.IpvodIngestStage;

public interface IngestStageRest {

	/**
	 * Busca dados na tabela @IpvodIngestStage que estao com status igual a 7
	 * 
	 * @param stageId
	 * @return List<IpvodIngestStage>
	 */
	public abstract List<IpvodIngestStage> findFilesForCleanUp();

	/**
	 * Atualiza dados na tabela @IpvodIngestStage
	 * 
	 * @param ipvodIngestStage
	 */
	public abstract void updateIngest(IpvodIngestStage ipvodIngestStage);

}
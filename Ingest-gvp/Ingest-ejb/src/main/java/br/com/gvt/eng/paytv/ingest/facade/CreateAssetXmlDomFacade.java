package br.com.gvt.eng.paytv.ingest.facade;

import java.io.File;

import javax.ejb.Local;

import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Local
public interface CreateAssetXmlDomFacade {

	/**
	 * @param outFile
	 * @param ingestAsset
	 * @return boolean
	 */
	public abstract boolean writeXML(File outFile, IngestAssetVO ingestAsset);

}

package br.com.gvt.eng.paytv.ingest.facade;

import java.io.BufferedReader;
import java.util.List;

import javax.ejb.Local;

import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Local
public interface ReadImportFileFacade {

	/**
	 * @param br
	 * @return List<IngestAssetVO>
	 */
	public abstract List<IngestAssetVO> readXLS(BufferedReader br);

}

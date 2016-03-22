package br.com.gvt.eng.paytv.ingest.facade;

import java.io.File;
import java.util.List;

public interface SaveFilesFacade {

	/**
	 * @param listFiles
	 * @param pathFileIn
	 * @param pathFileOut
	 * @param folderRoot
	 * @return boolean
	 */
	public boolean saveFiles(List<File> listFiles, String pathFileIn,
			String pathFileOut, String folderRoot);
}

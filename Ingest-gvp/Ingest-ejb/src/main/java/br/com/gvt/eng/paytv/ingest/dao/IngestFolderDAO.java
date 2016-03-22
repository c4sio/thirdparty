package br.com.gvt.eng.paytv.ingest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.model.IngestFolder;

@Stateless
public class IngestFolderDAO extends GenericDAO<IngestFolder> {

	public IngestFolderDAO() {
		super(IngestFolder.class);
	}

	/**
	 * @param ingestFolder
	 */
	public void deleteIngestFolder(IngestFolder ingestFolder) {
		super.delete(ingestFolder.getFolderID(), IngestFolder.class);
	}

	/**
	 * @param folderReferenceList
	 * @return
	 */
	public List<IngestFolder> findFolderByFolderReference(
			List<String> folderReferenceList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("folderReferenceList", folderReferenceList);
		return super.findResultByParameter(
				IngestFolder.FIND_FOLDER_BY_FOLDER_REFERENCE, parameters);
	}

}

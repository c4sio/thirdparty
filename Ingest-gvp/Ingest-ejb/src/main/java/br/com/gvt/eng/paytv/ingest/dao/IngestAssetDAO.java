package br.com.gvt.eng.paytv.ingest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.model.IngestAsset;

@Stateless
public class IngestAssetDAO extends GenericDAO<IngestAsset> {

	public IngestAssetDAO() {
		super(IngestAsset.class);
	}

	/**
	 * @param assetReferenceList
	 * @return List<IngestAsset>
	 */
	public List<IngestAsset> findAssets(List<String> folderReferenceList) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("folderReferenceList", folderReferenceList);
		return super.findResultByParameter(
				IngestAsset.FIND_ASSETS_BY_FOLDER_REFERENCE, parameters);
	}

	/**
	 * @param assetReferenceList
	 * @return List<IngestAsset>
	 */
	public List<IngestAsset> findAssetByStatus(String status) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return super.findResultByParameter(IngestAsset.FIND_ASSETS_BY_STATUS,
				parameters);
	}

}

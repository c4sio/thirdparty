package br.com.gvt.eng.paytv.ingest.facade.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.dao.IngestAssetDAO;
import br.com.gvt.eng.paytv.ingest.dao.IngestFolderDAO;
import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.facade.IngestAssetFacade;
import br.com.gvt.eng.paytv.ingest.model.IngestAsset;
import br.com.gvt.eng.paytv.ingest.model.IngestFile;
import br.com.gvt.eng.paytv.ingest.model.IngestFolder;
import br.com.gvt.eng.paytv.ingest.utils.ParserUtils;
import br.com.gvt.eng.paytv.ingest.utils.StatusImportXLSConstants;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

@Stateless
public class IngestAssetFacadeImpl implements IngestAssetFacade {

	@EJB
	private IngestFolderDAO folderDAO;

	@EJB
	private IngestAssetDAO assetDAO;

	@Override
	public IngestAssetVO save(IngestAssetVO ingestAssetVO) {
		IngestAsset asset = ParserUtils.parseIngest(ingestAssetVO);
		asset.setLatestUpdate(new Date());
		return ParserUtils.parseIngestVO(this.assetDAO.save(asset));
	}

	@Override
	public IngestAssetVO update(IngestAssetVO ingestAssetVO) {
		IngestAsset asset = ParserUtils.parseIngest(ingestAssetVO);
		asset.setLatestUpdate(new Date());
		return ParserUtils.parseIngestVO(this.assetDAO.update(asset));
	}

	@Override
	public IngestAssetVO find(Long entityID) {
		IngestAsset asset = this.assetDAO.find(entityID);
		if (asset == null) {
			throw new EntityNotFoundException();
		}
		IngestAssetVO vo = ParserUtils.parseIngestVO(asset);
		return vo;
	}

	@Override
	public List<IngestAssetVO> findAll() {
		List<IngestAsset> assets = this.assetDAO.findAll();
		List<IngestAssetVO> result = new ArrayList<IngestAssetVO>();
		for (IngestAsset asset : assets) {
			IngestAssetVO vo = ParserUtils.parseIngestVO(asset);
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<IngestAssetVO> findReadyToSend() {
		List<IngestAsset> assets = this.assetDAO
				.findAssetByStatus(StatusImportXLSConstants.XML_GENERATED);
		List<IngestAssetVO> result = new ArrayList<IngestAssetVO>();
		for (IngestAsset asset : assets) {
			IngestAssetVO vo = ParserUtils.parseIngestVO(asset);
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<IngestAssetVO> findByStatus(String status) {
		List<IngestAsset> assets = this.assetDAO.findAssetByStatus(status);
		List<IngestAssetVO> result = new ArrayList<IngestAssetVO>();
		for (IngestAsset asset : assets) {
			IngestAssetVO vo = ParserUtils.parseIngestVO(asset);
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<IngestAssetVO> save(List<IngestAssetVO> assetList) {
		List<String> folderReferenceList = new ArrayList<String>();
		Map<String, IngestAsset> assetToInsertMap = new HashMap<String, IngestAsset>();

		// Muda o VO para Model
		List<IngestAsset> listAssets = new ArrayList<IngestAsset>();
		listAssets = ParserUtils.parseListIngest(assetList);

		for (IngestAsset asset : listAssets) {
			// assetReferenceList.add(asset.getAssetReference());
			if (asset.getStatus() == null || asset.getStatus().equals("")) {
				// Folder reference list for Folder Search
				folderReferenceList.add(asset.getOdgId());
				// Map of assets to be put on base
				assetToInsertMap.put(asset.getOdgId(), asset);
			}
		}

		// Return se nao houver assets para inserir
		if (assetToInsertMap.isEmpty()) {
			assetList = new ArrayList<IngestAssetVO>();
			for (IngestAsset a : listAssets) {
				assetList.add(ParserUtils.parseIngestVO(a));
			}
			return assetList;
		}

		// Busca Assets por ID
		List<IngestAsset> foundAsset = this.assetDAO
				.findAssets(folderReferenceList);
		if (foundAsset != null) {
			for (IngestAsset asset : foundAsset) {
				// Remove do Map de Assets
				IngestAsset assetFromList = assetToInsertMap.remove(asset
						.getOdgId());
				// Caso ja tenha sido inserido na base
				if (assetFromList != null) {
					assetFromList
							.setStatus(StatusImportXLSConstants.ALREADY_INSERTED);
				}
			}
		}

		// Busca folders pelo folderReference
		List<IngestFolder> folders = this.folderDAO
				.findFolderByFolderReference(folderReferenceList);
		if (folders != null) {
			for (IngestFolder f : folders) {
				IngestAsset assetFromList = assetToInsertMap.get(f
						.getFolderReference());
				if (assetFromList != null) {
					IngestFolder metadataFolder = assetFromList
							.getIngestFolder();
					assetFromList.setIngestFolder(this.folderDAO.find(f
							.getFolderID()));
					for (IngestFile file : assetFromList.getIngestFolder()
							.getIngestFiles()) {
						if (metadataFolder != null
								&& metadataFolder.getIngestFiles() != null) {
							for (IngestFile metadataFile : metadataFolder
									.getIngestFiles()) {
								if (file.getMediaType() == metadataFile
										.getMediaType()) {
									file.merge(metadataFile);
									break;
								}
							}
						}
					}
				}
			}
		}
		
		// Insert dos que sobraram
		for (IngestAsset a : assetToInsertMap.values()) {
			if (a.getAssetReference() != null && a.getStatus() == null) {
				if (a.getIngestFolder() != null
						&& a.getIngestFolder().getUrlRootOut() != null) {
					// salvar registro
					try {
						a.setStatus(StatusImportXLSConstants.XML_READY);
						a.setLatestUpdate(new Date());
						assetDAO.save(a);
					} catch (Exception e) {
						a.setStatus(StatusImportXLSConstants.ERROR_WHILE_SAVING);
						e.printStackTrace();
					}
				} else {
					a.setStatus(StatusImportXLSConstants.FOLDER_MISSING);
				}
			}
		}

		assetList = new ArrayList<IngestAssetVO>();
		for (IngestAsset a : listAssets) {
			assetList.add(ParserUtils.parseIngestVO(a));
		}
		return assetList;
	}
}

package br.com.gvt.eng.paytv.ingest.facade.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.dao.IngestFolderDAO;
import br.com.gvt.eng.paytv.ingest.exception.EntityNotFoundException;
import br.com.gvt.eng.paytv.ingest.facade.IngestFolderFacade;
import br.com.gvt.eng.paytv.ingest.model.IngestFolder;
import br.com.gvt.eng.paytv.ingest.utils.ParserUtils;
import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

@Stateless
public class IngestFolderFacadeImpl implements IngestFolderFacade {

	@EJB
	private IngestFolderDAO ingestFolderDAO;

	@Override
	public IngestFolderVO save(IngestFolderVO folderVO) {
		IngestFolder ingestFolder = new IngestFolder();
		ingestFolder = ParserUtils.parseIngestFolderVO(folderVO);
		ingestFolder.setInsertDate(new Date());
		return ParserUtils.parseIngestFolder(this.ingestFolderDAO
				.save(ingestFolder));
	}

	@Override
	public void delete(IngestFolderVO folderVO) {
		IngestFolder ingestFolder = new IngestFolder();
		ingestFolder = ParserUtils.parseIngestFolderVO(folderVO);
		this.ingestFolderDAO.deleteIngestFolder(ingestFolder);
	}

	@Override
	public IngestFolderVO update(IngestFolderVO folderVO) {
		IngestFolder ingestFolder = new IngestFolder();
		ingestFolder = ParserUtils.parseIngestFolderVO(folderVO);
		return ParserUtils.parseIngestFolder(this.ingestFolderDAO
				.update(ingestFolder));
	}

	@Override
	public IngestFolderVO find(Long entityID) {
		IngestFolder ingestFolder = new IngestFolder();
		ingestFolder = this.ingestFolderDAO.find(entityID);
		return ParserUtils.parseIngestFolder(ingestFolder);
	}

	@Override
	public List<IngestFolderVO> findAll() {
		List<IngestFolder> listIngestFolder = new ArrayList<IngestFolder>();
		listIngestFolder = this.ingestFolderDAO.findAll();
		return ParserUtils.parseListIngestFolderVO(listIngestFolder);
	}

	@Override
	public void removeFolder(Long folderID) {
		IngestFolder ingestFolder = this.ingestFolderDAO.find(folderID);
		if (ingestFolder == null) {
			throw new EntityNotFoundException();
		}
		this.ingestFolderDAO.deleteIngestFolder(ingestFolder);
	}

}

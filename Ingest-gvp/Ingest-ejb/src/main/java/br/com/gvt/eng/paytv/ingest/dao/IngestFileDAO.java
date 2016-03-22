package br.com.gvt.eng.paytv.ingest.dao;

import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.model.IngestFile;

@Stateless
public class IngestFileDAO extends GenericDAO<IngestFile> {

	public IngestFileDAO() {
		super(IngestFile.class);
	}

}

package br.com.gvt.eng.paytv.ingest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IngestFolderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String urlRootIn;

	private String urlRootOut;

	private String folderReference;

	private List<IngestFileDTO> ingestFiles = new ArrayList<IngestFileDTO>();

	public String getUrlRootIn() {
		return urlRootIn;
	}

	public void setUrlRootIn(String urlRootIn) {
		this.urlRootIn = urlRootIn;
	}

	public String getUrlRootOut() {
		return urlRootOut;
	}

	public void setUrlRootOut(String urlRootOut) {
		this.urlRootOut = urlRootOut;
	}

	public String getFolderReference() {
		return folderReference;
	}

	public void setFolderReference(String folderReference) {
		this.folderReference = folderReference;
	}

	public List<IngestFileDTO> getIngestFiles() {
		return ingestFiles;
	}

	public void setIngestFiles(List<IngestFileDTO> ingestFiles) {
		this.ingestFiles = ingestFiles;
	}

}

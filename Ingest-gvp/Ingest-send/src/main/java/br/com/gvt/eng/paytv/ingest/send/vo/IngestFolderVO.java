package br.com.gvt.eng.paytv.ingest.send.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IngestFolderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long folderID;

	private String urlRootIn;

	private String urlRootOut;

	private String folderReference;

	private List<IngestFileVO> ingestFiles = new ArrayList<IngestFileVO>();

	private String insertDate;

	public long getFolderID() {
		return folderID;
	}

	public void setFolderID(long folderID) {
		this.folderID = folderID;
	}

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

	public List<IngestFileVO> getIngestFiles() {
		return ingestFiles;
	}

	public void setIngestFiles(List<IngestFileVO> ingestFiles) {
		this.ingestFiles = ingestFiles;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

}

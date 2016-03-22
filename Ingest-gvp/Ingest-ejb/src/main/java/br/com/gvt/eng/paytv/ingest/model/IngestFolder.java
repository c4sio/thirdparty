package br.com.gvt.eng.paytv.ingest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "INGEST_FOLDER")
@NamedQueries({ @NamedQuery(name = "IngestFolder.getFolderByFolderRefence", query = "SELECT folder FROM IngestFolder folder WHERE folder.folderReference in(:folderReferenceList)") })
public class IngestFolder implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_FOLDER_BY_FOLDER_REFERENCE = "IngestFolder.getFolderByFolderRefence";

	@Id
	@Column(name = "FOLDER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long folderID;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 2000)
	@Column(name = "URL_FOLDER_IN")
	private String urlRootIn;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 2000)
	@Column(name = "URL_FOLDER_OUT")
	private String urlRootOut;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 2000)
	@Column(name = "FOLDER_REFERENCE")
	private String folderReference;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "FOLDER_ID")
	private List<IngestFile> ingestFiles = new ArrayList<IngestFile>();

	@Column(name = "INSERT_DATE")
	private Date insertDate;
	
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

	public List<IngestFile> getIngestFiles() {
		return ingestFiles;
	}

	public void setIngestFiles(List<IngestFile> ingestFiles) {
		this.ingestFiles = ingestFiles;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
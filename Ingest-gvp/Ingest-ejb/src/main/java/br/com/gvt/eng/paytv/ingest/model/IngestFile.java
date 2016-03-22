package br.com.gvt.eng.paytv.ingest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.gvt.eng.paytv.ingest.model.enums.EnumMediaType;

@Entity
@Table(name = "INGEST_FILE")
@NamedQueries({ @NamedQuery(name = "IngestFile.listByAssetId", query = "SELECT a FROM IngestAsset a WHERE a.assetReference IN (:assetIdList)") })
public class IngestFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FILE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fileID;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 2000)
	private String name;

	private EnumMediaType mediaType;

	private Long fileSize;

	/**
	 * Ex In a movie: Widescreen Ex in a poster: 600x882
	 */
	private String format;

	private String language;

	private String checksum;

	public long getFileID() {
		return fileID;
	}

	public void setFileID(long fileID) {
		this.fileID = fileID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mediaType
	 */
	public EnumMediaType getMediaType() {
		return mediaType;
	}

	/**
	 * @param mediaType
	 *            the mediaType to set
	 */
	public void setMediaType(EnumMediaType mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize
	 *            the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the checksum
	 */
	public String getChecksum() {
		return checksum;
	}

	/**
	 * @param checksum
	 *            the checksum to set
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	/**
	 * @param metadataFile
	 */
	public void merge(IngestFile metadataFile) {
		this.mediaType = metadataFile.getMediaType();
		// this.fileSize = metadataFile.getFileSize();
		this.format = metadataFile.getFormat();
		this.language = metadataFile.getLanguage();
		// this.checksum = metadataFile.getChecksum();
	}

}
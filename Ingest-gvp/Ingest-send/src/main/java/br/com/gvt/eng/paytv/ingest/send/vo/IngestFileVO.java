package br.com.gvt.eng.paytv.ingest.send.vo;

import java.io.Serializable;

import br.com.gvt.eng.paytv.ingest.send.enums.EnumMediaType;

public class IngestFileVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long fileID;

	private String name;

	private EnumMediaType mediaType;

	private Long fileSize;

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

	public EnumMediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(EnumMediaType mediaType) {
		this.mediaType = mediaType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
}

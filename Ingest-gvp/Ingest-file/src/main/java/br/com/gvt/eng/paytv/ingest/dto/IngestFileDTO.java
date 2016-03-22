package br.com.gvt.eng.paytv.ingest.dto;

import java.io.Serializable;

import br.com.gvt.eng.paytv.ingest.enums.EnumMediaType;

public class IngestFileDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Long fileSize;

	private String checksum;

	private EnumMediaType mediaType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public EnumMediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(EnumMediaType mediaType) {
		this.mediaType = mediaType;
	}

}

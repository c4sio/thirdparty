package br.com.gvt.eng.drm.vo;

import java.io.Serializable;

public class IpvodMediaType implements Serializable {

	private static final long serialVersionUID = 1L;

	private long mediaTypeId;

	private String description;

	public long getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(long mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

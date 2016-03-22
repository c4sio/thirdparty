package br.com.gvt.eng.drm.vo;

import java.io.Serializable;

public class IpvodMediaAsset implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private IpvodMediaType ipvodMediaType;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public IpvodMediaType getIpvodMediaType() {
		return ipvodMediaType;
	}

	public void setIpvodMediaType(IpvodMediaType ipvodMediaType) {
		this.ipvodMediaType = ipvodMediaType;
	}

}

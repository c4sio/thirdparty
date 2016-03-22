package br.com.gvt.eng.drm.vo;

import java.io.Serializable;
import java.util.List;

public class IpvodVariants implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private List<Long> bitrates;
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getBitrates() {
		return bitrates;
	}

	public void setBitrates(List<Long> bitrates) {
		this.bitrates = bitrates;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

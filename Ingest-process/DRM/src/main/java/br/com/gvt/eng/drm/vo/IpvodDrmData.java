package br.com.gvt.eng.drm.vo;

import java.io.Serializable;

public class IpvodDrmData implements Serializable {

	private static final long serialVersionUID = 1L;

	private long drmId;
	private IpvodIngestStage ipvodIngestStage;
	private String jobIdDrm;
	private String nameFile;
	private String statusDrm;
	private boolean sendMail;
	private Double percentCompDrm;
	private IpvodMediaAsset ipvodMediaAsset;
	private String cookieDrm;

	public long getDrmId() {
		return drmId;
	}

	public void setDrmId(long drmId) {
		this.drmId = drmId;
	}

	public IpvodIngestStage getIpvodIngestStage() {
		return ipvodIngestStage;
	}

	public void setIpvodIngestStage(IpvodIngestStage ipvodIngestStage) {
		this.ipvodIngestStage = ipvodIngestStage;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public boolean isSendMail() {
		return sendMail;
	}

	public void setSendMail(boolean sendMail) {
		this.sendMail = sendMail;
	}

	public String getJobIdDrm() {
		return jobIdDrm;
	}

	public void setJobIdDrm(String jobIdDrm) {
		this.jobIdDrm = jobIdDrm;
	}

	public String getStatusDrm() {
		return statusDrm;
	}

	public void setStatusDrm(String statusDrm) {
		this.statusDrm = statusDrm;
	}

	public Double getPercentCompDrm() {
		return percentCompDrm;
	}

	public void setPercentCompDrm(Double percentCompDrm) {
		this.percentCompDrm = percentCompDrm;
	}

	public IpvodMediaAsset getIpvodMediaAsset() {
		return ipvodMediaAsset;
	}

	public void setIpvodMediaAsset(IpvodMediaAsset ipvodMediaAsset) {
		this.ipvodMediaAsset = ipvodMediaAsset;
	}

	public String getCookieDrm() {
		return cookieDrm;
	}

	public void setCookieDrm(String cookieDrm) {
		this.cookieDrm = cookieDrm;
	}

}
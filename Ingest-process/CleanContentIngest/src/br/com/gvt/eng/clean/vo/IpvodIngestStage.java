package br.com.gvt.eng.clean.vo;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class IpvodIngestStage implements Serializable {

	static Logger logger = Logger.getLogger(IpvodIngestStage.class.getName());

	private static final long serialVersionUID = 1L;

	private Long idIngest;
	private Integer stageType;
	private Boolean resultOK;
	private Boolean cleanup;
	private String adicionalInfo;
	private String assetInfo;
	private Long assetId;
	private String ftpPath;

	public Long getIdIngest() {
		return idIngest;
	}

	public void setIdIngest(Long idIngest) {
		this.idIngest = idIngest;
	}

	public Integer getStageType() {
		return stageType;
	}

	public void setStageType(Integer stageType) {
		this.stageType = stageType;
	}

	public Boolean getResultOK() {
		return resultOK;
	}

	public void setResultOK(Boolean resultOK) {
		this.resultOK = resultOK;
	}

	public Boolean getCleanup() {
		return cleanup;
	}

	public void setCleanup(Boolean cleanup) {
		this.cleanup = cleanup;
	}

	public String getAdicionalInfo() {
		return adicionalInfo;
	}

	public void setAdicionalInfo(String adicionalInfo) {
		this.adicionalInfo = adicionalInfo;
	}

	public String getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}
}
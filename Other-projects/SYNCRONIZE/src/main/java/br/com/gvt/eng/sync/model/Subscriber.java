package br.com.gvt.eng.sync.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author GVT
 * 
 */
@Entity
@Table(name = "IAP_SUBSCRIBER_DATA")
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_ASSET", sequenceName = "SEQ_ASSET", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ASSET")
	private String id;

	@Column(name = "SERVICE_REGION")
	private String serviceRegion;

	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@Column(name = "STATUS")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceRegion() {
		return serviceRegion;
	}

	public void setServiceRegion(String serviceRegion) {
		this.serviceRegion = serviceRegion;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "id: " + id + ", serviceRegion: " + serviceRegion
				+ ", postalCode: " + postalCode + ", status: " + status;
	}

}
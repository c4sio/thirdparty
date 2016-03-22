package br.com.gvt.eng.sync.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the IPVOD_ASSET database table.
 */
@Entity
@Table(name = "IPVOD_ASSET")
//TODO INCLUIR ACTORS
public class AssetSyncVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ASSET_ID")
	private long assetId;

	private String description;

	private String director;

	private String episode;

	@Column(name = "EPISODE_NAME")
	private String episodeName;

	@Column(name = "RELEASE_YEAR")
	private Integer releaseYear;

	private String season;

	@Column(name = "TITLE")
	private String title;

	private String country;

	@Column(name = "MAIN_CATEGORY_ID")
	private Long categoryId;
	
	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getEpisode() {
		return episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public void setEpisodeName(String episodeName) {
		this.episodeName = episodeName;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	
}
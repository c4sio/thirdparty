package br.com.gvt.eng.paytv.ingest.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INGEST_ASSET")
@NamedQueries({
		@NamedQuery(name = "IngestAsset.getAssetsByFolderRefence", query = "SELECT asset FROM IngestAsset asset WHERE asset.odgId in(:folderReferenceList)"),
		@NamedQuery(name = "IngestAsset.getAssetsByStatus", query = "SELECT asset FROM IngestAsset asset WHERE asset.status = :status") })
public class IngestAsset implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIND_ASSETS_BY_FOLDER_REFERENCE = "IngestAsset.getAssetsByFolderRefence";
	public static final String FIND_ASSETS_BY_STATUS = "IngestAsset.getAssetsByStatus";

	@Id
	@Column(name = "ASSET_ID")
	@GeneratedValue
	private Long assetId;

	@Column(name = "ODG_ID")
	private String odgId;

	@Column(name = "ASSET_REFERENCE")
	private String assetReference;

	@Column(name = "CONTENT_PROVIDER")
	private String contentProvider;

	@Column(name = "CONTENT_PROVIDER_ID")
	private String contentProviderId;

	private String category;

	private String playlist1;

	private String playlist2;

	private String genre1;

	// private IngestGenre genre1;

	// private IngestGenre genre2;

	// private IngestGenre genre3;

	// private IngestGenre genre4;

	// private IngestGenre genre5;

	private String program;

	@Column(name = "ORIGINAL_TITLE")
	private String originalTitle;

	@Column(name = "PORTUGUESE_TITLE")
	private String ptTitle;

	private Integer season;

	@Column(name = "EPISODE_NUMBER")
	private Integer episodeNumber;

	private String seriesId;

	private String seasonId;

	private String synopsis;

	private String rating;

	private String actors;

	private String director;

	private String country;

	@Column(name = "RELEASE_YEAR")
	private Integer releaseYear;

	@Column(name = "ORIGINAL_AUDIO")
	private String originalAudio;

	@Column(name = "DUBBED_AUDIO")
	private String dubbedAudio;

	private String subtitle;

	private String audio51;

	@Column(name = "SUBTITLE_ODG")
	private String subtitleODG;

	private String duration;

	@Column(name = "WINDOW_START")
	private String windowStart;

	@Column(name = "WINDOW_END")
	private String windowEnd;

	@Column(name = "SCHEDULE_START_DATE")
	private String scheduleStartDate;

	@Column(name = "SCHEDULE_END_DATE")
	private String scheduleEndDate;

	private Boolean hd;

	private Boolean sd;

	private Boolean publicity;

	@Column(name = "STB_OTT")
	private Boolean stbOtt;

	private Boolean mediaroom;

	private Boolean pc;

	private Boolean conectTV;

	private Boolean tablet;

	private Boolean mobile;

	private Boolean games;

	private Boolean trailer;

	@Column(name = "BUSINESS_MODEL")
	private String businessModel;

	@Column(name = "PRICE_MODEL_GROUP_ID")
	private String priceModelGroupID;

	private String processing;

	@Column(name = "DELIVERED_RECEIVED_STATUS")
	private String deliveredReceivedStatus;

	@Column(name = "RECEIVED_DATE")
	private String receivedDate;

	@Column(name = "DELIVERED_DATE_GVP")
	private String deliveredDateGVP;

	private String changes;

	private String status;

	@Column(name = "MEDIA_ERRORS")
	private String mediaErrors;

	@Column(name = "ENCODING_ERROR")
	private String encodingError;

	@Column(name = "UPDATE_DATE")
	private String updateDate;

	@Column(name = "GPV_ID_STATUS")
	private String gpvIdStatus;

	@Column(name = "GPV_ID_PROC")
	private String gpvIdProc;

	@Column(name = "ORIGINAL_ID")
	private String originalId;

	// private String status;

	private String status2;

	@Column(name = "LATEST_UPDATE")
	private Date latestUpdate;

	@Column(name = "PATH_IN")
	private String pathAssetIn;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "FOLDER_ID")
	private IngestFolder ingestFolder;

	/**
	 * @return the assetId
	 */
	public Long getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId
	 *            the assetId to set
	 */
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the assetReference
	 */
	public String getAssetReference() {
		return assetReference;
	}

	/**
	 * @param assetReference
	 *            the assetReference to set
	 */
	public void setAssetReference(String assetReference) {
		this.assetReference = assetReference;
	}

	/**
	 * @return the odgId
	 */
	public String getOdgId() {
		return odgId;
	}

	/**
	 * @param odgId
	 *            the odgId to set
	 */
	public void setOdgId(String odgId) {
		this.odgId = odgId;
	}

	/**
	 * @return the contentProvider
	 */
	public String getContentProvider() {
		return contentProvider;
	}

	/**
	 * @param contentProvider
	 *            the contentProvider to set
	 */
	public void setContentProvider(String contentProvider) {
		this.contentProvider = contentProvider;
	}

	/**
	 * @return the contentProviderId
	 */
	public String getContentProviderId() {
		return contentProviderId;
	}

	/**
	 * @param contentProviderId
	 *            the contentProviderId to set
	 */
	public void setContentProviderId(String contentProviderId) {
		this.contentProviderId = contentProviderId;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the playlist1
	 */
	public String getPlaylist1() {
		return playlist1;
	}

	/**
	 * @param playlist1
	 *            the playlist1 to set
	 */
	public void setPlaylist1(String playlist1) {
		this.playlist1 = playlist1;
	}

	/**
	 * @return the playlist2
	 */
	public String getPlaylist2() {
		return playlist2;
	}

	/**
	 * @param playlist2
	 *            the playlist2 to set
	 */
	public void setPlaylist2(String playlist2) {
		this.playlist2 = playlist2;
	}

	/**
	 * @return the genre1
	 */
	public String getGenre1() {
		return genre1;
	}

	/**
	 * @param genre1
	 *            the genre1 to set
	 */
	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}

	// /**
	// * @return the genre2
	// */
	// public IngestGenre getGenre2() {
	// return genre2;
	// }
	//
	// /**
	// * @param genre2
	// * the genre2 to set
	// */
	// public void setGenre2(IngestGenre genre2) {
	// this.genre2 = genre2;
	// }
	//
	// /**
	// * @return the genre3
	// */
	// public IngestGenre getGenre3() {
	// return genre3;
	// }
	//
	// /**
	// * @param genre3
	// * the genre3 to set
	// */
	// public void setGenre3(IngestGenre genre3) {
	// this.genre3 = genre3;
	// }
	//
	// /**
	// * @return the genre4
	// */
	// public IngestGenre getGenre4() {
	// return genre4;
	// }
	//
	// /**
	// * @param genre4
	// * the genre4 to set
	// */
	// public void setGenre4(IngestGenre genre4) {
	// this.genre4 = genre4;
	// }
	//
	// /**
	// * @return the genre5
	// */
	// public IngestGenre getGenre5() {
	// return genre5;
	// }
	//
	// /**
	// * @param genre5
	// * the genre5 to set
	// */
	// public void setGenre5(IngestGenre genre5) {
	// this.genre5 = genre5;
	// }

	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}

	/**
	 * @param program
	 *            the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}

	/**
	 * @return the originalTitle
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * @param originalTitle
	 *            the originalTitle to set
	 */
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	/**
	 * @return the ptTitle
	 */
	public String getPtTitle() {
		return ptTitle;
	}

	/**
	 * @param ptTitle
	 *            the ptTitle to set
	 */
	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	/**
	 * @return the season
	 */
	public Integer getSeason() {
		return season;
	}

	/**
	 * @param season
	 *            the season to set
	 */
	public void setSeason(Integer season) {
		this.season = season;
	}

	/**
	 * @return the episodeNumber
	 */
	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * @param episodeNumber
	 *            the episodeNumber to set
	 */
	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	// /**
	// * @return the seriesId
	// */
	// public Integer getSeriesId() {
	// return seriesId;
	// }
	//
	// /**
	// * @param seriesId
	// * the seriesId to set
	// */
	// public void setSeriesId(Integer seriesId) {
	// this.seriesId = seriesId;
	// }
	//
	// /**
	// * @return the seasonId
	// */
	// public Integer getSeasonId() {
	// return seasonId;
	// }
	//
	// /**
	// * @param seasonId
	// * the seasonId to set
	// */
	// public void setSeasonId(Integer seasonId) {
	// this.seasonId = seasonId;
	// }

	/**
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @param synopsis
	 *            the synopsis to set
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the actors
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * @param actors
	 *            the actors to set
	 */
	public void setActors(String actors) {
		this.actors = actors;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director
	 *            the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the releaseYear
	 */
	public Integer getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear
	 *            the releaseYear to set
	 */
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return the originalAudio
	 */
	public String getOriginalAudio() {
		return originalAudio;
	}

	/**
	 * @param originalAudio
	 *            the originalAudio to set
	 */
	public void setOriginalAudio(String originalAudio) {
		this.originalAudio = originalAudio;
	}

	/**
	 * @return the dubbedAudio
	 */
	public String getDubbedAudio() {
		return dubbedAudio;
	}

	/**
	 * @param dubbedAudio
	 *            the dubbedAudio to set
	 */
	public void setDubbedAudio(String dubbedAudio) {
		this.dubbedAudio = dubbedAudio;
	}

	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @param subtitle
	 *            the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * @return the audio51
	 */
	public String getAudio51() {
		return audio51;
	}

	/**
	 * @param audio51
	 *            the audio51 to set
	 */
	public void setAudio51(String audio51) {
		this.audio51 = audio51;
	}

	/**
	 * @return the subtitleODG
	 */
	public String getSubtitleODG() {
		return subtitleODG;
	}

	/**
	 * @param subtitleODG
	 *            the subtitleODG to set
	 */
	public void setSubtitleODG(String subtitleODG) {
		this.subtitleODG = subtitleODG;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the windowStart
	 */
	public String getWindowStart() {
		return windowStart;
	}

	/**
	 * @param windowStart
	 *            the windowStart to set
	 */
	public void setWindowStart(String windowStart) {
		this.windowStart = windowStart;
	}

	/**
	 * @return the windowEnd
	 */
	public String getWindowEnd() {
		return windowEnd;
	}

	/**
	 * @param windowEnd
	 *            the windowEnd to set
	 */
	public void setWindowEnd(String windowEnd) {
		this.windowEnd = windowEnd;
	}

	/**
	 * @return the scheduleStartDate
	 */
	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	/**
	 * @param scheduleStartDate
	 *            the scheduleStartDate to set
	 */
	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	/**
	 * @return the scheduleEndDate
	 */
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	/**
	 * @param scheduleEndDate
	 *            the scheduleEndDate to set
	 */
	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	/**
	 * @return the hd
	 */
	public Boolean getHd() {
		return hd;
	}

	/**
	 * @param hd
	 *            the hd to set
	 */
	public void setHd(Boolean hd) {
		this.hd = hd;
	}

	/**
	 * @return the sd
	 */
	public Boolean getSd() {
		return sd;
	}

	/**
	 * @param sd
	 *            the sd to set
	 */
	public void setSd(Boolean sd) {
		this.sd = sd;
	}

	/**
	 * @return the publicity
	 */
	public Boolean getPublicity() {
		return publicity;
	}

	/**
	 * @param publicity
	 *            the publicity to set
	 */
	public void setPublicity(Boolean publicity) {
		this.publicity = publicity;
	}

	/**
	 * @return the stbOtt
	 */
	public Boolean getStbOtt() {
		return stbOtt;
	}

	/**
	 * @param stbOtt
	 *            the stbOtt to set
	 */
	public void setStbOtt(Boolean stbOtt) {
		this.stbOtt = stbOtt;
	}

	/**
	 * @return the mediaroom
	 */
	public Boolean getMediaroom() {
		return mediaroom;
	}

	/**
	 * @param mediaroom
	 *            the mediaroom to set
	 */
	public void setMediaroom(Boolean mediaroom) {
		this.mediaroom = mediaroom;
	}

	/**
	 * @return the pc
	 */
	public Boolean getPc() {
		return pc;
	}

	/**
	 * @param pc
	 *            the pc to set
	 */
	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	/**
	 * @return the conectTV
	 */
	public Boolean getConectTV() {
		return conectTV;
	}

	/**
	 * @param conectTV
	 *            the conectTV to set
	 */
	public void setConectTV(Boolean conectTV) {
		this.conectTV = conectTV;
	}

	/**
	 * @return the tablet
	 */
	public Boolean getTablet() {
		return tablet;
	}

	/**
	 * @param tablet
	 *            the tablet to set
	 */
	public void setTablet(Boolean tablet) {
		this.tablet = tablet;
	}

	/**
	 * @return the mobile
	 */
	public Boolean getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the games
	 */
	public Boolean getGames() {
		return games;
	}

	/**
	 * @param games
	 *            the games to set
	 */
	public void setGames(Boolean games) {
		this.games = games;
	}

	/**
	 * @return the trailer
	 */
	public Boolean getTrailer() {
		return trailer;
	}

	/**
	 * @param trailer
	 *            the trailer to set
	 */
	public void setTrailer(Boolean trailer) {
		this.trailer = trailer;
	}

	/**
	 * @return the businessModel
	 */
	public String getBusinessModel() {
		return businessModel;
	}

	/**
	 * @param businessModel
	 *            the businessModel to set
	 */
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	/**
	 * @return the priceModelGroupID
	 */
	public String getPriceModelGroupID() {
		return priceModelGroupID;
	}

	/**
	 * @param priceModelGroupID
	 *            the priceModelGroupID to set
	 */
	public void setPriceModelGroupID(String priceModelGroupID) {
		this.priceModelGroupID = priceModelGroupID;
	}

	/**
	 * @return the processing
	 */
	public String getProcessing() {
		return processing;
	}

	/**
	 * @param processing
	 *            the processing to set
	 */
	public void setProcessing(String processing) {
		this.processing = processing;
	}

	/**
	 * @return the deliveredReceivedStatus
	 */
	public String getDeliveredReceivedStatus() {
		return deliveredReceivedStatus;
	}

	/**
	 * @param deliveredReceivedStatus
	 *            the deliveredReceivedStatus to set
	 */
	public void setDeliveredReceivedStatus(String deliveredReceivedStatus) {
		this.deliveredReceivedStatus = deliveredReceivedStatus;
	}

	/**
	 * @return the receivedDate
	 */
	public String getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate
	 *            the receivedDate to set
	 */
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * @return the deliveredDateGVP
	 */
	public String getDeliveredDateGVP() {
		return deliveredDateGVP;
	}

	/**
	 * @param deliveredDateGVP
	 *            the deliveredDateGVP to set
	 */
	public void setDeliveredDateGVP(String deliveredDateGVP) {
		this.deliveredDateGVP = deliveredDateGVP;
	}

	/**
	 * @return the changes
	 */
	public String getChanges() {
		return changes;
	}

	/**
	 * @param changes
	 *            the changes to set
	 */
	public void setChanges(String changes) {
		this.changes = changes;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the mediaErrors
	 */
	public String getMediaErrors() {
		return mediaErrors;
	}

	/**
	 * @param mediaErrors
	 *            the mediaErrors to set
	 */
	public void setMediaErrors(String mediaErrors) {
		this.mediaErrors = mediaErrors;
	}

	/**
	 * @return the encodingError
	 */
	public String getEncodingError() {
		return encodingError;
	}

	/**
	 * @param encodingError
	 *            the encodingError to set
	 */
	public void setEncodingError(String encodingError) {
		this.encodingError = encodingError;
	}

	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the gpvIdStatus
	 */
	public String getGpvIdStatus() {
		return gpvIdStatus;
	}

	/**
	 * @param gpvIdStatus
	 *            the gpvIdStatus to set
	 */
	public void setGpvIdStatus(String gpvIdStatus) {
		this.gpvIdStatus = gpvIdStatus;
	}

	/**
	 * @return the gpvIdProc
	 */
	public String getGpvIdProc() {
		return gpvIdProc;
	}

	/**
	 * @param gpvIdProc
	 *            the gpvIdProc to set
	 */
	public void setGpvIdProc(String gpvIdProc) {
		this.gpvIdProc = gpvIdProc;
	}

	/**
	 * @return the originalId
	 */
	public String getOriginalId() {
		return originalId;
	}

	/**
	 * @param originalId
	 *            the originalId to set
	 */
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	/**
	 * @return the status2
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * @param status2
	 *            the status2 to set
	 */
	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	/**
	 * @return the ingestFolder
	 */
	public IngestFolder getIngestFolder() {
		return ingestFolder;
	}

	/**
	 * @param ingestFolder
	 *            the ingestFolder to set
	 */
	public void setIngestFolder(IngestFolder ingestFolder) {
		this.ingestFolder = ingestFolder;
	}

	/**
	 * @return the seriesId
	 */
	public String getSeriesId() {
		return seriesId;
	}

	/**
	 * @param seriesId
	 *            the seriesId to set
	 */
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	/**
	 * @return the seasonId
	 */
	public String getSeasonId() {
		return seasonId;
	}

	/**
	 * @param seasonId
	 *            the seasonId to set
	 */
	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	/**
	 * @return the latestUpdate
	 */
	public Date getLatestUpdate() {
		return latestUpdate;
	}

	/**
	 * @param latestUpdate
	 *            the latestUpdate to set
	 */
	public void setLatestUpdate(Date latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	/**
	 * @return the pathAssetIn
	 */
	public String getPathAssetIn() {
		return pathAssetIn;
	}

	/**
	 * @param pathAssetIn
	 *            the pathAssetIn to set
	 */
	public void setPathAssetIn(String pathAssetIn) {
		this.pathAssetIn = pathAssetIn;
	}

}

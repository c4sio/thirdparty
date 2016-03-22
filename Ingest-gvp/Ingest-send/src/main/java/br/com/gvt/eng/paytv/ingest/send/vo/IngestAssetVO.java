package br.com.gvt.eng.paytv.ingest.send.vo;

import java.io.Serializable;

public class IngestAssetVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long assetId;
	private String ogdId;
	private String assetReference;
	private String contentProvider;
	private String contentProviderId;
	private String category;
	private String playlist1;
	private String playlist2;
	private String genre1;
	private String program;
	private String originalTitle;
	private String ptTitle;
	private Integer season;
	private Integer episodeNumber;
	private String seriesId;
	private String seasonId;
	private String synopsis;
	private String rating;
	private String actors;
	private String director;
	private String country;
	private Integer releaseYear;
	private String originalAudio;
	private String dubbedAudio;
	private String subtitle;
	private String audio51;
	private String subtitleODG;
	private String duration;
	private String windowStart;
	private String windowEnd;
	private String scheduleStartDate;
	private String scheduleEndDate;
	private Boolean hd;
	private Boolean sd;
	private Boolean publicity;
	private Boolean stbOtt;
	private Boolean mediaroom;
	private Boolean pc;
	private Boolean conectTV;
	private Boolean tablet;
	private Boolean mobile;
	private Boolean games;
	private Boolean trailer;
	private String businessModel;
	private String priceModelGroupID;
	private String processing;
	private String deliveredReceivedStatus;
	private String receivedDate;
	private String deliveredDateGVP;
	private String changes;
	private String status;
	private String mediaErrors;
	private String encodingError;
	private String updateDate;
	private String gpvIdStatus;
	private String gpvIdProc;
	private String originalId;
	private String status2;
	private IngestFolderVO ingestFolder;
	private String latestUpdate;
	private String pathAssetIn;

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getOgdId() {
		return ogdId;
	}

	public void setOgdId(String ogdId) {
		this.ogdId = ogdId;
	}

	public String getAssetReference() {
		return assetReference;
	}

	public void setAssetReference(String assetReference) {
		this.assetReference = assetReference;
	}

	public String getContentProvider() {
		return contentProvider;
	}

	public void setContentProvider(String contentProvider) {
		this.contentProvider = contentProvider;
	}

	public String getContentProviderId() {
		return contentProviderId;
	}

	public void setContentProviderId(String contentProviderId) {
		this.contentProviderId = contentProviderId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlaylist1() {
		return playlist1;
	}

	public void setPlaylist1(String playlist1) {
		this.playlist1 = playlist1;
	}

	public String getPlaylist2() {
		return playlist2;
	}

	public void setPlaylist2(String playlist2) {
		this.playlist2 = playlist2;
	}

	public String getGenre1() {
		return genre1;
	}

	public void setGenre1(String genre1) {
		this.genre1 = genre1;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getPtTitle() {
		return ptTitle;
	}

	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(String seasonId) {
		this.seasonId = seasonId;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getOriginalAudio() {
		return originalAudio;
	}

	public void setOriginalAudio(String originalAudio) {
		this.originalAudio = originalAudio;
	}

	public String getDubbedAudio() {
		return dubbedAudio;
	}

	public void setDubbedAudio(String dubbedAudio) {
		this.dubbedAudio = dubbedAudio;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getAudio51() {
		return audio51;
	}

	public void setAudio51(String audio51) {
		this.audio51 = audio51;
	}

	public String getSubtitleODG() {
		return subtitleODG;
	}

	public void setSubtitleODG(String subtitleODG) {
		this.subtitleODG = subtitleODG;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getWindowStart() {
		return windowStart;
	}

	public void setWindowStart(String windowStart) {
		this.windowStart = windowStart;
	}

	public String getWindowEnd() {
		return windowEnd;
	}

	public void setWindowEnd(String windowEnd) {
		this.windowEnd = windowEnd;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Boolean getHd() {
		return hd;
	}

	public void setHd(Boolean hd) {
		this.hd = hd;
	}

	public Boolean getSd() {
		return sd;
	}

	public void setSd(Boolean sd) {
		this.sd = sd;
	}

	public Boolean getPublicity() {
		return publicity;
	}

	public void setPublicity(Boolean publicity) {
		this.publicity = publicity;
	}

	public Boolean getStbOtt() {
		return stbOtt;
	}

	public void setStbOtt(Boolean stbOtt) {
		this.stbOtt = stbOtt;
	}

	public Boolean getMediaroom() {
		return mediaroom;
	}

	public void setMediaroom(Boolean mediaroom) {
		this.mediaroom = mediaroom;
	}

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public Boolean getConectTV() {
		return conectTV;
	}

	public void setConectTV(Boolean conectTV) {
		this.conectTV = conectTV;
	}

	public Boolean getTablet() {
		return tablet;
	}

	public void setTablet(Boolean tablet) {
		this.tablet = tablet;
	}

	public Boolean getMobile() {
		return mobile;
	}

	public void setMobile(Boolean mobile) {
		this.mobile = mobile;
	}

	public Boolean getGames() {
		return games;
	}

	public void setGames(Boolean games) {
		this.games = games;
	}

	public Boolean getTrailer() {
		return trailer;
	}

	public void setTrailer(Boolean trailer) {
		this.trailer = trailer;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	public String getPriceModelGroupID() {
		return priceModelGroupID;
	}

	public void setPriceModelGroupID(String priceModelGroupID) {
		this.priceModelGroupID = priceModelGroupID;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getDeliveredReceivedStatus() {
		return deliveredReceivedStatus;
	}

	public void setDeliveredReceivedStatus(String deliveredReceivedStatus) {
		this.deliveredReceivedStatus = deliveredReceivedStatus;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getDeliveredDateGVP() {
		return deliveredDateGVP;
	}

	public void setDeliveredDateGVP(String deliveredDateGVP) {
		this.deliveredDateGVP = deliveredDateGVP;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMediaErrors() {
		return mediaErrors;
	}

	public void setMediaErrors(String mediaErrors) {
		this.mediaErrors = mediaErrors;
	}

	public String getEncodingError() {
		return encodingError;
	}

	public void setEncodingError(String encodingError) {
		this.encodingError = encodingError;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getGpvIdStatus() {
		return gpvIdStatus;
	}

	public void setGpvIdStatus(String gpvIdStatus) {
		this.gpvIdStatus = gpvIdStatus;
	}

	public String getGpvIdProc() {
		return gpvIdProc;
	}

	public void setGpvIdProc(String gpvIdProc) {
		this.gpvIdProc = gpvIdProc;
	}

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public IngestFolderVO getIngestFolder() {
		return ingestFolder;
	}

	public void setIngestFolder(IngestFolderVO ingestFolder) {
		this.ingestFolder = ingestFolder;
	}

	public String getLatestUpdate() {
		return latestUpdate;
	}

	public void setLatestUpdate(String latestUpdate) {
		this.latestUpdate = latestUpdate;
	}

	public String getPathAssetIn() {
		return pathAssetIn;
	}

	public void setPathAssetIn(String pathAssetIn) {
		this.pathAssetIn = pathAssetIn;
	}

}
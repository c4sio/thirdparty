package com.gvt.eng.ipvod.proccess.vo;

import java.util.List;

public class PackAsset {

	private List<Asset> asset;
	private MetadataPackage metadata;
	private Metadata AssetsMetadata;

	public List<Asset> getAsset() {
		return asset;
	}

	public void setAsset(List<Asset> asset) {
		this.asset = asset;
	}

	public MetadataPackage getMetadata() {
		return metadata;
	}

	public void setMetadata(MetadataPackage metadata) {
		this.metadata = metadata;
	}

	public Metadata getAssetsMetadata() {
		return AssetsMetadata;
	}

	public void setAssetsMetadata(Metadata assetsMetadata) {
		AssetsMetadata = assetsMetadata;
	}

}

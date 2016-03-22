package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodAssetType;

@Local
public interface AssetTypeDAO {

	public IpvodAssetType findAssetTypeByName(String assetTypeName);

}

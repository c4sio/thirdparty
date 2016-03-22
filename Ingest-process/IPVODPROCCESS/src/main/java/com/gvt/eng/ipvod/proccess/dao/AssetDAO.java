package com.gvt.eng.ipvod.proccess.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodAsset;

@Local
public interface AssetDAO {

	/**
	 * @return List<IpvodAsset>
	 */
	public abstract List<IpvodAsset> findAll();

	/**
	 * @param asset
	 * @return boolean
	 */
	public abstract boolean createAsset(IpvodAsset asset);

	/**
	 * @param asset
	 */
	public abstract void save(IpvodAsset asset);

}

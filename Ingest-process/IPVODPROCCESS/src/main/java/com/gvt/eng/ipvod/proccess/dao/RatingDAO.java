package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodRating;

@Local
public interface RatingDAO {

	/**
	 * Busca dados de Rating para o Asset
	 * 
	 * @param value
	 * @param isAdult
	 * @return IpvodRating
	 */
	public IpvodRating findRating(String value, boolean isAdult);

}

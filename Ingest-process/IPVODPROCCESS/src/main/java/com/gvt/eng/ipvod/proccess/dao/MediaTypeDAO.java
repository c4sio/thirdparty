package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodMediaType;

@Local
public interface MediaTypeDAO {
	
	public IpvodMediaType findMediaTypeByName(String mediaTypeName);

}

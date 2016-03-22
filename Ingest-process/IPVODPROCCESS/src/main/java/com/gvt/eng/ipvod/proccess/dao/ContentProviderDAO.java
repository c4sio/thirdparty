package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

import br.com.gvt.eng.vod.model.IpvodContentProvider;

@Local
public interface ContentProviderDAO {
	
	public IpvodContentProvider findContendProviderByParam(String providerName,
			String providerID);

}

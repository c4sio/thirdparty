package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

@Local
public interface CategoryAssetDAO {

	public long findCategoryByName(String categoryName);
	
}

package com.gvt.eng.ipvod.proccess.dao;

import javax.ejb.Local;

@Local
public interface LanguageDAO {

	public String setCodeLanguageByName(String languageName);

}

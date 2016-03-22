package com.gvt.eng.ipvod.proccess.vo;

import java.util.HashMap;

public class MetadataPackage {

	private Ams AMS;
	private HashMap<String, String> App_Data;

	public Ams getAMS() {
		return AMS;
	}

	public void setAMS(Ams aMS) {
		AMS = aMS;
	}

	public HashMap<String, String> getApp_Data() {
		return App_Data;
	}

	public void setApp_Data(HashMap<String, String> app_Data) {
		App_Data = app_Data;
	}

}

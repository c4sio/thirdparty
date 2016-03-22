package com.gvt.eng.ipvod.proccess.vo;

import java.util.HashMap;
import java.util.List;

public class Metadata {

	private Ams AMS;
	private List<HashMap<String, String>> App_Data;

	public Ams getAMS() {
		return AMS;
	}

	public void setAMS(Ams aMS) {
		AMS = aMS;
	}

	public List<HashMap<String, String>> getApp_Data() {
		return App_Data;
	}

	public void setApp_Data(List<HashMap<String, String>> app_Data) {
		App_Data = app_Data;
	}

}

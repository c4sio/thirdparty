package br.com.gvt.eng.paytv.ingest.model.enums;

public enum EnumScreenFormat {
	 Standard, 
	 Widescreen, 
	 Letterbox, 
	 OAR;
	
	public static EnumScreenFormat getScreenFormat(String str) {
		return EnumScreenFormat.valueOf(EnumScreenFormat.class, str.trim());
	}
}
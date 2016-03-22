package br.com.gvt.eng.paytv.ingest.model.enums;

public enum EnumBusinessModel {
	catchup("CATCHUP"),
	tvod("TVOD"),
	svod("SVOD"),
	freevod("FreeVOD"),
	externalcatchup("ExternalCATCHUP");
	
	public static EnumBusinessModel getBusinessModel(String str) {
		return EnumBusinessModel.valueOf(EnumBusinessModel.class, str.toLowerCase().trim());
	}
	
	private final String text;

	private EnumBusinessModel(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
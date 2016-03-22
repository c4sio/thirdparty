package br.com.gvt.eng.paytv.ingest.model.enums;

public enum EnumAudioType {
	DolbyProLogic("Dolby ProLogic"),
	DolbyDigital("Dolby Digital"),
	Stereo("Stereo"), 
	Mono("Mono"), 
	Dolby51("Dolby 5.1");
	
	private final String text;

	private EnumAudioType(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
	 public static EnumAudioType getAudioType(String str) {
	     for (EnumAudioType audioType : EnumAudioType.values()) {
	         if (audioType.toString().equals(str.trim())) {
	             return audioType;
	         }
	     }
	     // throw an IllegalArgumentException or return null
	     throw new IllegalArgumentException("the given value doesn't match any Audio Type.");
	 }
}
package br.com.gvt.eng.paytv.ingest.model.enums;

public enum EnumCategory {
	AdultMovies,
	JapaneseManga,
	Shorts,
	Sports,
	SportsSoccer,
	DocumentaryNature,
	Documentary ,
	Factual,
	Children,
	Cartoons,
	Movies,
	MusicC,
	MusicK,
	MusicVC,
	Varieties,
	Telenovela,
	Toros,
	TVMovie,
	LocalTVShows,
	LocalPrograms,
	LocalVariety,
	ForeignTV,
	Promotional,
	USSeries;
	
	public static EnumCategory getGenre(String str) {
		return EnumCategory.valueOf(EnumCategory.class, str);
	}
}
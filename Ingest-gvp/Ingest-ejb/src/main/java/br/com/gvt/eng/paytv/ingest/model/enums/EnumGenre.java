package br.com.gvt.eng.paytv.ingest.model.enums;

public enum EnumGenre {
	Action("Action"),
	AdultAmateurs("Adult Amateurs"),
	AdultAnal("Adult Anal"),
	AdultAsiatic("Adult Asiatic"),
	AdultBigTits("Adult Big Tits"),
	AdultGay("Adult Gay"),
	AdultLatins("Adult Latins"),
	AdultLesbians("Adult Lesbians"),
	AdultMature("Adult Mature"),
	AdultMILF("Adult MILF"),
	AdultMovies("Adult Movies"),
	AdultOutdoors("Adult Outdoors"),
	AdultPornStars("Adult PornStars"),
	AdultShort("Adult Short"),
	AdultTeens("Adult Teens"),
	AdultTravestiTransexual("Adult Travesti Transexual"),
	Adventure("Adventure"),
	Biography("Biography"),
	Cartoons("Cartoons"),
	Children("Children"),
	Classic("Classic"),
	Comedy("Comedy"),
	Crime("Crime"),
	Culinary("Culinary"),
	Documentary("Documentary"),
	Drama("Drama"),
	Educational("Educational"),
	Family("Family"),
	Fantasy("Fantasy"),
	Fashion("Fashion"),
	Historical("Historical"),
	Humor("Humor"),
	International("International"),
	Local("Local"),
	Musicals("Musicals"),
	Others("Others"),
	Religious("Religious"),
	Romance("Romance"),
	SciFi("Sci-Fi"),
	Short("Short"),
	Sports("Sports"),
	Suspense("Suspense"),
	Teen("Teen"),
	Terror("Terror"),
	Thriller("Thriller"),
	Travels("Travels"),
	War("War"),
	Western("Western");

	private final String text;

	private EnumGenre(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
	
	 public static EnumGenre getGenre(String str) {
	     for (EnumGenre genre : EnumGenre.values()) {
	         if (genre.toString().equals(str.trim())) {
	             return genre;
	         }
	     }
	     // throw an IllegalArgumentException or return null
	     throw new IllegalArgumentException("the given value doesn't match any Genre.");
	 }
}
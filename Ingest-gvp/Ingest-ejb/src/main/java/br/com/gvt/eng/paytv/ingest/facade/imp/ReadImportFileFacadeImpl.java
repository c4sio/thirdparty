package br.com.gvt.eng.paytv.ingest.facade.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;

import br.com.gvt.eng.paytv.ingest.facade.ReadImportFileFacade;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumBusinessModel;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumCategory;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumGenre;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumMediaType;
import br.com.gvt.eng.paytv.ingest.model.enums.EnumScreenFormat;
import br.com.gvt.eng.paytv.ingest.utils.StatusImportXLSConstants;
import br.com.gvt.eng.paytv.ingest.utils.Util;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;
import br.com.gvt.eng.paytv.ingest.vo.IngestFileVO;
import br.com.gvt.eng.paytv.ingest.vo.IngestFolderVO;

@Stateless
public class ReadImportFileFacadeImpl implements ReadImportFileFacade {

	@Override
	public List<IngestAssetVO> readXLS(BufferedReader br) {
		List<IngestAssetVO> listAsset = new ArrayList<IngestAssetVO>();
		String line = "";
		try {
			// ignora HEADER
			br.readLine();
			while ((line = br.readLine()) != null) {
				listAsset.add(getAsset(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("FIM Leitura de CVS");
		return listAsset;
	}

	/**
	 * @param line
	 * @return IngestAssetVO
	 */
	private IngestAssetVO getAsset(String line) {
		String cvsSplitBy = ";";
		IngestAssetVO asset = new IngestAssetVO();
		String[] splitResult = line.split(cvsSplitBy);
		int i = 0;

		try {
			// ODG ID
			asset.setOdgId(splitResult[i]);

			// Asset Refence deve ter 20 posições, sendo 4 Letras e 16 numeros
			String assetReference = splitResult[++i].trim();
			Pattern p = Pattern.compile("[A-Z]{4}[0-9]{16}");
			Matcher m = p.matcher(assetReference);
			if (!m.matches()) {
				asset.setStatus(StatusImportXLSConstants.ASSET_REFERENCE_FORMAT_ERROR);
			} else {
				asset.setAssetReference(assetReference);
			}

			asset.setContentProvider(splitResult[++i].trim());
			if (asset.getContentProvider().equals("")) {
				asset.setStatus(StatusImportXLSConstants.PROVIDER_ERROR);
			}

			asset.setContentProviderId(splitResult[++i].trim());
			if (asset.getContentProviderId().equals("")) {
				asset.setStatus(StatusImportXLSConstants.PROVIDER_ID_ERROR);
			}

			String category = setCategory(splitResult[++i].trim());
			asset.setCategory(category);
			if (asset.getCategory() == null) {
				asset.setStatus(StatusImportXLSConstants.CATEGORY_ERROR);
			}

			asset.setPlaylist1(splitResult[++i].trim());

			asset.setPlaylist2(splitResult[++i].trim());

			String genre = setGenre(splitResult[++i].trim());
			asset.setGenre1(genre);
			if (asset.getGenre1() == null) {
				asset.setStatus(StatusImportXLSConstants.GENRE_ERROR);
			}

			i++;
			// asset.setgenre2

			i++;
			// asset.setgenre3

			i++;
			// asset.setgenre4

			i++;
			// asset.setgenre5

			asset.setProgram(splitResult[++i].trim());

			asset.setOriginalTitle(splitResult[++i].trim());

			asset.setPtTitle(splitResult[++i].trim());

			i++;
			if (!splitResult[i].equals("")) {
				asset.setSeason(new Integer(splitResult[i]));
			}

			i++;
			if (!splitResult[i].equals("")) {
				asset.setEpisodeNumber(new Integer(splitResult[i]));
			}

			asset.setSeriesId(splitResult[++i].trim());

			asset.setSeasonId(splitResult[++i].trim());

			if (asset.getEpisodeNumber() != null
					|| !asset.getSeriesId().equals("")
					|| !asset.getSeasonId().equals("")) {
				if (asset.getEpisodeNumber() == null) {
					asset.setStatus(StatusImportXLSConstants.EPISODE_NUMBER_ERROR);
				}
				if (asset.getSeriesId().equals("")) {
					asset.setStatus(StatusImportXLSConstants.SERIES_ID_ERROR);
				}
				if (asset.getSeasonId().equals("")) {
					asset.setStatus(StatusImportXLSConstants.SEASON_ID_ERROR);
				}
			}

			asset.setSynopsis(splitResult[++i]);

			asset.setRating(splitResult[++i]);
			if (asset.getRating().equalsIgnoreCase("livre")) {
				asset.setStatus(StatusImportXLSConstants.RATING_ERROR);
			}

			asset.setActors(splitResult[++i]);

			asset.setDirector(splitResult[++i]);

			asset.setCountry(splitResult[++i].trim());

			i++;
			if (!splitResult[i].equals("")) {
				asset.setReleaseYear(new Integer(splitResult[i]));
			}

			asset.setOriginalAudio(splitResult[++i].trim());

			asset.setDubbedAudio(splitResult[++i].trim());

			asset.setSubtitle(splitResult[++i]);

			asset.setAudio51(splitResult[++i]);

			asset.setSubtitleODG(splitResult[++i]);

			asset.setDuration(splitResult[++i]);

			asset.setWindowStart(Util.formatDate(splitResult[++i]));

			asset.setWindowEnd(Util.formatDate(splitResult[++i]));

			asset.setScheduleStartDate(Util.formatDate(splitResult[++i]));

			asset.setScheduleEndDate(Util.formatDate(splitResult[++i]));

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setHd(true);
			} else {
				asset.setHd(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setSd(true);
			} else {
				asset.setSd(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setPublicity(true);
			} else {
				asset.setPublicity(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setStbOtt(true);
			} else {
				asset.setStbOtt(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setMediaroom(true);
			} else {
				asset.setMediaroom(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setPc(true);
			} else {
				asset.setPc(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setConectTV(true);
			} else {
				asset.setConectTV(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setTablet(true);
			} else {
				asset.setTablet(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setMobile(true);
			} else {
				asset.setMobile(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setGames(true);
			} else {
				asset.setGames(false);
			}

			if (splitResult[++i].equalsIgnoreCase("true")) {
				asset.setTrailer(true);
			} else {
				asset.setTrailer(false);
			}

			String businessModel = setBusinessModel(splitResult[++i].trim());
			asset.setBusinessModel(businessModel);
			if (asset.getBusinessModel() == null) {
				asset.setStatus(StatusImportXLSConstants.BUSINESS_MODEL_ERROR);
			}

			asset.setPriceModelGroupID(splitResult[++i]);

			asset.setProcessing(splitResult[++i]);

			asset.setDeliveredReceivedStatus(splitResult[++i]);

			asset.setReceivedDate(splitResult[++i]);

			asset.setDeliveredDateGVP(splitResult[++i]);

			asset.setChanges(splitResult[++i]);

			i++;
			// splitResult[i];
			// asset.setstatus *

			asset.setMediaErrors(splitResult[++i]);

			asset.setEncodingError(splitResult[++i]);

			asset.setUpdateDate(splitResult[++i]);

			asset.setGpvIdStatus(splitResult[++i]);

			asset.setGpvIdProc(splitResult[++i]);

			asset.setOriginalId(splitResult[++i]);

			i++;
			// asset.setStatus(splitResult[i]);

			asset.setStatus2(splitResult[++i]);

			// Path
			asset.setPathAssetIn(splitResult[++i].trim());
			if (asset.getPathAssetIn() == null
					|| asset.getPathAssetIn().equals("")) {
				asset.setPathAssetIn(StatusImportXLSConstants.PATH_ERROR);
			}

			asset.setIngestFolder(new IngestFolderVO());
			asset.getIngestFolder().setIngestFiles(
					new ArrayList<IngestFileVO>());

			// Movie
			IngestFileVO movie = new IngestFileVO();
			movie.setMediaType(EnumMediaType.MOVIE);

			// Screen_Format
			String screenFormat = setScreenFormat(splitResult[++i].trim());
			movie.setFormat(screenFormat);

			// Languages
			movie.setLanguage(splitResult[++i].trim());

			// Content_FileSize
			i++;
			if (splitResult[i] != null && !splitResult[i].equals("")) {
				movie.setFileSize(new Long(splitResult[i]));
			}

			// Content_CheckSum
			movie.setChecksum(splitResult[++i].trim());

			asset.getIngestFolder().getIngestFiles().add(movie);

			// Poster
			IngestFileVO poster = new IngestFileVO();
			poster.setMediaType(EnumMediaType.POSTER);

			// Image_Aspect_Ratio
			poster.setFormat(splitResult[++i].trim());
			poster.setLanguage(movie.getLanguage());

			asset.getIngestFolder().getIngestFiles().add(poster);

			// Content_FileSize
			i++;
			if (splitResult[i] != null && !splitResult[i].equals("")) {
				poster.setFileSize(new Long(splitResult[i]));
			}

			// Content_CheckSums
			poster.setChecksum(splitResult[++i].trim());

			// Subtitle
			IngestFileVO subtitle = new IngestFileVO();
			subtitle.setMediaType(EnumMediaType.SUBTITLE);

			// Language
			subtitle.setLanguage(splitResult[++i].trim());

			// Content_FileSize
			i++;
			if (splitResult[i] != null && !splitResult[i].equals("")) {
				subtitle.setFileSize(new Long(splitResult[i]));
			}

			asset.getIngestFolder().getIngestFiles().add(subtitle);

			if (movie != null) {
				if (movie.getFormat() == null) {
					asset.setStatus(StatusImportXLSConstants.SCREEN_FORMAT_ERROR);
				}
			}
			if (poster != null) {
				if (poster.getFormat() == null || poster.getFormat().equals("")) {
					asset.setStatus(StatusImportXLSConstants.IMG_SCREEN_FORMAT_ERROR);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Encontrou Campos nulos no arquivo - coluna ["
					+ e.getMessage() + "]");
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("Erro - " + exc.getMessage());
		}
		return asset;
	}

	/**
	 * @param str
	 * @return String
	 */
	private static String setScreenFormat(String str) {
		try {
			EnumScreenFormat.getScreenFormat(str).toString();
		} catch (IllegalArgumentException e) {
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return String
	 */
	private static String setBusinessModel(String str) {
		try {
			return EnumBusinessModel.getBusinessModel(str).toString();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * @param str
	 * @return String
	 */
	private static String setGenre(String str) {
		try {
			EnumGenre.getGenre(str).toString();
		} catch (IllegalArgumentException e) {
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return String
	 */
	private static String setCategory(String str) {
		try {
			return EnumCategory.valueOf(EnumCategory.class, str).toString();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
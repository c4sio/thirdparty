package br.com.gvt.eng.paytv.ingest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.gvt.eng.paytv.ingest.model.IngestAsset;

public class ImportXLS {

	public static void main(String[] args) {
		String fileName = "C:/Users/GVT/Documents/Skype/xml_engenharia.xlsx";
		File file = new File(fileName);
		System.out.println(readXLS(file).size());
	}

	private static List<IngestAsset> readXLS(File file) {

		List<IngestAsset> listAsset = null;
		
		FileInputStream fileInputStream;
		Workbook workbook = null;
		Sheet sheet;
		Iterator<Row> rowIterator;
		try {
			fileInputStream = new FileInputStream(file);
			String fileExtension = file.getName().substring(file.getName().indexOf("."));
			System.out.println(fileExtension);
			if (fileExtension.equals(".xls")) {
				workbook = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
			} else if (fileExtension.equals(".xlsx")) {
				workbook = new XSSFWorkbook(fileInputStream);
			} else {
				System.out.println("Wrong File Type");
			}
			sheet = workbook.getSheetAt(0);
			rowIterator = sheet.iterator();

			// ignora header
			rowIterator.next();
			listAsset = new ArrayList<IngestAsset>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println(row.getRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				
				listAsset.add(getAsset(cellIterator));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return listAsset;
	}

	private static IngestAsset getAsset(Iterator<Cell> cellIterator) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IngestAsset asset = new IngestAsset();

		try {
			
		
		// ignora ODG ID
		Cell cell = cellIterator.next();
		
		cell = cellIterator.next();
		asset.setAssetReference(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		cell.getStringCellValue();
		asset.setContentProvider(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setCategory(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setPlaylist1(cell.getStringCellValue());	  
		
		cell = cellIterator.next();
		asset.setPlaylist2(cell.getStringCellValue());
		
		cell = cellIterator.next();
//		cell.getStringCellValue()
//		asset.setGenre1(genre1);
//		
		cell = cellIterator.next();
//		cell.getStringCellValue();
//		asset.setgenre2	
//		
		cell = cellIterator.next();
//		cell.getStringCellValue();
//		asset.setgenre3	
//		
		cell = cellIterator.next();
//		cell.getStringCellValue();
//		asset.setgenre4	
//		
		cell = cellIterator.next();
//		cell.getStringCellValue();
//		asset.setgenre5	
		
		cell = cellIterator.next();
		asset.setProgram(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setOriginalTitle(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setPtTitle(cell.getStringCellValue());
		
		cell = cellIterator.next();
		if (cell.getNumericCellValue() != 0) {
			asset.setSeason(new Double(cell.getNumericCellValue()).intValue());
		}
		
		cell = cellIterator.next();
		if (cell.getNumericCellValue() != 0) {
			asset.setEpisodeNumber(new Double(cell.getNumericCellValue()).intValue());
		}
		
//		cell = cellIterator.next();
//		if (cell.getNumericCellValue() != 0) {
//			asset.setSeriesId(new Double(cell.getNumericCellValue()).intValue());
//		}
//		
//		cell = cellIterator.next();
//		if (cell.getNumericCellValue() != 0) {
//			asset.setSeasonId(new Double(cell.getNumericCellValue()).intValue());
//		}
		
		cell = cellIterator.next();
		asset.setSynopsis(cell.getStringCellValue());
		
		cell = cellIterator.next();
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			asset.setRating(new Double(cell.getNumericCellValue()).toString());	
		} else {
			asset.setRating(cell.getStringCellValue());
		}
		
		cell = cellIterator.next();
		asset.setActors(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setDirector(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setCountry(cell.getStringCellValue());
		
		cell = cellIterator.next();
		if (cell.getNumericCellValue() != 0) {
			asset.setReleaseYear(new Double(cell.getNumericCellValue()).intValue());
		}
		
		cell = cellIterator.next();
		asset.setOriginalAudio(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setDubbedAudio(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setSubtitle(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setAudio51(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setSubtitleODG(cell.getStringCellValue());
		
		cell = cellIterator.next();
		if (cell.getNumericCellValue() != 0) {
			asset.setDuration(cell.getStringCellValue());
		}
		
		cell = cellIterator.next();
		asset.setWindowStart(sdf.format(cell.getDateCellValue()));
		
		cell = cellIterator.next();
		asset.setWindowEnd(sdf.format(cell.getDateCellValue()));
		
		cell = cellIterator.next();
		asset.setScheduleStartDate(sdf.format(cell.getDateCellValue()));
		
		cell = cellIterator.next();
		asset.setScheduleEndDate(sdf.format(cell.getDateCellValue()));	
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setHd(true);
		} else {
			asset.setHd(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setSd(true);
		} else {
			asset.setSd(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setPublicity(true);
		} else {
			asset.setPublicity(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setStbOtt(true);
		} else {
			asset.setStbOtt(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setMediaroom(true);
		} else {
			asset.setMediaroom(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setPc(true);
		} else {
			asset.setPc(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setConectTV(true);
		} else {
			asset.setConectTV(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setTablet(true);
		} else {
			asset.setTablet(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setMobile(true);
		} else {
			asset.setMobile(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setGames(true);
		} else {
			asset.setGames(false);
		}
		
		cell = cellIterator.next();
		if (cell.getStringCellValue().equals("Sim")) {
			asset.setTrailer(true);
		} else {
			asset.setTrailer(false);
		}
		
		cell = cellIterator.next();
		asset.setBusinessModel(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setPriceModelGroupID(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setProcessing(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setDeliveredReceivedStatus(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setReceivedDate(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setDeliveredDateGVP(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setChanges(cell.getStringCellValue());	
		
//		cell = cellIterator.next();
//		cell.getStringCellValue();
//		asset.setstatus	*
		
		cell = cellIterator.next();
		asset.setMediaErrors(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setEncodingError(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setUpdateDate(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setGpvIdStatus(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setGpvIdProc(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setOriginalId(cell.getStringCellValue());
		
		cell = cellIterator.next();
		asset.setStatus(cell.getStringCellValue());	
		
		cell = cellIterator.next();
		asset.setStatus2(cell.getStringCellValue());
		} catch (NoSuchElementException noSuchElementException) {
			//Final da linha
		}
		return asset;
	}
}
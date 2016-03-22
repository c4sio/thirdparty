package br.com.gvt.eng.paytv.ingest.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import br.com.gvt.eng.paytv.ingest.facade.ReadImportFileFacade;
import br.com.gvt.eng.paytv.ingest.facade.imp.ReadImportFileFacadeImpl;
import br.com.gvt.eng.paytv.ingest.vo.IngestAssetVO;

public class ImportCVS {

	public static void main(String[] args) {
//		String csvFile = "C:/Users/GVT/Documents/XML/content_form_2.csv";
		String csvFile = args[0];

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ReadImportFileFacade facade = new ReadImportFileFacadeImpl();
		List<IngestAssetVO> a = facade.readXLS(br);
		System.out.println(a.size());
	}

}
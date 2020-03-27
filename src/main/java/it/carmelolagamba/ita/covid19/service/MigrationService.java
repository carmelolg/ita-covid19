package it.carmelolagamba.ita.covid19.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.FileImportedDocumentService;

@Component
public class MigrationService {

	@Autowired
	private FileImportedDocumentService fileImportedDocumentService;

	@Autowired
	private FileUtils fileUtils;

	public void migrateRegionData() {
		File folder = new File("./data/dati-regioni");
		List<String> fileNameList = fileUtils.listAllFiles(folder);
		
//		List<List<String>> records = new ArrayList<List<String>>();
//		try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
//		    String[] values = null;
//		    while ((values = csvReader.readNext()) != null) {
//		        records.add(Arrays.asList(values));
//		    }
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
	}

	public void migrateDistrictData() {

	}

	public void migrateNationalData() {

	}
}

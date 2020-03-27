package it.carmelolagamba.ita.covid19.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class FileUtils {

	private Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static void main(String[] args) {
		File folder = new File("./data/dati-regioni");
		FileUtils listFiles = new FileUtils();
		System.out.println(listFiles.listAllFiles(folder));
	}

	// Uses listFiles method
	public List<String> listAllFiles(File folder) {

		File[] fileNames = folder.listFiles();
		List<String> filenameList = new ArrayList<>();
		for (File file : fileNames) {
			if (file.isDirectory()) {
				logger.error("Path {} is a folder", file.getAbsoluteFile());
			} else {
				filenameList.add(file.getAbsolutePath());
			}
		}

		return filenameList;
	}

	public List<String[]> readAll(Reader reader) throws IOException {
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> list;
		list = csvReader.readAll();
		reader.close();
		csvReader.close();
		return list;
	}

	public String readAllExample() throws URISyntaxException, IOException {
		Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("csv/twoColumn.csv").toURI()));
		return readAll(reader).toString();
	}
}
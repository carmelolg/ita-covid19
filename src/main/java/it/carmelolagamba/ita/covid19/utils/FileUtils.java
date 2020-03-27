package it.carmelolagamba.ita.covid19.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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

	public List<File> listAllFiles(File folder) {

		File[] fileNames = folder.listFiles();
		List<File> list = new ArrayList<>();
		for (File file : fileNames) {
			if (file.isDirectory()) {
				logger.error("Path {} is a folder", file.getAbsoluteFile());
			} else {
				list.add(file);
			}
		}

		return list;
	}

	public static Integer convertStringToInteger(String value){
		try{
			Integer i = Integer.parseInt(value);
			return i;
		}catch (NumberFormatException e){
			return -1;
		}
	}
}
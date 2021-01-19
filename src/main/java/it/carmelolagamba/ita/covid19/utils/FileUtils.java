package it.carmelolagamba.ita.covid19.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	private static final int BUFFER_SIZE = 4096;

	/**
	 * Downloads a file from a URL
	 *
	 * @param fileURL HTTP URL of the file to be downloaded
	 * @param saveDir path of the directory to save the file
	 * @throws IOException
	 */
	public static boolean downloadFile(String fileURL, String saveDir) throws IOException {
		URL url = new URL(fileURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			String fileName = "";
			String disposition = httpConn.getHeaderField("Content-Disposition");
			String contentType = httpConn.getContentType();
			int contentLength = httpConn.getContentLength();

			if (disposition != null) {
				// extracts file name from header field
				int index = disposition.indexOf("filename=");
				if (index > 0) {
					fileName = disposition.substring(index + 10, disposition.length() - 1);
				}
			} else {
				// extracts file name from URL
				fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1, fileURL.length());
			}

			logger.info("Content-Type = {}", contentType);
			logger.info("Content-Disposition = {}", disposition);
			logger.info("Content-Length = {}", contentLength);
			logger.info("fileName = {}", fileName);

			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();
			String saveFilePath = saveDir + File.separator + fileName;

			File directory = new File(saveDir);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			File file = new File(saveFilePath);
			file.createNewFile(); // if file already exists will do nothing
			FileOutputStream outputStream = new FileOutputStream(saveFilePath, false);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			logger.info("File downloaded");
			httpConn.disconnect();
			return true;
		} else {
			logger.info("No file to download. Server replied HTTP code: {}", responseCode);
			httpConn.disconnect();
			return false;
		}
	}

	public List<File> listAllFiles(File folder) {

		File[] fileNames = folder.listFiles();
		List<File> list = new ArrayList<>();
		
		if(fileNames != null) {
			for (File file : fileNames) {
				if (file.isDirectory()) {
					logger.error("Path {} is a folder", file.getAbsoluteFile());
				} else {
					list.add(file);
				}
			}			
		}

		return list;
	}

	public static Integer convertStringToInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public static Double convertStringToDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return new Double(-1);
		}
	}
}
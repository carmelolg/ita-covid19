package it.carmelolagamba.ita.covid19.service.migration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.config.FileProperties;
import it.carmelolagamba.ita.covid19.domain.DataVacciniPuntiSomministrazione;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniPuntiSomministrazioneDocumentService;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataVacciniPuntiSomministrazione;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
@EnableConfigurationProperties({ FileProperties.class })
public class VacciniPuntiSomministrazioneMigrationService {

	private static Logger logger = LoggerFactory.getLogger(VacciniPuntiSomministrazioneMigrationService.class);

	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private CSVDataVacciniPuntiSomministrazione csvDataPuntiSomministrazione;

	@Autowired
	private DataVacciniPuntiSomministrazioneDocumentService dataPuntiSomministrazioneDocumentService;

	public void migrateData() throws Exception {
		File file = new File(getFolderPath() + "/" + fileProperties.getPuntiSomministrazioneFilename());

		List<DataVacciniPuntiSomministrazione> dataPuntiSomministrazioneList = csvDataPuntiSomministrazione
				.convertToDataFromFilename(file.getAbsolutePath());

		try {
			dataPuntiSomministrazioneDocumentService.replaceAll(dataPuntiSomministrazioneList);
		} catch (InterruptedException | ExecutionException e) {
            logger.warn("Interrupted exception replacing all data: {}", e.getMessage());
		}
	}

	protected File getFolderPath() {
		return new File(Constants.folderVaccini);
	}

	@Scheduled(cron = "0 0/30 * * * *")
	public void getFile() throws Exception {
		try {

			String fileURL = fileProperties.getVacciniBaseUrl() + fileProperties.getPuntiSomministrazioneFilename();
			String saveDir = Constants.folderVaccini;
			boolean saved = FileUtils.downloadFile(fileURL, saveDir);
			if (saved) {
				logger.info("File importato: {}", fileURL);
				migrateData();
				logger.info("File migrato: {}", fileURL);
			} else {
				logger.info("File: {} non ancora disponibile", fileURL);
			}

		} catch (IOException ex) {
			logger.error("Scheduling per scaricare i dati dei punti di somministrazione andato in errore", ex);
		}
	}
}

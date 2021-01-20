package it.carmelolagamba.ita.covid19.service.migration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.config.FileProperties;
import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniSummaryDocumentService;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataVacciniSummary;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
@EnableConfigurationProperties({ FileProperties.class })
public class VacciniSummaryMigrationService {

	private static Logger logger = LoggerFactory.getLogger(VacciniSummaryMigrationService.class);

	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private CSVDataVacciniSummary csvDataVacciniSummary;

	@Autowired
	private DataVacciniSummaryDocumentService dataVacciniSummaryDocumentService;

	public void migrateData() throws Exception {
		File file = new File(getFolderPath() + "/" + fileProperties.getVacciniSummaryFilename());

		List<DataVacciniSummary> dataVacciniSummaryList = csvDataVacciniSummary
				.convertToDataFromFilename(file.getAbsolutePath());

		dataVacciniSummaryList.stream().forEach(dataVacciniSummaryDocumentService::upsert);
	}

	protected File getFolderPath() {
		return new File(Constants.folderVaccini);
	}

	@Scheduled(cron = "0 0/30 * * * *")
	public void getFile() throws Exception {
		try {

			String fileURL = fileProperties.getVacciniBaseUrl() + fileProperties.getVacciniSummaryFilename();
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
			logger.error("Scheduling per scaricare i dati delle regioni giornalieri andato in errore", ex);
		}
	}
}

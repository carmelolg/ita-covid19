package it.carmelolagamba.ita.covid19.service.migration;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.config.FileProperties;
import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniSomministrazioneRegionaleSummaryDocumentService;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
@EnableConfigurationProperties({ FileProperties.class })
public class VacciniSomministrazioneRegionaleSummaryMigrationService {

	private static Logger logger = LoggerFactory
			.getLogger(VacciniSomministrazioneRegionaleSummaryMigrationService.class);

	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private CSVDataVacciniSomministrazioneRegionaleSummary csvDataVacciniSomministrazioneRegionaleSummary;

	@Autowired
	private DataVacciniSomministrazioneRegionaleSummaryDocumentService dataVacciniSomministrazioneRegionaleSummaryDocumentService;

	public void migrateData() throws Exception {
		File file = new File(getFolderPath() + "/" + fileProperties.getSomministrazioneRegionaleFilename());

		List<DataVacciniSomministrazioneRegionaleSummary> dataVacciniSomministrazioneRegionaleSummaryList = csvDataVacciniSomministrazioneRegionaleSummary
				.convertToDataFromFilename(file.getAbsolutePath());

		dataVacciniSomministrazioneRegionaleSummaryList.stream().filter(d -> {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = dateFormat.format(d.getData_somministrazione());
			String todayInString = dateFormat.format(new Date());
			return dateInString.equals(todayInString);
		}).forEach(dataVacciniSomministrazioneRegionaleSummaryDocumentService::upsert);
	}

	protected File getFolderPath() {
		return new File(Constants.folderVaccini);
	}

	@Scheduled(cron = "0 0/30 * * * *")
	public void getFile() throws Exception {
		try {

			String fileURL = fileProperties.getVacciniBaseUrl() + fileProperties.getSomministrazioneRegionaleFilename();
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
			logger.error("Scheduling per scaricare i dati sui vaccini andato in errore", ex);
		}
	}
}

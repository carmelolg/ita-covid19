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
import it.carmelolagamba.ita.covid19.domain.DataAnagraficaVacciniSummary;
import it.carmelolagamba.ita.covid19.persistence.DataAnagraficaVacciniSummaryDocumentService;
import it.carmelolagamba.ita.covid19.service.csv.AbstractCSVDataReader;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataAnagraficaVacciniSummary;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
@EnableConfigurationProperties({ FileProperties.class })
public class AnagraficaVacciniSummryMigrationService
		extends AbstractVacciniMigrationService<DataAnagraficaVacciniSummary> {

	private static Logger logger = LoggerFactory.getLogger(AnagraficaVacciniSummryMigrationService.class);

	@Autowired
	private FileProperties fileProperties;

	@Autowired
	private CSVDataAnagraficaVacciniSummary csvDataAnagraficaVacciniSummary;

	@Autowired
	private DataAnagraficaVacciniSummaryDocumentService dataAnagraficaVacciniSummaryDocumentService;

	@Override
	protected void migrateInvoke(List<DataAnagraficaVacciniSummary> list) {
		list.stream().forEach(dataAnagraficaVacciniSummaryDocumentService::upsert);
	}

	protected File getFolderPath() {
		return new File(Constants.folderVaccini);
	}

	@Override
	protected AbstractCSVDataReader<DataAnagraficaVacciniSummary> getReader() {
		return csvDataAnagraficaVacciniSummary;
	}

	@Override
	protected String getFilename() {
		return fileProperties.getAnagraficaVacciniSummaryFilename();
	}
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Scheduled(cron = "0 0/5 * * * *")
	public void getFile() throws Exception {
		try {

			String fileURL = fileProperties.getVacciniBaseUrl() + fileProperties.getAnagraficaVacciniSummaryFilename();
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

package it.carmelolagamba.ita.covid19.service.migration;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;

import it.carmelolagamba.ita.covid19.service.csv.AbstractCSVDataReader;

public abstract class AbstractVacciniMigrationService<T> {

	public void migrateData() throws Exception {

		File file = new File(getFolderPath() + "/" + getFilename());

		getLogger().info("Convert file to list of pojo..");
		List<T> dataAnagraficaVacciniSummaryList = getReader().convertToDataFromFilename(file.getAbsolutePath());
		getLogger().info("Update database..");
		migrateInvoke(dataAnagraficaVacciniSummaryList);
	}

	protected abstract AbstractCSVDataReader<T> getReader();

	protected abstract File getFolderPath();

	protected abstract String getFilename();

	protected abstract void migrateInvoke(List<T> dataAnagraficaVacciniSummaryList);

	protected abstract Logger getLogger();
}

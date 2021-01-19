package it.carmelolagamba.ita.covid19.service.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary;
import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary.FIELD;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
public class CSVDataVacciniSummary extends AbstractCSVDataReader<DataVacciniSummary> {

	private static Logger logger = LoggerFactory.getLogger(CSVDataVacciniSummary.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected void map(DataVacciniSummary vacciniSummary, int i, String value) {

		final FIELD[] fields = FIELD.values();
		switch (fields[i]) {
		case ULTIMO_AGGIORNAMENTO:
			try {
				Calendar now = Calendar.getInstance();
				now.setTimeInMillis(System.currentTimeMillis());
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(value));
				dateCalendar.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
				vacciniSummary.setUltimo_aggiornamento(dateCalendar.getTime());
			} catch (ParseException e) {
				logger.error("Date format error", e);
			}
			break;
		case AREA:
			vacciniSummary.setArea(value);
			break;
		case DOSI_CONSEGNATE:
			vacciniSummary.setDosi_consegnate(FileUtils.convertStringToInteger(value));
			break;
		case DOSI_SOMMINISTRATE: 
			vacciniSummary.setDosi_somministrate(FileUtils.convertStringToInteger(value));
			break;
		case PERCENTUALE_SOMMINISTRAZIONE:
			vacciniSummary.setPercentuale_somministrazione(FileUtils.convertStringToDouble(value));
			break;
		default:
            logger.warn("Unexpected value: ", fields[i]);
		}
	}

	@Override
	protected DataVacciniSummary getInstance() {
		return new DataVacciniSummary();
	}

}

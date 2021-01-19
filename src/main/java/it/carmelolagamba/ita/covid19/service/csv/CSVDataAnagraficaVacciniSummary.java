package it.carmelolagamba.ita.covid19.service.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataAnagraficaVacciniSummary;
import it.carmelolagamba.ita.covid19.domain.DataAnagraficaVacciniSummary.FIELD;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
public class CSVDataAnagraficaVacciniSummary extends AbstractCSVDataReader<DataAnagraficaVacciniSummary> {

	private static Logger logger = LoggerFactory.getLogger(CSVDataAnagraficaVacciniSummary.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected void map(DataAnagraficaVacciniSummary anagraficaVacciniSummary, int i, String value) {

		final FIELD[] fields = FIELD.values();
		switch (fields[i]) {
		case ULTIMO_AGGIORNAMENTO:
			try {
				Calendar now = Calendar.getInstance();
				now.setTimeInMillis(System.currentTimeMillis());
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(value));
				dateCalendar.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
				anagraficaVacciniSummary.setUltimo_aggiornamento(dateCalendar.getTime());
			} catch (ParseException e) {
				logger.error("Date format error", e);
			}
			break;
		case FASCIA_ANAGRAFICA:
			anagraficaVacciniSummary.setFascia_anagrafica(value);
			break;
		case TOTALE:
			anagraficaVacciniSummary.setTotale(FileUtils.convertStringToInteger(value));
			break;
		case SESSO_MASCHILE:
			anagraficaVacciniSummary.setSesso_maschile(FileUtils.convertStringToInteger(value));
			break;
		case SESSO_FEMMINILE:
			anagraficaVacciniSummary.setSesso_femminile(FileUtils.convertStringToInteger(value));
			break;
		case PRIMA_DOSE:
			anagraficaVacciniSummary.setPrima_dose(FileUtils.convertStringToInteger(value));
			break;
		case SECONDA_DOSE:
			anagraficaVacciniSummary.setSeconda_dose(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_OSS:
			anagraficaVacciniSummary.setCategoria_operatori_sanitari_sociosanitari(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_OVER80:
			anagraficaVacciniSummary.setCategoria_over80(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_PERSONALE_NON_SANITARIO:
			anagraficaVacciniSummary.setCategoria_personale_non_sanitario(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_RSA:
			anagraficaVacciniSummary.setCategoria_ospiti_rsa(FileUtils.convertStringToInteger(value));
			break;
		default:
			logger.warn("Unexpected value: ", fields[i]);
		}
	}

	@Override
	protected DataAnagraficaVacciniSummary getInstance() {
		return new DataAnagraficaVacciniSummary();
	}

}

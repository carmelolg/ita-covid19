package it.carmelolagamba.ita.covid19.service.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary.FIELD;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
public class CSVDataVacciniSomministrazioneRegionaleSummary
		extends AbstractCSVDataReader<DataVacciniSomministrazioneRegionaleSummary> {

	private static Logger logger = LoggerFactory.getLogger(CSVDataVacciniSomministrazioneRegionaleSummary.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected void map(DataVacciniSomministrazioneRegionaleSummary vacciniSomministrazioneRegionaleSummary, int i,
			String value) {

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		vacciniSomministrazioneRegionaleSummary.setUltimo_aggiornamento_interno(now.getTime());

		final FIELD[] fields = FIELD.values();
		switch (fields[i]) {
		case AREA:
			vacciniSomministrazioneRegionaleSummary.setArea(value);
			break;
		case NOME_AREA:
			vacciniSomministrazioneRegionaleSummary.setArea_descrizione(value);
			break;
		case FASCIA_ANAGRAFICA:
			vacciniSomministrazioneRegionaleSummary.setFascia_anagrafica(value);
			break;
		case SESSO_MASCHILE:
			vacciniSomministrazioneRegionaleSummary.setSesso_maschile(FileUtils.convertStringToInteger(value));
			break;
		case SESSO_FEMMINILE:
			vacciniSomministrazioneRegionaleSummary.setSesso_femminile(FileUtils.convertStringToInteger(value));
			break;
		case PRIMA_DOSE:
			vacciniSomministrazioneRegionaleSummary.setPrima_dose(FileUtils.convertStringToInteger(value));
			break;
		case SECONDA_DOSE:
			vacciniSomministrazioneRegionaleSummary.setSeconda_dose(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_OSS:
			vacciniSomministrazioneRegionaleSummary
					.setCategoria_operatori_sanitari_sociosanitari(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_OVER80:
			vacciniSomministrazioneRegionaleSummary.setCategoria_over80(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_PERSONALE_NON_SANITARIO:
			vacciniSomministrazioneRegionaleSummary
					.setCategoria_personale_non_sanitario(FileUtils.convertStringToInteger(value));
			break;
		case CATEGORIA_RSA:
			vacciniSomministrazioneRegionaleSummary.setCategoria_ospiti_rsa(FileUtils.convertStringToInteger(value));
			break;
		case DATA_SOMMINISTRAZIONE:
			try {
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(value));
				vacciniSomministrazioneRegionaleSummary.setData_somministrazione(dateCalendar.getTime());
			} catch (ParseException e) {
				logger.error("Date format error", e);
			}
			break;
		case FORNITORE:
			vacciniSomministrazioneRegionaleSummary.setFornitore(value);
			break;
		default:
			logger.warn("Unexpected value: {}", value);
		}
	}

	@Override
	protected DataVacciniSomministrazioneRegionaleSummary getInstance() {
		return new DataVacciniSomministrazioneRegionaleSummary();
	}

}

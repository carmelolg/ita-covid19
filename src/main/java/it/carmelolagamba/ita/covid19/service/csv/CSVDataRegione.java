package it.carmelolagamba.ita.covid19.service.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.domain.DataRegione.FIELD;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
public class CSVDataRegione extends AbstractCSVDataReader<DataRegione> {

	private static Logger logger = LoggerFactory.getLogger(CSVDataRegione.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected DataRegione getInstance() {
		return new DataRegione();
	}

	@Override
	protected void map(DataRegione dataRegione, int i, String value) {

		final FIELD[] fields = FIELD.values();
		switch (fields[i]) {
		case DATA:
			try {
				Calendar dateCalendar = Calendar.getInstance();
				dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value));
				dateCalendar.set(Calendar.HOUR_OF_DAY, 18);
				dataRegione.setData(dateCalendar.getTime());
			} catch (ParseException e) {
				logger.error("Date format error", e);
			}
			break;
		case STATO:
			dataRegione.setStato(value);
			break;
		case CODICE_REGIONE:
			dataRegione.setCodice_regione(FileUtils.convertStringToInteger(value));
			break;
		case DENOMINAZIONE_REGIONE:
			dataRegione.setDenominazione_regione(value);
			break;
		case LAT:
			dataRegione.setLat(value);
			break;
		case LON:
			dataRegione.setLon(value);
			break;
		case RICOVERATI_CON_SINTOMI:
			dataRegione.setRicoverati_con_sintomi(FileUtils.convertStringToInteger(value));
			break;
		case TERAPIA_INTENSIVA:
			dataRegione.setTerapia_intensiva(FileUtils.convertStringToInteger(value));
			break;
		case TOTALE_OSPEDALIZZATI:
			dataRegione.setTotale_ospedalizzati(FileUtils.convertStringToInteger(value));
			break;
		case ISOLAMENTO_DOMICILIARE:
			dataRegione.setIsolamento_domiciliare(FileUtils.convertStringToInteger(value));
			break;
		case TOTALE_POSITIVI:
			dataRegione.setTotale_positivi(FileUtils.convertStringToInteger(value));
			break;
		case VARIAZIONE_TOTALE_POSITIVI:
			dataRegione.setVariazione_totale_positivi(FileUtils.convertStringToInteger(value));
			break;
		case NUOVI_POSITIVI:
			dataRegione.setNuovi_positivi(FileUtils.convertStringToInteger(value));
			break;
		case DIMESSI_GUARITI:
			dataRegione.setDimessi_guariti(FileUtils.convertStringToInteger(value));
			break;
		case DECEDUTI:
			dataRegione.setDeceduti(FileUtils.convertStringToInteger(value));
			break;
		case CASI_DA_SOSPETTO_DIAGNOSTICO:
			break;
		case CASI_DA_SCREENING:
			break;
		case TOTALE_CASI:
			dataRegione.setTotale_casi(FileUtils.convertStringToInteger(value));
			break;
		case TAMPONI:
			dataRegione.setTamponi(FileUtils.convertStringToInteger(value));
			break;
		case CASI_TESTATI:
			dataRegione.setCasi_testati(FileUtils.convertStringToInteger(value));
			break;
		case NOTE:
			dataRegione.setNote_it(value);
			dataRegione.setNote_en(value);
			break;
		case INGRESSI_TERAPIA_INTENSIVA:
			break;
		case NOTE_TEST:
			break;
		case NOTE_CASI:
			break;
		case TAMPONI_TEST_ANTIGENICO_RAPIDO:
			dataRegione.setTamponi_test_antigenico_rapido(FileUtils.convertStringToInteger(value));
			break;
		case TAMPONI_TEST_MOLECOLARE:
			dataRegione.setTamponi_test_molecolare(FileUtils.convertStringToInteger(value));
			break;
		case TOTALE_POSITIVI_TEST_ANTIGENICO_RAPIDO:
			dataRegione.setTotale_positivi_test_antigenico_rapido(FileUtils.convertStringToInteger(value));
			break;
		case TOTALE_POSITIVI_TEST_MOLECOLARE:
			dataRegione.setTotale_positivi_test_molecolare(FileUtils.convertStringToInteger(value));
			break;
		default:
            logger.warn("Unexpected value: {}", value);
		}
	}

}

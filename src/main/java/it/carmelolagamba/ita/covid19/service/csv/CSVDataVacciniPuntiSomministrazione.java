package it.carmelolagamba.ita.covid19.service.csv;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniPuntiSomministrazione;
import it.carmelolagamba.ita.covid19.domain.DataVacciniPuntiSomministrazione.FIELD;

@Component
public class CSVDataVacciniPuntiSomministrazione extends AbstractCSVDataReader<DataVacciniPuntiSomministrazione> {

	private static Logger logger = LoggerFactory.getLogger(CSVDataVacciniPuntiSomministrazione.class);

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected void map(DataVacciniPuntiSomministrazione puntiSomministrazione, int i, String value) {

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());
		puntiSomministrazione.setUltimo_aggiornamento_interno(now.getTime());

		final FIELD[] fields = FIELD.values();
		switch (fields[i]) {
		case AREA:
			puntiSomministrazione.setArea(value);
			break;
		case NOME_AREA:
			//puntiSomministrazione.setArea_descrizione(value);
			break;
		case PROVINCIA:
			puntiSomministrazione.setProvincia(value);
			break;
		case COMUNE:
			puntiSomministrazione.setComune(value);
			break;
		case PRESIDIO_OSPEDALIERO:
			puntiSomministrazione.setPresidio_ospedaliero(value);
			break;
		default:
            logger.debug("Unexpected value: {}", value);
		}
	}

	@Override
	protected DataVacciniPuntiSomministrazione getInstance() {
		return new DataVacciniPuntiSomministrazione();
	}

}

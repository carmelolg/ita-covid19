package it.carmelolagamba.ita.covid19.service.csv;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static it.carmelolagamba.ita.covid19.domain.DataProvincia.FIELD;

@Component
public class CSVDataProvincia extends AbstractCSVDataReader<DataProvincia>{

    private static Logger logger = LoggerFactory.getLogger(CSVDataProvincia.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected DataProvincia getInstance() {
        return new DataProvincia();
    }

    @Override
    protected void map(DataProvincia dataProvincia, int i, String value) {

        final FIELD[] fields = FIELD.values();
        switch (fields[i]) {
            case DATA:
                try {
                    dataProvincia.setData(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    logger.error("Date format error", e);
                }
                break;
            case STATO:
                dataProvincia.setStato(value);
                break;
            case CODICE_REGIONE:
                dataProvincia.setCodice_regione(FileUtils.convertStringToInteger(value));
                break;
            case DENOMINAZIONE_REGIONE:
                dataProvincia.setDenominazione_regione(value);
                break;
            case LAT:
                dataProvincia.setLat(value);
                break;
            case LON:
                dataProvincia.setLon(value);
                break;
            case CODICE_PROVINCIA:
                dataProvincia.setCodice_provincia(FileUtils.convertStringToInteger(value));
                break;
            case DENOMINAZIONE_PROVINCIA:
                dataProvincia.setDenominazione_provincia(value);
                break;
            case SIGLA_PROVINCIA:
                dataProvincia.setSigla_provincia(value);
                break;
            case TOTALE_CASI:
                dataProvincia.setTotale_casi(FileUtils.convertStringToInteger(value));
                break;
            case NOTE_IT:
                dataProvincia.setNote_it(value);
                break;
            case NOTE_EN:
                dataProvincia.setNote_en(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fields[i]);
        }
    }

}

package it.carmelolagamba.ita.covid19.service.csv;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static it.carmelolagamba.ita.covid19.domain.DataRegione.FIELD;

@Component
public class CSVDataRegione extends AbstractCSVDataReader<DataRegione>{

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
    protected void map(DataRegione dataRegione, int i, String value){

        final FIELD[] fields = FIELD.values();
        switch (fields[i]){
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
            case TOTALE_CASI:
                dataRegione.setTotale_casi(FileUtils.convertStringToInteger(value));
                break;
            case TAMPONI:
                dataRegione.setTamponi(FileUtils.convertStringToInteger(value));
                break;
            case NOTE_IT:
                dataRegione.setNote_it(value);
                break;
            case NOTE_EN:
                dataRegione.setNote_en(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fields[i]);
        }
    }

}

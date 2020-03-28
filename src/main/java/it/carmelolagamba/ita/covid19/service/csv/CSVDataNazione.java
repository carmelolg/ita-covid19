package it.carmelolagamba.ita.covid19.service.csv;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static it.carmelolagamba.ita.covid19.domain.DataNazione.FIELD;

@Component
public class CSVDataNazione extends AbstractCSVDataReader<DataNazione>{

    private static Logger logger = LoggerFactory.getLogger(CSVDataNazione.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected DataNazione getInstance() {
        return new DataNazione();
    }

    @Override
    protected void map(DataNazione dataNazione, int i, String value){

        final FIELD[] fields = FIELD.values();
        switch (fields[i]){
            case DATA:
                try {
                    dataNazione.setData(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value));
                } catch (ParseException e) {
                    logger.error("Date format error", e);
                }
                break;
            case STATO:
                dataNazione.setStato(value);
                break;
            case RICOVERATI_CON_SINTOMI:
                dataNazione.setRicoverati_con_sintomi(FileUtils.convertStringToInteger(value));
                break;
            case TERAPIA_INTENSIVA:
                dataNazione.setTerapia_intensiva(FileUtils.convertStringToInteger(value));
                break;
            case TOTALE_OSPEDALIZZATI:
                dataNazione.setTotale_ospedalizzati(FileUtils.convertStringToInteger(value));
                break;
            case ISOLAMENTO_DOMICILIARE:
                dataNazione.setIsolamento_domiciliare(FileUtils.convertStringToInteger(value));
                break;
            case TOTALE_ATTUALMENTE_POSITIVI:
                dataNazione.setTotale_attualmente_positivi(FileUtils.convertStringToInteger(value));
                break;
            case NUOVI_ATTUALMENTE_POSITIVI:
                dataNazione.setNuovi_attualmente_positivi(FileUtils.convertStringToInteger(value));
                break;
            case DIMESSI_GUARITI:
                dataNazione.setDimessi_guariti(FileUtils.convertStringToInteger(value));
                break;
            case DECEDUTI:
                dataNazione.setDeceduti(FileUtils.convertStringToInteger(value));
                break;
            case TOTALE_CASI:
                dataNazione.setTotale_casi(FileUtils.convertStringToInteger(value));
                break;
            case TAMPONI:
                dataNazione.setTamponi(FileUtils.convertStringToInteger(value));
                break;
            case NOTE_IT:
                dataNazione.setNote_it(value);
                break;
            case NOTE_EN:
                dataNazione.setNote_en(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fields[i]);
        }
    }

}

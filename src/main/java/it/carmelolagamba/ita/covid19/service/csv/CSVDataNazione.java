package it.carmelolagamba.ita.covid19.service.csv;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
                    Calendar dateCalendar = Calendar.getInstance();
                    dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value));
                    dateCalendar.set(Calendar.HOUR_OF_DAY, 18);
                    dataNazione.setData(dateCalendar.getTime());
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
            case TOTALE_POSITIVI:
                dataNazione.setTotale_positivi(FileUtils.convertStringToInteger(value));
                break;
            case VARIAZIONE_TOTALE_POSITIVI:
                dataNazione.setVariazione_totale_positivi(FileUtils.convertStringToInteger(value));
                break;
            case NUOVI_POSITIVI:
                dataNazione.setNuovi_positivi(FileUtils.convertStringToInteger(value));
                break;
            case DIMESSI_GUARITI:
                dataNazione.setDimessi_guariti(FileUtils.convertStringToInteger(value));
                break;
            case DECEDUTI:
                dataNazione.setDeceduti(FileUtils.convertStringToInteger(value));
                break;
            case CASI_DA_SOSPETTO_DIAGNOSTICO:
                break;
            case CASI_DA_SCREENING:
                break;
            case TOTALE_CASI:
                dataNazione.setTotale_casi(FileUtils.convertStringToInteger(value));
                break;
            case TAMPONI:
                dataNazione.setTamponi(FileUtils.convertStringToInteger(value));
                break;
            case CASI_TESTATI:
                dataNazione.setCasi_testati(FileUtils.convertStringToInteger(value));
                break;
            case NOTE:
                dataNazione.setNote_it(value);
                dataNazione.setNote_en(value);
                break;
            case INGRESSI_TERAPIA_INTENSIVA:
            	break;
            case NOTE_TEST:
            	break;
            case NOTE_CASI:
            	break;
            case TAMPONI_TEST_ANTIGENICO_RAPIDO:
            	dataNazione.setTamponi_test_antigenico_rapido(FileUtils.convertStringToInteger(value));
            	break;
            case TAMPONI_TEST_MOLECOLARE:
            	dataNazione.setTamponi_test_molecolare(FileUtils.convertStringToInteger(value));
            	break;
            case TOTALE_POSITIVI_TEST_ANTIGENICO_RAPIDO:
            	dataNazione.setTotale_positivi_test_antigenico_rapido(FileUtils.convertStringToInteger(value));
            	break;
            case TOTALE_POSITIVI_TEST_MOLECOLARE:
            	dataNazione.setTotale_positivi_test_molecolare(FileUtils.convertStringToInteger(value));
            	break;
            default:
                logger.warn("Unexpected value: ", fields[i]);
        }
    }

}

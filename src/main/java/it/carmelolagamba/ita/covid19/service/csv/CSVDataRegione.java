package it.carmelolagamba.ita.covid19.service.csv;

import com.opencsv.CSVReader;
import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.carmelolagamba.ita.covid19.domain.DataRegione.FIELD;

@Component
public class CSVDataRegione {

    private static Logger logger = LoggerFactory.getLogger(CSVDataRegione.class);

    public List<DataRegione> convertRegionDataByFilename(String filename) throws Exception {

        List<DataRegione> dataRegioneList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        logger.info("Leggo il file: {}", filename);

        try (CSVReader csvReader = new CSVReader(new FileReader(filename));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                rows.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw e;
        }

        if(rows.size() == 0){
            throw new Exception("Nessun dato estrapolato dal csv");
        }

        rows.remove(0);

        rows.forEach(row -> {
            DataRegione dataRegione = new DataRegione();
            for (int i = 0; i < row.size(); i++){
                String value = row.get(i);
                map(dataRegione, i, value);
            }
            dataRegioneList.add(dataRegione);
        });

        return dataRegioneList;
    }

    private void map(DataRegione dataRegione, int i, String value){

        final FIELD[] fields = FIELD.values();
        switch (fields[i]){
            case DATA:
                try {
                    dataRegione.setData(new SimpleDateFormat("yyyy-MM-dd").parse(value));
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
            case TOTALE_ATTUALMENTE_POSITIVI:
                dataRegione.setTotale_attualmente_positivi(FileUtils.convertStringToInteger(value));
                break;
            case NUOVI_ATTUALMENTE_POSITIVI:
                dataRegione.setNuovi_attualmente_positivi(FileUtils.convertStringToInteger(value));
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

package it.carmelolagamba.ita.covid19.service.csv;

import com.opencsv.CSVReader;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
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

import static it.carmelolagamba.ita.covid19.domain.DataProvincia.FIELD;

@Component
public class CSVDataProvincia {

    private static Logger logger = LoggerFactory.getLogger(CSVDataProvincia.class);

    // TODO da rifare con i generics perché codice duplicato con CSVDataRegioni
    public List<DataProvincia> convertProvinciaDataByFilename(String filename) throws Exception {

        List<DataProvincia> dataProvinciaList = new ArrayList<>();
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

        if (rows.size() == 0) {
            throw new Exception("Nessun dato estrapolato dal csv");
        }

        rows.remove(0);

        rows.forEach(row -> {
            DataProvincia dataProvincia = new DataProvincia();
            for (int i = 0; i < row.size(); i++) {
                String value = row.get(i);
                map(dataProvincia, i, value);
            }
            dataProvinciaList.add(dataProvincia);
        });

        return dataProvinciaList;
    }

    private void map(DataProvincia dataProvincia, int i, String value) {

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

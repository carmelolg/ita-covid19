package it.carmelolagamba.ita.covid19.service.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.opencsv.CSVReader;

public abstract class AbstractCSVDataReader<T> {

    protected abstract Logger getLogger();

    protected abstract T getInstance();

    protected abstract void map(T item, int index, String value);

    public List<T> convertToDataFromFilename(String filename) throws Exception {
        List<T> dataList = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        getLogger().info("Leggo il file: {}", filename);

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
            T data = getInstance();
            for (int i = 0; i < row.size(); i++) {
                String value = row.get(i);
                map(data, i, value);
            }
            dataList.add(data);
        });

        return dataList;
    }
}

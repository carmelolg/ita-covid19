package it.carmelolagamba.ita.covid19.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AndamentoDto {
    private String key;
    private String description;
    List<ResultDto> results = new ArrayList<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ResultDto> getResults() {
        return results;
    }

    public void setResults(List<ResultDto> results) {
        this.results = results;
    }
}

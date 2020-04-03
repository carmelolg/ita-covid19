package it.carmelolagamba.ita.covid19.view;

import java.util.ArrayList;
import java.util.List;

public class GrowthRateDto {
    private String key;
    private String description;
    List<GrowthRateResultDto> results = new ArrayList<>();

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

    public List<GrowthRateResultDto> getResults() {
        return results;
    }

    public void setResults(List<GrowthRateResultDto> results) {
        this.results = results;
    }
}

package it.carmelolagamba.ita.covid19.view;

import java.util.ArrayList;
import java.util.List;

public class ResumeStatsDto {
    private String key;
    private String description;
    List<AllResultDto> results = new ArrayList<>();

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

    public List<AllResultDto> getResults() {
        return results;
    }

    public void setResults(List<AllResultDto> results) {
        this.results = results;
    }
}

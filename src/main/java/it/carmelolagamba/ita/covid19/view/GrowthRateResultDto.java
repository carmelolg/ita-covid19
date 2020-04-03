package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class GrowthRateResultDto {
    private Double value;
    private Date data;

    public GrowthRateResultDto() {
    }

    public GrowthRateResultDto(Double value, Date data) {
        this.value = value;
        this.data = data;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

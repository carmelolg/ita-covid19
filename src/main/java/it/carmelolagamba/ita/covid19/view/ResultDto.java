package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class ResultDto {
    protected int value;
    protected int increaseFromYesterday;
    protected Date data;

    public ResultDto() {
    }

    public ResultDto(int value, Date data) {
        this.value = value;
        this.data = data;
    }

    public ResultDto(int value, int increaseFromYesterday, Date data) {
        this.value = value;
        this.increaseFromYesterday = increaseFromYesterday;
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIncreaseFromYesterday() {
        return increaseFromYesterday;
    }

    public void setIncreaseFromYesterday(int increaseFromYesterday) {
        this.increaseFromYesterday = increaseFromYesterday;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

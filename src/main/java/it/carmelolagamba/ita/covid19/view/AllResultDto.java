package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class AllResultDto {
    private int totalCases;
    private int nowPositives;
    private int recovered;
    private int dead;
    private Date date;

    public AllResultDto() {
    }

    public AllResultDto(int totalCases, int nowPositives, int recovered, int dead, Date date) {
        this.totalCases = totalCases;
        this.nowPositives = nowPositives;
        this.recovered = recovered;
        this.dead = dead;
        this.date = date;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getNowPositives() {
        return nowPositives;
    }

    public void setNowPositives(int nowPositives) {
        this.nowPositives = nowPositives;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date data) {
        this.date = data;
    }
}

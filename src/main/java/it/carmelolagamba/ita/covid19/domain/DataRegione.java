package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;

public class DataRegione {

    public enum FIELD {
        DATA,
        STATO,
        CODICE_REGIONE,
        DENOMINAZIONE_REGIONE,
        LAT,
        LON,
        RICOVERATI_CON_SINTOMI,
        TERAPIA_INTENSIVA,
        TOTALE_OSPEDALIZZATI,
        ISOLAMENTO_DOMICILIARE,
        TOTALE_POSITIVI,
        VARIAZIONE_TOTALE_POSITIVI,
        NUOVI_POSITIVI,
        DIMESSI_GUARITI,
        DECEDUTI,
        TOTALE_CASI,
        TAMPONI,
        NOTE_IT,
        NOTE_EN
    }

    @BsonId
    private ObjectId idProperty;

    private Date data;
    private String stato;
    private int codice_regione;
    private String denominazione_regione;
    private String lat;
    private String lon;
    private int ricoverati_con_sintomi,
            terapia_intensiva,
            totale_ospedalizzati,
            isolamento_domiciliare,
            totale_positivi,
            variazione_totale_positivi,
            nuovi_positivi,
            dimessi_guariti,
            deceduti,
            totale_casi,
            tamponi;
    String note_it,
            note_en;

    public ObjectId getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(ObjectId idProperty) {
        this.idProperty = idProperty;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public int getCodice_regione() {
        return codice_regione;
    }

    public void setCodice_regione(int codice_regione) {
        this.codice_regione = codice_regione;
    }

    public String getDenominazione_regione() {
        return denominazione_regione;
    }

    public void setDenominazione_regione(String denominazione_regione) {
        this.denominazione_regione = denominazione_regione;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getRicoverati_con_sintomi() {
        return ricoverati_con_sintomi;
    }

    public void setRicoverati_con_sintomi(int ricoverati_con_sintomi) {
        this.ricoverati_con_sintomi = ricoverati_con_sintomi;
    }

    public int getTerapia_intensiva() {
        return terapia_intensiva;
    }

    public void setTerapia_intensiva(int terapia_intensiva) {
        this.terapia_intensiva = terapia_intensiva;
    }

    public int getTotale_ospedalizzati() {
        return totale_ospedalizzati;
    }

    public void setTotale_ospedalizzati(int totale_ospedalizzati) {
        this.totale_ospedalizzati = totale_ospedalizzati;
    }

    public int getIsolamento_domiciliare() {
        return isolamento_domiciliare;
    }

    public void setIsolamento_domiciliare(int isolamento_domiciliare) {
        this.isolamento_domiciliare = isolamento_domiciliare;
    }

    public int getTotale_positivi() {
        return totale_positivi;
    }

    public void setTotale_positivi(int totale_positivi) {
        this.totale_positivi = totale_positivi;
    }

    public int getVariazione_totale_positivi() {
        return variazione_totale_positivi;
    }

    public void setVariazione_totale_positivi(int variazione_totale_positivi) {
        this.variazione_totale_positivi = variazione_totale_positivi;
    }

    public int getNuovi_positivi() {
        return nuovi_positivi;
    }

    public void setNuovi_positivi(int nuovi_positivi) {
        this.nuovi_positivi = nuovi_positivi;
    }

    public int getDimessi_guariti() {
        return dimessi_guariti;
    }

    public void setDimessi_guariti(int dimessi_guariti) {
        this.dimessi_guariti = dimessi_guariti;
    }

    public int getDeceduti() {
        return deceduti;
    }

    public void setDeceduti(int deceduti) {
        this.deceduti = deceduti;
    }

    public int getTotale_casi() {
        return totale_casi;
    }

    public void setTotale_casi(int totale_casi) {
        this.totale_casi = totale_casi;
    }

    public int getTamponi() {
        return tamponi;
    }

    public void setTamponi(int tamponi) {
        this.tamponi = tamponi;
    }

    public String getNote_it() {
        return note_it;
    }

    public void setNote_it(String note_it) {
        this.note_it = note_it;
    }

    public String getNote_en() {
        return note_en;
    }

    public void setNote_en(String note_en) {
        this.note_en = note_en;
    }
}

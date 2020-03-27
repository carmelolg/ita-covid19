package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;

public class DataProvincia {

    public enum FIELD {
        DATA,
        STATO,
        CODICE_REGIONE,
        DENOMINAZIONE_REGIONE,
        CODICE_PROVINCIA,
        DENOMINAZIONE_PROVINCIA,
        SIGLA_PROVINCIA,
        LAT,
        LON,
        TOTALE_CASI,
        NOTE_IT,
        NOTE_EN
    }

    @BsonId
    private ObjectId idProperty;
    private Date data;
    private String stato;
    private int codice_regione;
    private String denominazione_regione;
    private int codice_provincia;
    private String denominazione_provincia;
    private String sigla_provincia;
    private String lat;
    private String lon;
    private int totale_casi;
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

    public int getCodice_provincia() {
        return codice_provincia;
    }

    public void setCodice_provincia(int codice_provincia) {
        this.codice_provincia = codice_provincia;
    }

    public String getDenominazione_provincia() {
        return denominazione_provincia;
    }

    public void setDenominazione_provincia(String denominazione_provincia) {
        this.denominazione_provincia = denominazione_provincia;
    }

    public String getSigla_provincia() {
        return sigla_provincia;
    }

    public void setSigla_provincia(String sigla_provincia) {
        this.sigla_provincia = sigla_provincia;
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

    public int getTotale_casi() {
        return totale_casi;
    }

    public void setTotale_casi(int totale_casi) {
        this.totale_casi = totale_casi;
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

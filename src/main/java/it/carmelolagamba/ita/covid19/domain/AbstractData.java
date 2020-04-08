package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;

public abstract class AbstractData {

    @BsonId
    protected ObjectId idProperty;
    protected Date data;
    protected String stato;
    protected String lat;
    protected String lon;
    protected int totale_casi;
    protected String note_it,
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

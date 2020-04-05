package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Provincia {

    @BsonId
    private ObjectId idProperty;

    String nome;
    String regione;

    public ObjectId getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(ObjectId idProperty) {
        this.idProperty = idProperty;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
}

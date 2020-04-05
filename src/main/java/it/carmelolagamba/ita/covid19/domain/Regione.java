package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;

public class Regione {

    @BsonId
    private ObjectId idProperty;

    String nome;

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
}

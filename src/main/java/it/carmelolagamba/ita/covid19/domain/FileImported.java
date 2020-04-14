package it.carmelolagamba.ita.covid19.domain;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.Date;

public class FileImported {

    @BsonId
    ObjectId idProperty;

    private String filename;
    private Date migrationDate;

    public FileImported() {
    }

    public FileImported(String filename) {
        this.filename = filename;
    }

    public FileImported(String filename, Date date) {
        this.filename = filename;
        this.migrationDate = date;
    }

    public ObjectId getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(ObjectId idProperty) {
        this.idProperty = idProperty;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getMigrationDate() {
        return migrationDate;
    }

    public void setMigrationDate(Date migrationDate) {
        this.migrationDate = migrationDate;
    }
}

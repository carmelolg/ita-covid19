package it.carmelolagamba.ita.covid19.domain;

public class DataProvincia extends AbstractData {

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

    private int codice_regione;
    private String denominazione_regione;
    private int codice_provincia;
    private String denominazione_provincia;
    private String sigla_provincia;

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


}

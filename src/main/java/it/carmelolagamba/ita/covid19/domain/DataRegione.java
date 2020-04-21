package it.carmelolagamba.ita.covid19.domain;

public class DataRegione extends AbstractFullData{

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
        CASI_TESTATI,
        NOTE_IT,
        NOTE_EN
    }

    private int codice_regione;
    private String denominazione_regione;


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

}

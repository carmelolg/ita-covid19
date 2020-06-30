package it.carmelolagamba.ita.covid19.domain;

public class DataNazione extends AbstractFullData{

    public enum FIELD {
        DATA,
        STATO,
        RICOVERATI_CON_SINTOMI,
        TERAPIA_INTENSIVA,
        TOTALE_OSPEDALIZZATI,
        ISOLAMENTO_DOMICILIARE,
        TOTALE_POSITIVI,
        VARIAZIONE_TOTALE_POSITIVI,
        NUOVI_POSITIVI,
        DIMESSI_GUARITI,
        DECEDUTI,
        CASI_DA_SOSPETTO_DIAGNOSTICO,
        CASI_DA_SCREENING,
        TOTALE_CASI,
        TAMPONI,
        CASI_TESTATI,
        NOTE
    }
}

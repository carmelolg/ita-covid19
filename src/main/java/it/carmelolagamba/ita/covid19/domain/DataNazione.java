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
        TOTALE_CASI,
        TAMPONI,
        NOTE_IT,
        NOTE_EN
    }
}

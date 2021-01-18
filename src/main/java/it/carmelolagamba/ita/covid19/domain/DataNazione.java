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
        NOTE,
        INGRESSI_TERAPIA_INTENSIVA,
        NOTE_TEST,
        NOTE_CASI,
        TOTALE_POSITIVI_TEST_MOLECOLARE,
        TOTALE_POSITIVI_TEST_ANTIGENICO_RAPIDO, 
        TAMPONI_TEST_MOLECOLARE,
        TAMPONI_TEST_ANTIGENICO_RAPIDO
    }
}

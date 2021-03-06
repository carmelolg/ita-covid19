package it.carmelolagamba.ita.covid19.domain;

public class AbstractFullData extends AbstractData {

	protected int ricoverati_con_sintomi, terapia_intensiva, totale_ospedalizzati, isolamento_domiciliare,
			totale_positivi, variazione_totale_positivi, nuovi_positivi, dimessi_guariti, deceduti, totale_casi,
			tamponi, casi_testati, totale_positivi_test_molecolare, totale_positivi_test_antigenico_rapido,
			tamponi_test_molecolare, tamponi_test_antigenico_rapido;

	public int getRicoverati_con_sintomi() {
		return ricoverati_con_sintomi;
	}

	public void setRicoverati_con_sintomi(int ricoverati_con_sintomi) {
		this.ricoverati_con_sintomi = ricoverati_con_sintomi;
	}

	public int getTerapia_intensiva() {
		return terapia_intensiva;
	}

	public void setTerapia_intensiva(int terapia_intensiva) {
		this.terapia_intensiva = terapia_intensiva;
	}

	public int getTotale_ospedalizzati() {
		return totale_ospedalizzati;
	}

	public void setTotale_ospedalizzati(int totale_ospedalizzati) {
		this.totale_ospedalizzati = totale_ospedalizzati;
	}

	public int getIsolamento_domiciliare() {
		return isolamento_domiciliare;
	}

	public void setIsolamento_domiciliare(int isolamento_domiciliare) {
		this.isolamento_domiciliare = isolamento_domiciliare;
	}

	public int getTotale_positivi() {
		return totale_positivi;
	}

	public void setTotale_positivi(int totale_positivi) {
		this.totale_positivi = totale_positivi;
	}

	public int getVariazione_totale_positivi() {
		return variazione_totale_positivi;
	}

	public void setVariazione_totale_positivi(int variazione_totale_positivi) {
		this.variazione_totale_positivi = variazione_totale_positivi;
	}

	public int getNuovi_positivi() {
		return nuovi_positivi;
	}

	public void setNuovi_positivi(int nuovi_positivi) {
		this.nuovi_positivi = nuovi_positivi;
	}

	public int getDimessi_guariti() {
		return dimessi_guariti;
	}

	public void setDimessi_guariti(int dimessi_guariti) {
		this.dimessi_guariti = dimessi_guariti;
	}

	public int getDeceduti() {
		return deceduti;
	}

	public void setDeceduti(int deceduti) {
		this.deceduti = deceduti;
	}

	@Override
	public int getTotale_casi() {
		return totale_casi;
	}

	@Override
	public void setTotale_casi(int totale_casi) {
		this.totale_casi = totale_casi;
	}

	public int getTamponi() {
		return tamponi;
	}

	public void setTamponi(int tamponi) {
		this.tamponi = tamponi;
	}

	public int getCasi_testati() {
		return casi_testati;
	}

	public void setCasi_testati(int casi_testati) {
		this.casi_testati = casi_testati;
	}

	public int getTotale_positivi_test_molecolare() {
		return totale_positivi_test_molecolare;
	}

	public void setTotale_positivi_test_molecolare(int totale_positivi_test_molecolare) {
		this.totale_positivi_test_molecolare = totale_positivi_test_molecolare;
	}

	public int getTotale_positivi_test_antigenico_rapido() {
		return totale_positivi_test_antigenico_rapido;
	}

	public void setTotale_positivi_test_antigenico_rapido(int totale_positivi_test_antigenico_rapido) {
		this.totale_positivi_test_antigenico_rapido = totale_positivi_test_antigenico_rapido;
	}

	public int getTamponi_test_molecolare() {
		return tamponi_test_molecolare;
	}

	public void setTamponi_test_molecolare(int tamponi_test_molecolare) {
		this.tamponi_test_molecolare = tamponi_test_molecolare;
	}

	public int getTamponi_test_antigenico_rapido() {
		return tamponi_test_antigenico_rapido;
	}

	public void setTamponi_test_antigenico_rapido(int tamponi_test_antigenico_rapido) {
		this.tamponi_test_antigenico_rapido = tamponi_test_antigenico_rapido;
	}

}

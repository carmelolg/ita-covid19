package it.carmelolagamba.ita.covid19.domain;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class DataVacciniSomministrazioneRegionaleSummary {

	@BsonId
	private ObjectId idProperty;

	private Date data_somministrazione;
	private String fornitore;
	private String area, area_descrizione;
	private String fascia_anagrafica;
	private int sesso_maschile, sesso_femminile, categoria_operatori_sanitari_sociosanitari,
			categoria_personale_non_sanitario, categoria_ospiti_rsa, categoria_over80, categoria_forze_armate,
			categoria_personale_scolastico, prima_dose, seconda_dose, pregressa_infezione, dose_aggiuntiva, dose_booster;
	private Date ultimo_aggiornamento_interno;

	public enum FIELD {
		DATA_SOMMINISTRAZIONE, FORNITORE, AREA, FASCIA_ANAGRAFICA, SESSO_MASCHILE, SESSO_FEMMINILE, CATEGORIA_OSS,
		CATEGORIA_PERSONALE_NON_SANITARIO, CATEGORIA_RSA, CATEGORIA_OVER80, CATEGORIA_FORZE_ARMATE,
		CATEGORIA_PERSONALE_SCOLASTICO, PRIMA_DOSE, SECONDA_DOSE, CODICE_NUTS1, CODICE_NUTS2, CODICE_REGIONE_ISTAT,
		NOME_AREA, PREGRESSA_INFEZIONE, DOSE_AGGIUNTIVA, DOSE_BOOSTER
	}

	public ObjectId getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(ObjectId idProperty) {
		this.idProperty = idProperty;
	}

	public Date getData_somministrazione() {
		return data_somministrazione;
	}

	public void setData_somministrazione(Date data_somministrazione) {
		this.data_somministrazione = data_somministrazione;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea_descrizione() {
		return area_descrizione;
	}

	public void setArea_descrizione(String area_descrizione) {
		this.area_descrizione = area_descrizione;
	}

	public String getFascia_anagrafica() {
		return fascia_anagrafica;
	}

	public void setFascia_anagrafica(String fascia_anagrafica) {
		this.fascia_anagrafica = fascia_anagrafica;
	}

	public int getSesso_maschile() {
		return sesso_maschile;
	}

	public void setSesso_maschile(int sesso_maschile) {
		this.sesso_maschile = sesso_maschile;
	}

	public int getSesso_femminile() {
		return sesso_femminile;
	}

	public void setSesso_femminile(int sesso_femminile) {
		this.sesso_femminile = sesso_femminile;
	}

	public int getCategoria_operatori_sanitari_sociosanitari() {
		return categoria_operatori_sanitari_sociosanitari;
	}

	public void setCategoria_operatori_sanitari_sociosanitari(int categoria_operatori_sanitari_sociosanitari) {
		this.categoria_operatori_sanitari_sociosanitari = categoria_operatori_sanitari_sociosanitari;
	}

	public int getCategoria_personale_non_sanitario() {
		return categoria_personale_non_sanitario;
	}

	public void setCategoria_personale_non_sanitario(int categoria_personale_non_sanitario) {
		this.categoria_personale_non_sanitario = categoria_personale_non_sanitario;
	}

	public int getCategoria_ospiti_rsa() {
		return categoria_ospiti_rsa;
	}

	public void setCategoria_ospiti_rsa(int categoria_ospiti_rsa) {
		this.categoria_ospiti_rsa = categoria_ospiti_rsa;
	}

	public int getCategoria_over80() {
		return categoria_over80;
	}

	public void setCategoria_over80(int categoria_over80) {
		this.categoria_over80 = categoria_over80;
	}

	public int getPrima_dose() {
		return prima_dose;
	}

	public void setPrima_dose(int prima_dose) {
		this.prima_dose = prima_dose;
	}

	public int getSeconda_dose() {
		return seconda_dose;
	}

	public void setSeconda_dose(int seconda_dose) {
		this.seconda_dose = seconda_dose;
	}

	public Date getUltimo_aggiornamento_interno() {
		return ultimo_aggiornamento_interno;
	}

	public void setUltimo_aggiornamento_interno(Date ultimo_aggiornamento_interno) {
		this.ultimo_aggiornamento_interno = ultimo_aggiornamento_interno;
	}

	public int getCategoria_forze_armate() {
		return categoria_forze_armate;
	}

	public void setCategoria_forze_armate(int categoria_forze_armate) {
		this.categoria_forze_armate = categoria_forze_armate;
	}

	public int getCategoria_personale_scolastico() {
		return categoria_personale_scolastico;
	}

	public void setCategoria_personale_scolastico(int categoria_personale_scolastico) {
		this.categoria_personale_scolastico = categoria_personale_scolastico;
	}

	public int getPregressa_infezione() {
		return pregressa_infezione;
	}

	public void setPregressa_infezione(int pregressa_infezione) {
		this.pregressa_infezione = pregressa_infezione;
	}

	public int getDose_aggiuntiva() {
		return dose_aggiuntiva;
	}

	public void setDose_aggiuntiva(int dose_aggiuntiva) {
		this.dose_aggiuntiva = dose_aggiuntiva;
	}

	public int getDose_booster() {
		return dose_booster;
	}

	public void setDose_booster(int dose_booster) {
		this.dose_booster = dose_booster;
	}
}

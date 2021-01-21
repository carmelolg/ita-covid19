package it.carmelolagamba.ita.covid19.domain;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class DataVacciniAnagraficaSummary {

	@BsonId
	private ObjectId idProperty;

	private String fascia_anagrafica;
	private int totale, sesso_maschile, sesso_femminile, categoria_operatori_sanitari_sociosanitari,
			categoria_personale_non_sanitario, categoria_ospiti_rsa, categoria_over80, prima_dose, seconda_dose;
	private Date ultimo_aggiornamento;
	private Date ultimo_aggiornamento_interno;

	public enum FIELD {
		FASCIA_ANAGRAFICA, TOTALE, SESSO_MASCHILE, SESSO_FEMMINILE, CATEGORIA_OSS, CATEGORIA_PERSONALE_NON_SANITARIO,
		CATEGORIA_RSA, CATEGORIA_OVER80, PRIMA_DOSE, SECONDA_DOSE, ULTIMO_AGGIORNAMENTO
	}

	public ObjectId getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(ObjectId idProperty) {
		this.idProperty = idProperty;
	}

	public String getFascia_anagrafica() {
		return fascia_anagrafica;
	}

	public void setFascia_anagrafica(String fascia_anagrafica) {
		this.fascia_anagrafica = fascia_anagrafica;
	}

	public int getTotale() {
		return totale;
	}

	public void setTotale(int totale) {
		this.totale = totale;
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

	public Date getUltimo_aggiornamento() {
		return ultimo_aggiornamento;
	}

	public void setUltimo_aggiornamento(Date ultimo_aggiornamento) {
		this.ultimo_aggiornamento = ultimo_aggiornamento;
	}

	public Date getUltimo_aggiornamento_interno() {
		return ultimo_aggiornamento_interno;
	}

	public void setUltimo_aggiornamento_interno(Date ultimo_aggiornamento_interno) {
		this.ultimo_aggiornamento_interno = ultimo_aggiornamento_interno;
	}

}

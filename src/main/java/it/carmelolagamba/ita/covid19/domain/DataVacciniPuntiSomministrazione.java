package it.carmelolagamba.ita.covid19.domain;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class DataVacciniPuntiSomministrazione {

	@BsonId
	private ObjectId idProperty;

	private String area, area_descrizione;
	private String provincia;
	private String comune;
	private String presidio_ospedaliero;
	private Date ultimo_aggiornamento_interno;

	public enum FIELD {
		AREA, PROVINCIA, COMUNE, PRESIDIO_OSPEDALIERO, CODICE_NUTS1,
		CODICE_NUTS2, CODICE_REGIONE_ISTAT, NOME_AREA
	}

	public ObjectId getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(ObjectId idProperty) {
		this.idProperty = idProperty;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getPresidio_ospedaliero() {
		return presidio_ospedaliero;
	}

	public void setPresidio_ospedaliero(String presidio_ospedaliero) {
		this.presidio_ospedaliero = presidio_ospedaliero;
	}

	public Date getUltimo_aggiornamento_interno() {
		return ultimo_aggiornamento_interno;
	}

	public void setUltimo_aggiornamento_interno(Date ultimo_aggiornamento_interno) {
		this.ultimo_aggiornamento_interno = ultimo_aggiornamento_interno;
	}

}

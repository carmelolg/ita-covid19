package it.carmelolagamba.ita.covid19.domain;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class DataVacciniSummary {

	@BsonId
	private ObjectId idProperty;

	private String area;
	private int dosi_somministrate;
	private int dosi_consegnate;
	private double percentuale_somministrazione;
	private Date ultimo_aggiornamento;

	public enum FIELD {
		AREA,
		DOSI_SOMMINISTRATE,
		DOSI_CONSEGNATE,
		PERCENTUALE_SOMMINISTRAZIONE,
		ULTIMO_AGGIORNAMENTO
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

	public int getDosi_somministrate() {
		return dosi_somministrate;
	}

	public void setDosi_somministrate(int dosi_somministrate) {
		this.dosi_somministrate = dosi_somministrate;
	}

	public int getDosi_consegnate() {
		return dosi_consegnate;
	}

	public void setDosi_consegnate(int dosi_consegnate) {
		this.dosi_consegnate = dosi_consegnate;
	}

	public double getPercentuale_somministrazione() {
		return percentuale_somministrazione;
	}

	public void setPercentuale_somministrazione(double percentuale_somministrazione) {
		this.percentuale_somministrazione = percentuale_somministrazione;
	}

	public Date getUltimo_aggiornamento() {
		return ultimo_aggiornamento;
	}

	public void setUltimo_aggiornamento(Date ultimo_aggiornamento) {
		this.ultimo_aggiornamento = ultimo_aggiornamento;
	}

}

package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VaccinoTotaleRegioneDto {

	private String area;
	private String areaDescrizione;
	private int dosiSomministrate;
	private int dosiConsegnate;
	private double percentualeSomministrazione;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Rome")
	private Date ultimoAggiornamento;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Rome")
	private Date ultimoAggiornamentoInterno;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaDescrizione() {
		return areaDescrizione;
	}
	public void setAreaDescrizione(String areaDescrizione) {
		this.areaDescrizione = areaDescrizione;
	}
	public int getDosiSomministrate() {
		return dosiSomministrate;
	}
	public void setDosiSomministrate(int dosiSomministrate) {
		this.dosiSomministrate = dosiSomministrate;
	}
	public int getDosiConsegnate() {
		return dosiConsegnate;
	}
	public void setDosiConsegnate(int dosiConsegnate) {
		this.dosiConsegnate = dosiConsegnate;
	}
	public double getPercentualeSomministrazione() {
		return percentualeSomministrazione;
	}
	public void setPercentualeSomministrazione(double percentualeSomministrazione) {
		this.percentualeSomministrazione = percentualeSomministrazione;
	}
	public Date getUltimoAggiornamento() {
		return ultimoAggiornamento;
	}
	public void setUltimoAggiornamento(Date ultimoAggiornamento) {
		this.ultimoAggiornamento = ultimoAggiornamento;
	}
	public Date getUltimoAggiornamentoInterno() {
		return ultimoAggiornamentoInterno;
	}
	public void setUltimoAggiornamentoInterno(Date ultimoAggiornamentoInterno) {
		this.ultimoAggiornamentoInterno = ultimoAggiornamentoInterno;
	}

	

}

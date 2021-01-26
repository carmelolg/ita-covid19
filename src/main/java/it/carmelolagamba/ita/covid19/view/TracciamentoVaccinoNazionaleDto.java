package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TracciamentoVaccinoNazionaleDto {

	private String fasciaAnagrafica;
	private int sessoMaschile, sessoFemminile, categoriaOss, categoriaNonSanitari, categoriaRsa, categoriaOver80,
			primaDose, secondaDose;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Rome")
	private Date ultimoAggiornamento, ultimoAggiornamentoInterno;

	public String getFasciaAnagrafica() {
		return fasciaAnagrafica;
	}

	public void setFasciaAnagrafica(String fasciaAnagrafica) {
		this.fasciaAnagrafica = fasciaAnagrafica;
	}

	public int getSessoMaschile() {
		return sessoMaschile;
	}

	public void setSessoMaschile(int sessoMaschile) {
		this.sessoMaschile = sessoMaschile;
	}

	public int getSessoFemminile() {
		return sessoFemminile;
	}

	public void setSessoFemminile(int sessoFemminile) {
		this.sessoFemminile = sessoFemminile;
	}

	public int getCategoriaOss() {
		return categoriaOss;
	}

	public void setCategoriaOss(int categoriaOss) {
		this.categoriaOss = categoriaOss;
	}

	public int getCategoriaNonSanitari() {
		return categoriaNonSanitari;
	}

	public void setCategoriaNonSanitari(int categoriaNonSanitari) {
		this.categoriaNonSanitari = categoriaNonSanitari;
	}

	public int getCategoriaRsa() {
		return categoriaRsa;
	}

	public void setCategoriaRsa(int categoriaRsa) {
		this.categoriaRsa = categoriaRsa;
	}

	public int getCategoriaOver80() {
		return categoriaOver80;
	}

	public void setCategoriaOver80(int categoriaOver80) {
		this.categoriaOver80 = categoriaOver80;
	}

	public int getPrimaDose() {
		return primaDose;
	}

	public void setPrimaDose(int primaDose) {
		this.primaDose = primaDose;
	}

	public int getSecondaDose() {
		return secondaDose;
	}

	public void setSecondaDose(int secondaDose) {
		this.secondaDose = secondaDose;
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

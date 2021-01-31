package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TracciamentoVaccinoRegionaleDetailsDto {

	private String area, areaDescrizione;
	private int categoriaOss, categoriaNonSanitari, categoriaRsa, categoriaOver80,
			primaDose, secondaDose;
	
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

	public Date getUltimoAggiornamentoInterno() {
		return ultimoAggiornamentoInterno;
	}

	public void setUltimoAggiornamentoInterno(Date ultimoAggiornamentoInterno) {
		this.ultimoAggiornamentoInterno = ultimoAggiornamentoInterno;
	}

}

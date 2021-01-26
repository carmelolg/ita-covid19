package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class VaccinoPuntoSomministrazioneDto {

	private String area, areaDescrizione;
	private String provincia;
	private String comune;
	private String presidioOspedaliero;
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

	public String getPresidioOspedaliero() {
		return presidioOspedaliero;
	}

	public void setPresidioOspedaliero(String presidioOspedaliero) {
		this.presidioOspedaliero = presidioOspedaliero;
	}

	public Date getUltimoAggiornamentoInterno() {
		return ultimoAggiornamentoInterno;
	}

	public void setUltimoAggiornamentoInterno(Date ultimoAggiornamentoInterno) {
		this.ultimoAggiornamentoInterno = ultimoAggiornamentoInterno;
	}

}

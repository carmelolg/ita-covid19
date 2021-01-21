package it.carmelolagamba.ita.covid19.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "file")
public class FileProperties {

	public String vacciniBaseUrl;
	public String vacciniSummaryFilename;
	public String anagraficaVacciniSummaryFilename;
	public String puntiSomministrazioneFilename;

	public String getVacciniBaseUrl() {
		return vacciniBaseUrl;
	}

	public void setVacciniBaseUrl(String vacciniBaseUrl) {
		this.vacciniBaseUrl = vacciniBaseUrl;
	}

	public String getVacciniSummaryFilename() {
		return vacciniSummaryFilename;
	}

	public void setVacciniSummaryFilename(String vacciniSummaryFilename) {
		this.vacciniSummaryFilename = vacciniSummaryFilename;
	}

	public String getAnagraficaVacciniSummaryFilename() {
		return anagraficaVacciniSummaryFilename;
	}

	public void setAnagraficaVacciniSummaryFilename(String anagraficaVacciniSummaryFilename) {
		this.anagraficaVacciniSummaryFilename = anagraficaVacciniSummaryFilename;
	}

	public String getPuntiSomministrazioneFilename() {
		return puntiSomministrazioneFilename;
	}

	public void setPuntiSomministrazioneFilename(String puntiSomministrazioneFilename) {
		this.puntiSomministrazioneFilename = puntiSomministrazioneFilename;
	}

}

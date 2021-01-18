package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class TamponiResultDto extends ResultDto {

	private int positiviMolecolare;
	private int positiviAntigenicoRapido;
	private int totaleMolecolare;
	private int totaleAntigenicoRapido;

	public TamponiResultDto() {
	}

	public TamponiResultDto(int value, int increaseFromYesterday, int positiviMolecolare, int positiviAntigenicoRapido,
			int totaleMolecolare, int totaleAntigenicoRapido, Date data) {
		super();
		this.value = value;
		this.increaseFromYesterday = increaseFromYesterday;
		this.positiviMolecolare = positiviMolecolare;
		this.positiviAntigenicoRapido = positiviAntigenicoRapido;
		this.totaleMolecolare = totaleMolecolare;
		this.totaleAntigenicoRapido = totaleAntigenicoRapido;
		this.data = data;
	}

	public int getPositiviMolecolare() {
		return positiviMolecolare;
	}

	public void setPositiviMolecolare(int positiviMolecolare) {
		this.positiviMolecolare = positiviMolecolare;
	}

	public int getPositiviAntigenicoRapido() {
		return positiviAntigenicoRapido;
	}

	public void setPositiviAntigenicoRapido(int positiviAntigenicoRapido) {
		this.positiviAntigenicoRapido = positiviAntigenicoRapido;
	}

	public int getTotaleMolecolare() {
		return totaleMolecolare;
	}

	public void setTotaleMolecolare(int totaleMolecolare) {
		this.totaleMolecolare = totaleMolecolare;
	}

	public int getTotaleAntigenicoRapido() {
		return totaleAntigenicoRapido;
	}

	public void setTotaleAntigenicoRapido(int totaleAntigenicoRapido) {
		this.totaleAntigenicoRapido = totaleAntigenicoRapido;
	}

}

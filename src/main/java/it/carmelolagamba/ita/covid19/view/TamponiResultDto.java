package it.carmelolagamba.ita.covid19.view;

import java.util.Date;

public class TamponiResultDto extends ResultDto {

	private int positiviMolecolare;
	private int increaseFromYesterdayPositiviMolecolare;
	private int positiviAntigenicoRapido;
	private int increaseFromYesterdayPositiviAntigenicoRapido;
	private int totaleMolecolare;
	private int increaseFromYesterdayTotaleMolecolare;
	private int totaleAntigenicoRapido;
	private int increaseFromYesterdayTotaleAntigenicoRapido;

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

	public int getIncreaseFromYesterdayPositiviMolecolare() {
		return increaseFromYesterdayPositiviMolecolare;
	}

	public void setIncreaseFromYesterdayPositiviMolecolare(int increaseFromYesterdayPositiviMolecolare) {
		this.increaseFromYesterdayPositiviMolecolare = increaseFromYesterdayPositiviMolecolare;
	}

	public int getIncreaseFromYesterdayPositiviAntigenicoRapido() {
		return increaseFromYesterdayPositiviAntigenicoRapido;
	}

	public void setIncreaseFromYesterdayPositiviAntigenicoRapido(int increaseFromYesterdayPositiviAntigenicoRapido) {
		this.increaseFromYesterdayPositiviAntigenicoRapido = increaseFromYesterdayPositiviAntigenicoRapido;
	}

	public int getIncreaseFromYesterdayTotaleMolecolare() {
		return increaseFromYesterdayTotaleMolecolare;
	}

	public void setIncreaseFromYesterdayTotaleMolecolare(int increaseFromYesterdayTotaleMolecolare) {
		this.increaseFromYesterdayTotaleMolecolare = increaseFromYesterdayTotaleMolecolare;
	}

	public int getIncreaseFromYesterdayTotaleAntigenicoRapido() {
		return increaseFromYesterdayTotaleAntigenicoRapido;
	}

	public void setIncreaseFromYesterdayTotaleAntigenicoRapido(int increaseFromYesterdayTotaleAntigenicoRapido) {
		this.increaseFromYesterdayTotaleAntigenicoRapido = increaseFromYesterdayTotaleAntigenicoRapido;
	}

}

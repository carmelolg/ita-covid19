package it.carmelolagamba.ita.covid19.view;

import java.util.ArrayList;
import java.util.List;

public class TracciamentoVaccinoNazionaleResponseDto {

	private List<TracciamentoVaccinoNazionaleDto> ageGroups = new ArrayList<TracciamentoVaccinoNazionaleDto>();
	private TracciamentoVaccinoNazionaleDto all;

	public List<TracciamentoVaccinoNazionaleDto> getAgeGroups() {
		return ageGroups;
	}

	public void setAgeGroups(List<TracciamentoVaccinoNazionaleDto> ageGroups) {
		this.ageGroups = ageGroups;
	}

	public TracciamentoVaccinoNazionaleDto getAll() {
		return all;
	}

	public void setAll(TracciamentoVaccinoNazionaleDto all) {
		this.all = all;
	}

}

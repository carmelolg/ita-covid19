package it.carmelolagamba.ita.covid19.domain.mapper;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary;
import it.carmelolagamba.ita.covid19.view.VaccinoTotaleRegioneDto;

@Component
public class DataVacciniSummaryMapper {

	public VaccinoTotaleRegioneDto convertEntityToDto(DataVacciniSummary entity) {
		
		VaccinoTotaleRegioneDto bean = new VaccinoTotaleRegioneDto();
		
		bean.setArea(entity.getArea());
		bean.setAreaDescrizione(entity.getArea_descrizione());
		bean.setDosiConsegnate(entity.getDosi_consegnate());
		bean.setDosiSomministrate(entity.getDosi_somministrate());
		bean.setPercentualeSomministrazione(entity.getPercentuale_somministrazione());
		bean.setUltimoAggiornamento(entity.getUltimo_aggiornamento());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());
		
		return bean;
	}
	
}

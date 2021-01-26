package it.carmelolagamba.ita.covid19.domain.mapper;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniPuntiSomministrazione;
import it.carmelolagamba.ita.covid19.view.VaccinoPuntoSomministrazioneDto;

@Component
public class DataVacciniPuntiSomministrazioneMapper {

	public VaccinoPuntoSomministrazioneDto convertEntityToDto(DataVacciniPuntiSomministrazione entity) {
		
		VaccinoPuntoSomministrazioneDto bean = new VaccinoPuntoSomministrazioneDto();
		
		bean.setArea(entity.getArea());
		bean.setAreaDescrizione(entity.getArea_descrizione());
		bean.setComune(entity.getComune());
		bean.setPresidioOspedaliero(entity.getPresidio_ospedaliero());
		bean.setProvincia(entity.getProvincia());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());
		
		return bean;
	}
	
}

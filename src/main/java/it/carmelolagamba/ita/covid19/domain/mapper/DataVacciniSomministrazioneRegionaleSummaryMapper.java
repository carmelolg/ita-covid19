package it.carmelolagamba.ita.covid19.domain.mapper;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoRegionaleDto;

@Component
public class DataVacciniSomministrazioneRegionaleSummaryMapper {

	public TracciamentoVaccinoRegionaleDto convertEntityToDto(DataVacciniSomministrazioneRegionaleSummary entity) {

		TracciamentoVaccinoRegionaleDto bean = new TracciamentoVaccinoRegionaleDto();

		bean.setArea(entity.getArea());
		bean.setAreaDescrizione(entity.getArea_descrizione());
		bean.setFornitore(entity.getFornitore());
		bean.setDataSomministrazione(entity.getData_somministrazione());
		bean.setFasciaAnagrafica(entity.getFascia_anagrafica());
		bean.setCategoriaNonSanitari(entity.getCategoria_personale_non_sanitario());
		bean.setCategoriaOss(entity.getCategoria_operatori_sanitari_sociosanitari());
		bean.setCategoriaOver80(entity.getCategoria_over80());
		bean.setCategoriaRsa(entity.getCategoria_ospiti_rsa());
		bean.setPrimaDose(entity.getPrima_dose());
		bean.setSecondaDose(entity.getSeconda_dose());
		bean.setSessoMaschile(entity.getSesso_maschile());
		bean.setSessoFemminile(entity.getSesso_femminile());
//	bean.setUltimoAggiornamento(entity.getUltimo_aggiornamento());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());

		return bean;
	}
}

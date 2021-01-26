package it.carmelolagamba.ita.covid19.domain.mapper;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniAnagraficaSummary;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoNazionaleDto;

@Component
public class DataVacciniAnagraficaSummaryMapper {

	public TracciamentoVaccinoNazionaleDto convertEntityToDto(DataVacciniAnagraficaSummary entity) {

		TracciamentoVaccinoNazionaleDto bean = new TracciamentoVaccinoNazionaleDto();

		bean.setFasciaAnagrafica(entity.getFascia_anagrafica());
		bean.setCategoriaNonSanitari(entity.getCategoria_personale_non_sanitario());
		bean.setCategoriaOss(entity.getCategoria_operatori_sanitari_sociosanitari());
		bean.setCategoriaOver80(entity.getCategoria_over80());
		bean.setCategoriaRsa(entity.getCategoria_ospiti_rsa());
		bean.setPrimaDose(entity.getPrima_dose());
		bean.setSecondaDose(entity.getSeconda_dose());
		bean.setSessoMaschile(entity.getSesso_maschile());
		bean.setSessoFemminile(entity.getSesso_femminile());
		bean.setUltimoAggiornamento(entity.getUltimo_aggiornamento());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());

		return bean;
	}

}

package it.carmelolagamba.ita.covid19.domain.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoRegionaleDetailsDto;

@Component
public class DataVacciniSomministrazioneRegionaleSummaryMapper {

	public HashMap<String, List<TracciamentoVaccinoRegionaleDetailsDto>> group(
			List<DataVacciniSomministrazioneRegionaleSummary> allData) {

		HashMap<String, List<TracciamentoVaccinoRegionaleDetailsDto>> grouped = new HashMap<String, List<TracciamentoVaccinoRegionaleDetailsDto>>();

		for (DataVacciniSomministrazioneRegionaleSummary entity : allData) {

			String key = entity.getFornitore();
			String innerKey = entity.getFascia_anagrafica();
			List<TracciamentoVaccinoRegionaleDetailsDto> bean = grouped.get(key);
			if (bean != null) {
				Optional<TracciamentoVaccinoRegionaleDetailsDto> detailBeanOptional = bean.stream().filter(item -> item.getFasciaAnagrafica().equals(innerKey)).findAny();
				TracciamentoVaccinoRegionaleDetailsDto detailBean = new TracciamentoVaccinoRegionaleDetailsDto();
				
				if(detailBeanOptional.isPresent()) {
					detailBean = detailBeanOptional.get();
					TracciamentoVaccinoRegionaleDetailsDto detail = new TracciamentoVaccinoRegionaleDetailsDto();
					detail.setFasciaAnagrafica(innerKey);
					detail.setCategoriaNonSanitari(
							entity.getCategoria_personale_non_sanitario() + detailBean.getCategoriaNonSanitari());
					detail.setCategoriaOss(
							entity.getCategoria_operatori_sanitari_sociosanitari() + detailBean.getCategoriaOss());
					detail.setCategoriaOver80(entity.getCategoria_over80() + detailBean.getCategoriaOver80());
					detail.setCategoriaRsa(entity.getCategoria_ospiti_rsa() + detailBean.getCategoriaRsa());
					detail.setPrimaDose(entity.getPrima_dose() + detailBean.getPrimaDose());
					detail.setSecondaDose(entity.getSeconda_dose() + detailBean.getSecondaDose());
					detail.setArea(entity.getArea());
					detail.setAreaDescrizione(entity.getArea_descrizione());
					detail.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());
					detail.setTerza_dose(entity.getDose_aggiuntiva() + entity.getDose_booster());
					detail.setPregressa_infezione(entity.getPregressa_infezione());
					bean.removeIf(item -> item.getFasciaAnagrafica().equals(entity.getFascia_anagrafica()));
					bean.add(detail);
				}else {
					bean.add(convertEntityToDto(entity));
				}
			} else {
				List<TracciamentoVaccinoRegionaleDetailsDto> innerList = new ArrayList<TracciamentoVaccinoRegionaleDetailsDto>();
				innerList.add(convertEntityToDto(entity));
				grouped.put(key, innerList);
			}

		}

		return grouped;
	}

	private TracciamentoVaccinoRegionaleDetailsDto convertEntityToDto(
			DataVacciniSomministrazioneRegionaleSummary entity) {

		TracciamentoVaccinoRegionaleDetailsDto bean = new TracciamentoVaccinoRegionaleDetailsDto();

		bean.setArea(entity.getArea());
		bean.setAreaDescrizione(entity.getArea_descrizione());
		bean.setFasciaAnagrafica(entity.getFascia_anagrafica());
		bean.setCategoriaNonSanitari(entity.getCategoria_personale_non_sanitario());
		bean.setCategoriaOss(entity.getCategoria_operatori_sanitari_sociosanitari());
		bean.setCategoriaOver80(entity.getCategoria_over80());
		bean.setCategoriaRsa(entity.getCategoria_ospiti_rsa());
		bean.setPrimaDose(entity.getPrima_dose());
		bean.setSecondaDose(entity.getSeconda_dose());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());
		bean.setCategoriaForzeArmate(entity.getCategoria_forze_armate());
		bean.setCategoriaScuola(entity.getCategoria_personale_scolastico());

		return bean;
	}
}

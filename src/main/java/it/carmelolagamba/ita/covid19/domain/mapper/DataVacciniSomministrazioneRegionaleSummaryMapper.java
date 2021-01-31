package it.carmelolagamba.ita.covid19.domain.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoRegionaleDetailsDto;

@Component
public class DataVacciniSomministrazioneRegionaleSummaryMapper {

	public HashMap<String, HashMap<String, TracciamentoVaccinoRegionaleDetailsDto>> group(
			List<DataVacciniSomministrazioneRegionaleSummary> allData) {

		HashMap<String, HashMap<String, TracciamentoVaccinoRegionaleDetailsDto>> grouped = new HashMap<String, HashMap<String, TracciamentoVaccinoRegionaleDetailsDto>>();

		for (DataVacciniSomministrazioneRegionaleSummary entity : allData) {

			String key = entity.getFornitore();
			String innerKey = entity.getFascia_anagrafica();
			HashMap<String, TracciamentoVaccinoRegionaleDetailsDto> bean = grouped.get(key);
			if (bean != null) {
				TracciamentoVaccinoRegionaleDetailsDto detailBean = bean.get(innerKey);
				if (detailBean != null) {
					TracciamentoVaccinoRegionaleDetailsDto detail = new TracciamentoVaccinoRegionaleDetailsDto();
					detail.setCategoriaNonSanitari(
							entity.getCategoria_personale_non_sanitario() + detailBean.getCategoriaNonSanitari());
					detail.setCategoriaOss(
							entity.getCategoria_operatori_sanitari_sociosanitari() + detailBean.getCategoriaOss());
					detail.setCategoriaOver80(entity.getCategoria_over80() + detailBean.getCategoriaOver80());
					detail.setCategoriaRsa(entity.getCategoria_ospiti_rsa() + detailBean.getCategoriaRsa());
					detail.setPrimaDose(entity.getPrima_dose() + detailBean.getPrimaDose());
					detail.setSecondaDose(entity.getSeconda_dose() + detailBean.getSecondaDose());
					bean.put(innerKey, detail);
				} else {
					bean.put(innerKey, convertEntityToDto(entity));
				}
			} else {
				HashMap<String, TracciamentoVaccinoRegionaleDetailsDto> fasciaAnagraficaHashMap = new HashMap<String, TracciamentoVaccinoRegionaleDetailsDto>();
				fasciaAnagraficaHashMap.put(entity.getFascia_anagrafica(), convertEntityToDto(entity));
				grouped.put(key, fasciaAnagraficaHashMap);
			}

		}

		return grouped;
	}

	private TracciamentoVaccinoRegionaleDetailsDto convertEntityToDto(
			DataVacciniSomministrazioneRegionaleSummary entity) {

		TracciamentoVaccinoRegionaleDetailsDto bean = new TracciamentoVaccinoRegionaleDetailsDto();

		bean.setCategoriaNonSanitari(entity.getCategoria_personale_non_sanitario());
		bean.setCategoriaOss(entity.getCategoria_operatori_sanitari_sociosanitari());
		bean.setCategoriaOver80(entity.getCategoria_over80());
		bean.setCategoriaRsa(entity.getCategoria_ospiti_rsa());
		bean.setPrimaDose(entity.getPrima_dose());
		bean.setSecondaDose(entity.getSeconda_dose());
		bean.setUltimoAggiornamentoInterno(entity.getUltimo_aggiornamento_interno());

		return bean;
	}
}

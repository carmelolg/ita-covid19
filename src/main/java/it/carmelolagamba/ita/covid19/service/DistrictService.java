package it.carmelolagamba.ita.covid19.service;

import static it.carmelolagamba.ita.covid19.utils.MathUtils.round;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.utils.MathUtils;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.GenericStatsDto;
import it.carmelolagamba.ita.covid19.view.GrowthRateDto;
import it.carmelolagamba.ita.covid19.view.GrowthRateResultDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;

@Component
public class DistrictService {

	@Autowired
	private DataProvinciaDocumentService dataProvinciaDocumentService;

	public GrowthRateDto findGrowthRate(String region) {

		GrowthRateDto growthRateDto = new GrowthRateDto();

		// Tasso di crescita
		List<DataProvincia> dataNazioneList = dataProvinciaDocumentService.findLast30ByDistrictName(region);

		if (dataNazioneList.isEmpty()) {
			growthRateDto.setDescription("Dati non presenti");
			return growthRateDto;
		} else {
			growthRateDto.setDescription(String.format("Statistiche del tasso di crescita per la regione %s", region));

			dataNazioneList.forEach(data -> {
				// calcolo
				Double currentCase = Double.valueOf(data.getTotale_casi());
				DataProvincia yesterdayDate = dataProvinciaDocumentService.findYesterdayDataByDistrict(region,
						data.getData());
				Double yesterdayCase = Double.valueOf(yesterdayDate.getTotale_casi());

				Double rate = 0.0;
				if (yesterdayCase == 0) {
					rate = currentCase * 100;
				} else {
					rate = ((currentCase - yesterdayCase) / yesterdayCase) * 100;
				}

				growthRateDto.getResults().add(new GrowthRateResultDto(MathUtils.round(rate), data.getData()));
			});

			return growthRateDto;
		}
	}

	public GenericStatsDto findStats(String district) {

		GenericStatsDto dto = new GenericStatsDto();

		// Tasso di crescita
		DataProvincia last = dataProvinciaDocumentService.findLast(district);
		DataProvincia lastYesterday = dataProvinciaDocumentService.findYesterdayDataByDistrict(district,
				last.getData());

		if (last != null && lastYesterday != null) {

			// growth rate
			Double currentCase = Double.valueOf(last.getTotale_casi());
			Double yesterdayCase = Double.valueOf(lastYesterday.getTotale_casi());

			Double rate = ((currentCase - yesterdayCase) / yesterdayCase) * 100;
			dto.setCurrentRateOfGrowth(round(rate));

			dto.setNewPositives(last.getTotale_casi() - lastYesterday.getTotale_casi());
			dto.setCurrentTotalCases(last.getTotale_casi());
		}

		return dto;
	}

	public AndamentoDto findLast30ByDistrictName(String district) {

		AndamentoDto andamentoDto = new AndamentoDto();

		List<DataProvincia> dataProvinciaList = dataProvinciaDocumentService.findLast30ByDistrictName(district);

		if (dataProvinciaList.isEmpty()) {
			andamentoDto.setDescription("Dati non presenti");
			return andamentoDto;
		} else {
			andamentoDto
					.setDescription(String.format("Statistiche del numero di casi per la provincia di %s", district));

			dataProvinciaList.forEach(data -> {
				DataProvincia yesterdayDate = dataProvinciaDocumentService.findYesterdayDataByDistrict(district,
						data.getData());
				int increaseFromYesterday = (yesterdayDate != null)
						? data.getTotale_casi() - yesterdayDate.getTotale_casi()
						: 0;
				andamentoDto.getResults()
						.add(new ResultDto(data.getTotale_casi(), increaseFromYesterday, data.getData()));
			});

			return andamentoDto;
		}

	}

}

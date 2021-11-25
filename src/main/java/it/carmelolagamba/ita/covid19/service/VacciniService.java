package it.carmelolagamba.ita.covid19.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.ita.covid19.domain.mapper.DataVacciniAnagraficaSummaryMapper;
import it.carmelolagamba.ita.covid19.domain.mapper.DataVacciniPuntiSomministrazioneMapper;
import it.carmelolagamba.ita.covid19.domain.mapper.DataVacciniSomministrazioneRegionaleSummaryMapper;
import it.carmelolagamba.ita.covid19.domain.mapper.DataVacciniSummaryMapper;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniAnagraficaSummaryDocumentService;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniPuntiSomministrazioneDocumentService;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniSomministrazioneRegionaleSummaryDocumentService;
import it.carmelolagamba.ita.covid19.persistence.DataVacciniSummaryDocumentService;
import it.carmelolagamba.ita.covid19.view.RankingDto;
import it.carmelolagamba.ita.covid19.view.RankingItemDto;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoNazionaleDto;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoNazionaleResponseDto;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoRegionaleDetailsDto;
import it.carmelolagamba.ita.covid19.view.VaccinoPuntoSomministrazioneDto;
import it.carmelolagamba.ita.covid19.view.VaccinoTotaleRegioneDto;

@Component
public class VacciniService {

	private Logger logger = LoggerFactory.getLogger(VacciniService.class);

	@Autowired
	private DataVacciniSummaryDocumentService dataVacciniSummaryDocumentService;

	@Autowired
	private DataVacciniAnagraficaSummaryDocumentService dataVacciniAnagraficaSummaryDocumentService;

	@Autowired
	private DataVacciniAnagraficaSummaryMapper dataVacciniAnagraficaSummaryMapper;

	@Autowired
	private DataVacciniSomministrazioneRegionaleSummaryDocumentService dataVacciniSomministrazioneRegionaleSummaryDocumentService;

	@Autowired
	private DataVacciniSomministrazioneRegionaleSummaryMapper dataVacciniSomministrazioneRegionaleSummaryMapper;

	@Autowired
	private DataVacciniSummaryMapper dataVacciniSummaryMapper;

	@Autowired
	private DataVacciniPuntiSomministrazioneDocumentService dataVacciniPuntiSomministrazioneDocumentService;

	@Autowired
	private DataVacciniPuntiSomministrazioneMapper dataVacciniPuntiSomministrazioneMapper;

	public List<VaccinoPuntoSomministrazioneDto> getAllPuntiSomministrazione() {
		logger.info("Get all punti somministrazione");
		return dataVacciniPuntiSomministrazioneDocumentService.findAll().stream()
				.map(dataVacciniPuntiSomministrazioneMapper::convertEntityToDto).collect(Collectors.toList());
	}

	public List<VaccinoPuntoSomministrazioneDto> getAllPuntiSomministrazioneByRegion(Optional<String> regionName) {
		logger.info("Get all italian vaccination data by region");
		return dataVacciniPuntiSomministrazioneDocumentService.find(regionName).stream()
				.map(dataVacciniPuntiSomministrazioneMapper::convertEntityToDto).collect(Collectors.toList());
	}

	public List<VaccinoPuntoSomministrazioneDto> getAllPuntiSomministrazioneByDistrict(Optional<String> districtName) {
		logger.info("Get all italian vaccination data by district");
		return dataVacciniPuntiSomministrazioneDocumentService.findByDistrict(districtName).stream()
				.map(dataVacciniPuntiSomministrazioneMapper::convertEntityToDto).collect(Collectors.toList());
	}

	public List<VaccinoTotaleRegioneDto> getItalianDataByRegion(Optional<String> regionName) {
		logger.info("Get all italian vaccination data");
		return dataVacciniSummaryDocumentService.find(regionName).stream()
				.map(dataVacciniSummaryMapper::convertEntityToDto).collect(Collectors.toList());
	}

	public HashMap<String, List<TracciamentoVaccinoRegionaleDetailsDto>> getRegionData(
			Optional<String> regionName) {
		logger.info("Get all vaccination data by region");

		List<DataVacciniSomministrazioneRegionaleSummary> allData = dataVacciniSomministrazioneRegionaleSummaryDocumentService
				.find(regionName);

		return dataVacciniSomministrazioneRegionaleSummaryMapper.group(allData);
	}

	public TracciamentoVaccinoNazionaleResponseDto getItalianDataGroup() {

		logger.info("Get all italian vaccination data by group");

		TracciamentoVaccinoNazionaleResponseDto response = new TracciamentoVaccinoNazionaleResponseDto();
		TracciamentoVaccinoNazionaleDto total = new TracciamentoVaccinoNazionaleDto();

		List<TracciamentoVaccinoNazionaleDto> ageGroups = dataVacciniAnagraficaSummaryDocumentService.findAll().stream()
				.map(dataVacciniAnagraficaSummaryMapper::convertEntityToDto).collect(Collectors.toList());



		TracciamentoVaccinoNazionaleDto first = ageGroups.stream().findFirst().orElse(null);
		total.setUltimoAggiornamento(first.getUltimoAggiornamento());
		total.setUltimoAggiornamentoInterno(first.getUltimoAggiornamentoInterno());

		total.setCategoriaNonSanitari(ageGroups.stream().filter(item -> item.getCategoriaNonSanitari() > 0)
				.mapToInt(item -> item.getCategoriaNonSanitari()).sum());

		total.setCategoriaOss(ageGroups.stream().filter(item -> item.getCategoriaOss() > 0)
				.mapToInt(item -> item.getCategoriaOss()).sum());

		total.setCategoriaOver80(ageGroups.stream().filter(item -> item.getCategoriaOver80() > 0)
				.mapToInt(item -> item.getCategoriaOver80()).sum());

		total.setCategoriaRsa(ageGroups.stream().filter(item -> item.getCategoriaRsa() > 0)
				.mapToInt(item -> item.getCategoriaRsa()).sum());

		total.setPrimaDose(
				ageGroups.stream().filter(item -> item.getPrimaDose() > 0).mapToInt(item -> item.getPrimaDose()).sum());

		total.setSecondaDose(ageGroups.stream().filter(item -> item.getSecondaDose() > 0)
				.mapToInt(item -> item.getSecondaDose()).sum());

		total.setPregressaInfezione(ageGroups.stream().filter(item -> item.getPregressaInfezione() > 0)
				.mapToInt(item -> item.getPregressaInfezione()).sum());

		total.setSessoFemminile(ageGroups.stream().filter(item -> item.getSessoFemminile() > 0)
				.mapToInt(item -> item.getSessoFemminile()).sum());

		total.setSessoMaschile(ageGroups.stream().filter(item -> item.getSessoMaschile() > 0)
				.mapToInt(item -> item.getSessoMaschile()).sum());
		
		total.setCategoriaAltro(ageGroups.stream().filter(item -> item.getCategoriaAltro() > 0)
				.mapToInt(item -> item.getCategoriaAltro()).sum());	
		
		total.setCategoriaForzeArmate(ageGroups.stream().filter(item -> item.getCategoriaForzeArmate() > 0)
				.mapToInt(item -> item.getCategoriaForzeArmate()).sum());
		
		total.setCategoriaScuola(ageGroups.stream().filter(item -> item.getCategoriaScuola() > 0)
				.mapToInt(item -> item.getCategoriaScuola()).sum());

		response.setAll(total);
		response.setAgeGroups(ageGroups);
		return response;

	}

	public RankingDto getRankingBySomministration() {

		RankingDto ranking = new RankingDto();

		List<VaccinoTotaleRegioneDto> regions = getItalianDataByRegion(Optional.empty());
		ranking.setWorstRanked(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getPercentualeSomministrazione))
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getPercentualeSomministrazione()))
				.limit(5).collect(Collectors.toList()));

		ranking.setBestRanked(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getPercentualeSomministrazione).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getPercentualeSomministrazione()))
				.limit(5).collect(Collectors.toList()));

		ranking.setFullRanking(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getPercentualeSomministrazione).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getPercentualeSomministrazione()))
				.collect(Collectors.toList()));

		return ranking;

	}

	public RankingDto getRankingByDosiRicevute() {

		RankingDto ranking = new RankingDto();

		List<VaccinoTotaleRegioneDto> regions = getItalianDataByRegion(Optional.empty());
		ranking.setWorstRanked(
				regions.stream().sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiConsegnate))
						.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiConsegnate())).limit(5)
						.collect(Collectors.toList()));

		ranking.setBestRanked(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiConsegnate).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiConsegnate())).limit(5)
				.collect(Collectors.toList()));

		ranking.setFullRanking(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiConsegnate).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiConsegnate()))
				.collect(Collectors.toList()));

		return ranking;

	}

	public RankingDto getRankingByDosiSomministrate() {

		RankingDto ranking = new RankingDto();

		List<VaccinoTotaleRegioneDto> regions = getItalianDataByRegion(Optional.empty());
		ranking.setWorstRanked(
				regions.stream().sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiSomministrate))
						.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiSomministrate())).limit(5)
						.collect(Collectors.toList()));

		ranking.setBestRanked(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiSomministrate).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiSomministrate())).limit(5)
				.collect(Collectors.toList()));

		ranking.setFullRanking(regions.stream()
				.sorted(Comparator.comparingDouble(VaccinoTotaleRegioneDto::getDosiSomministrate).reversed())
				.map(item -> toRankingItemDto(item.getAreaDescrizione(), item.getDosiSomministrate()))
				.collect(Collectors.toList()));

		return ranking;

	}

	private RankingItemDto toRankingItemDto(String area, Object value) {
		RankingItemDto item = new RankingItemDto();
		item.setName(area);
		item.setValue(value);
		return item;

	}
}

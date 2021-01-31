package it.carmelolagamba.ita.covid19.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.VacciniService;
import it.carmelolagamba.ita.covid19.view.RankingDto;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoNazionaleResponseDto;
import it.carmelolagamba.ita.covid19.view.TracciamentoVaccinoRegionaleDetailsDto;
import it.carmelolagamba.ita.covid19.view.VaccinoPuntoSomministrazioneDto;
import it.carmelolagamba.ita.covid19.view.VaccinoTotaleRegioneDto;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "Utils Information")
public class VacciniController {

	@Autowired
	private VacciniService vacciniService;

	@ApiOperation(value = "Statistiche vaccini per regione")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/region/{name}/stats")
	public List<VaccinoTotaleRegioneDto> regions(@PathVariable("name") String name) {
		return vacciniService.getItalianDataByRegion(Optional.of(name));
	}
	
	@ApiOperation(value = "Dettaglio vaccini per regione")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/region/{name}")
	public HashMap<String, List<TracciamentoVaccinoRegionaleDetailsDto>> regionsByCategory(@PathVariable("name") String name) {
		return vacciniService.getRegionData(Optional.of(name));
	}
	
	@ApiOperation(value = "Punti di somministrazione per area o tutte le aree")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/region/{name}/somministration_point")
	public List<VaccinoPuntoSomministrazioneDto> somministrationPointByRegion(@PathVariable("name") String name) {
		return vacciniService.getAllPuntiSomministrazioneByRegion(Optional.of(name));
	}
	
	@ApiOperation(value = "Punti di somministrazione per area o tutte le aree")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/district/{name}/somministration_point")
	public List<VaccinoPuntoSomministrazioneDto> somministrationPointByDistrict(@PathVariable("name") String name) {
		return vacciniService.getAllPuntiSomministrazioneByDistrict(Optional.of(name));
	}

	@ApiOperation(value = "Vaccini in Italia")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/italy")
	public TracciamentoVaccinoNazionaleResponseDto italianStats() {
		return vacciniService.getItalianDataGroup();
	}
	
	@ApiOperation(value = "Punti di somministrazione italiani")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/italy/somministration_point")
	public List<VaccinoPuntoSomministrazioneDto> somministrationPoint() {
		return vacciniService.getAllPuntiSomministrazione();
	}

	@ApiOperation(value = "Vaccini in Italia Ranking: per percentuale di somministrazione")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/ranking/somministration")
	public RankingDto rankingSomministration() {
		return vacciniService.getRankingBySomministration();
	}
	
	@ApiOperation(value = "Vaccini in Italia Ranking: per consegne effettuate")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/ranking/delivered")
	public RankingDto rankingDelivered() {
		return vacciniService.getRankingByDosiRicevute();
	}
	
	@ApiOperation(value = "Vaccini in Italia Ranking: per dosi somministrate")
	@RequestMapping(method = RequestMethod.GET, path = "/vaccine/ranking/performed")
	public RankingDto rankingPerformed() {
		return vacciniService.getRankingByDosiSomministrate();
	}

}

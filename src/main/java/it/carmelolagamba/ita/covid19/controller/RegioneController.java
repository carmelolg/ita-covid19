package it.carmelolagamba.ita.covid19.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.RegionService;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.GenericStatsDto;
import it.carmelolagamba.ita.covid19.view.GrowthRateDto;
import it.carmelolagamba.ita.covid19.view.ResumeStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Data Stats")
public class RegioneController {

    @Autowired
    private RegionService regionService;

    @ApiOperation(value = "Statistiche growth rate regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/growthRate")
    public GrowthRateDto growthRateNazionali(@PathVariable("name") String name) {
        return regionService.findGrowthRate(name);
    }

    @ApiOperation(value = "Statistiche generali regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/stats")
    public GenericStatsDto statsRegionali(@PathVariable("name") String name) {
        return regionService.findStats(name);
    }

    @ApiOperation(value = "Totale casi per regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total")
    public AndamentoDto statsPerProvincia(@PathVariable("name") String name) {
        return regionService.findLast30ByDistrictName(name);
    }

    @ApiOperation(value = "Riepilogo")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/resume")
    public ResumeStatsDto riepilogoNazionali(@PathVariable("name") String name, @RequestParam("all") boolean all) {
        return regionService.findResume(name, all);
    }

    @ApiOperation(value = "Totale nuovi casi regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/new")
    public AndamentoDto totaleNuoviCasiNazionali(@PathVariable("name") String name) {
        return regionService.findLast30NewPositiveByRegion(name);
    }


    @ApiOperation(value = "Totale ricoverati regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/hospitalized")
    public AndamentoDto totaleNazionaliRicoverati(@PathVariable("name") String name) {
        return regionService.findLast30HospitalizedByDistrictName(name);
    }

    @ApiOperation(value = "Totale terapia intensiva regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/intensive-care")
    public AndamentoDto totaleNazionaliTerapiaIntensiva(@PathVariable("name") String name) {
        return regionService.findLast30IntensiveCureByDistrictName(name);
    }

    @ApiOperation(value = "Totale morti regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/dead")
    public AndamentoDto totaleNazionaliMorti(@PathVariable("name") String name) {
        return regionService.findLast30DeadByDistrictName(name);
    }


    @ApiOperation(value = "Totale tamponi regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/test")
    public AndamentoDto totaleNazionaliTamponi(@PathVariable("name") String name) {
        return regionService.findLast30TestByDistrictName(name);
    }

    @ApiOperation(value = "Totale guariti regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/recovered")
    public AndamentoDto totaleNazionaliGuariti(@PathVariable("name") String name) {
        return regionService.findLast30RecoveredByDistrictName(name);
    }

}

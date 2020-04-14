package it.carmelolagamba.ita.covid19.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.ita.covid19.service.RegionService;
import it.carmelolagamba.ita.covid19.service.UtilsService;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Data Stats")
public class RegioneController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private UtilsService utilsService;

    @ApiOperation(value = "Last file imported")
    @RequestMapping(method = RequestMethod.GET, path = "/region/file/last")
    public FileImportedDto lastFile() {
        return utilsService.getLastFileImportedByType(Constants.regioneText);
    }

    @ApiOperation(value = "Statistiche growth rate regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/growthRate")
    public GrowthRateDto growthRateNazionali(@PathVariable("name") String name) {
        return regionService.findGrowthRate(Optional.of(name));
    }

    @ApiOperation(value = "Statistiche generali regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/stats")
    public GenericStatsDto statsRegionali(@PathVariable("name") String name) {
        return regionService.findStats(Optional.of(name));
    }

    @ApiOperation(value = "Totale casi per regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total")
    public AndamentoDto statsPerProvincia(@PathVariable("name") String name) {
        return regionService.findLast30TotalCases(Optional.of(name));
    }

    @ApiOperation(value = "Riepilogo")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/resume")
    public ResumeStatsDto riepilogoNazionali(@PathVariable("name") String name, @RequestParam("all") boolean all) {
        return regionService.findResume(all, Optional.of(name));
    }

    @ApiOperation(value = "Totale nuovi casi regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/new")
    public AndamentoDto totaleNuoviCasiNazionali(@PathVariable("name") String name) {
        return regionService.findLast30NewPositive(Optional.of(name));
    }

    @ApiOperation(value = "Totale positivi regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/positive")
    public AndamentoDto totalePositiviRegionali(@PathVariable("name") String name) {
        return regionService.findLast30TotalPositive(Optional.of(name));
    }

    @ApiOperation(value = "Totale isolati a casa regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/isolated")
    public AndamentoDto totaleIsolatiRegionali(@PathVariable("name") String name) {
        return regionService.findLast30Isolated(Optional.of(name));
    }

    @ApiOperation(value = "Totale ricoverati regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/hospitalized")
    public AndamentoDto totaleNazionaliRicoverati(@PathVariable("name") String name) {
        return regionService.findLast30Hospitalized(Optional.of(name));
    }

    @ApiOperation(value = "Totale terapia intensiva regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/intensive-care")
    public AndamentoDto totaleNazionaliTerapiaIntensiva(@PathVariable("name") String name) {
        return regionService.findLast30IntensiveCure(Optional.of(name));
    }

    @ApiOperation(value = "Totale morti regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/dead")
    public AndamentoDto totaleNazionaliMorti(@PathVariable("name") String name) {
        return regionService.findLast30Dead(Optional.of(name));
    }


    @ApiOperation(value = "Totale tamponi regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/test")
    public AndamentoDto totaleNazionaliTamponi(@PathVariable("name") String name) {
        return regionService.findLast30Test(Optional.of(name));
    }

    @ApiOperation(value = "Totale guariti regionali")
    @RequestMapping(method = RequestMethod.GET, path = "/region/{name}/total/recovered")
    public AndamentoDto totaleNazionaliGuariti(@PathVariable("name") String name) {
        return regionService.findLast30Recovered(Optional.of(name));
    }

}

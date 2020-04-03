package it.carmelolagamba.ita.covid19.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.DistrictService;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.GenericStatsDto;
import it.carmelolagamba.ita.covid19.view.GrowthRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Data Stats")
public class ProvinciaController {

    @Autowired
    private DistrictService districtService;

    @ApiOperation(value = "Statistiche generali provinciali")
    @RequestMapping(method = RequestMethod.GET, path = "/district/{name}/stats")
    public GenericStatsDto statsProvinciali(@PathVariable("name") String name) {
        return districtService.findStats(name);
    }

    @ApiOperation(value = "Totale casi per provincia")
    @RequestMapping(method = RequestMethod.GET, path = "/district/{name}/total")
    public AndamentoDto statsPerProvincia(@PathVariable("name") String name) {
        return districtService.findLast30ByDistrictName(name);
    }

    @ApiOperation(value = "Statistiche growth rate provinciali")
    @RequestMapping(method = RequestMethod.GET, path = "/district/{name}/growthRate")
    public GrowthRateDto growthRateNazionali(@PathVariable("name") String name) {
        return districtService.findGrowthRate(name);
    }
}

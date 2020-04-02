package it.carmelolagamba.ita.covid19.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.NationalService;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Data Stats")
public class NazioneController {

    @Autowired
    private NationalService nationalService;

//    @ApiOperation(value = "Statistiche generali nazionali")
//    @RequestMapping(method = RequestMethod.GET, path = "/italy/total")
//    public AndamentoDto statsNazionali() {
//        return nationalService.findLast30TotalCases();
//    }

    @ApiOperation(value = "Totale casi nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total")
    public AndamentoDto totaleNazionali() {
        return nationalService.findLast30TotalCases();
    }

    @ApiOperation(value = "Totale ricoverati nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total/hospitalized")
    public AndamentoDto totaleNazionaliRicoverati() {
        return nationalService.findLast30Hospitalized();
    }

    @ApiOperation(value = "Totale terapia intensiva nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total/intensive-care")
    public AndamentoDto totaleNazionaliTerapiaIntensiva() {
        return nationalService.findLast30IntensiveCure();
    }

    @ApiOperation(value = "Totale morti nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total/dead")
    public AndamentoDto totaleNazionaliMorti() {
        return nationalService.findLast30Dead();
    }

    @ApiOperation(value = "Totale tamponi nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total/test")
    public AndamentoDto totaleNazionaliTamponi() {
        return nationalService.findLast30Test();
    }

    @ApiOperation(value = "Totale guariti nazionali")
    @RequestMapping(method = RequestMethod.GET, path = "/italy/total/recovered")
    public AndamentoDto totaleNazionaliGuariti() {
        return nationalService.findLast30Recovered();
    }
}

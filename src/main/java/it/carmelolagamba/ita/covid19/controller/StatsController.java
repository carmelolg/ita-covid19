package it.carmelolagamba.ita.covid19.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.StatsService;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

@RestController
@Api(value = "Data Stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @ApiOperation(value = "Totale casi per provincia")
    @RequestMapping(method = RequestMethod.GET, path = "/provincia/{name}/stats")
    public AndamentoDto statsPerProvincia(@PathVariable("name") String name) {
        return statsService.findStatsByDistrictName(name);
    }
}

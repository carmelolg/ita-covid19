package it.carmelolagamba.ita.covid19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.UtilsService;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "Utils Information")
public class UtilsController {

	@Autowired
	private UtilsService utilsService;

	@ApiOperation(value = "Regioni")
	@RequestMapping(method = RequestMethod.GET, path = "/regions")
	public List<String> regions() {
		return utilsService.allRegions();
	}


	@ApiOperation(value = "Province")
	@RequestMapping(method = RequestMethod.GET, path = "/districts")
	public List<String> districts() {
		return utilsService.allDistricts();
	}

}

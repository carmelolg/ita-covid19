package it.carmelolagamba.ita.covid19.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.config.ApplicationProperties;
import it.carmelolagamba.ita.covid19.service.ProvinciaMigrationService;
import it.carmelolagamba.ita.covid19.service.RegioneMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Migration of data")
public class MigrationController {

	@Autowired
	private ApplicationProperties config;
	
	@Autowired
	private RegioneMigrationService regioneMigrationService;

	@Autowired
	private ProvinciaMigrationService provinciaMigrationService;

	@ApiOperation(value = "Migrate region")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/region")
	public String region() {
		regioneMigrationService.migrateData();
		return "Dati delle regioni importati";

	}

	@ApiOperation(value = "Migrate district")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/district")
	public String district() {
		provinciaMigrationService.migrateData();
		return "Dati delle province importati";

	}

}

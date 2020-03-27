package it.carmelolagamba.ita.covid19.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.config.ApplicationProperties;
import it.carmelolagamba.ita.covid19.dto.system.InfoDto;
import it.carmelolagamba.ita.covid19.service.MigrationService;
import it.carmelolagamba.mongo.service.MongoService;
import it.carmelolagamba.mongo.utils.MongoStatusConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
@Api(value = "Migration of data")
public class MigrationController {

	@Autowired
	private ApplicationProperties config;
	
	@Autowired
	private MigrationService migrationService;

	@ApiOperation(value = "Migrate region")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/region")
	public String region() {
		migrationService.migrateRegionData();
		return "Imported";

	}

	@ApiOperation(value = "Migrate district")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/district")
	public String district() {
		migrationService.migrateDistrictData();
		return "Imported";

	}

}

package it.carmelolagamba.ita.covid19.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.config.ApplicationProperties;
import it.carmelolagamba.ita.covid19.service.migration.MigrateAllService;
import it.carmelolagamba.ita.covid19.service.migration.NazioneMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.ProvinciaMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.RegioneMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "Migration of data")
public class MigrationController {

	@Autowired
	private ApplicationProperties config;
	
	@Autowired
	private RegioneMigrationService regioneMigrationService;

	@Autowired
	private ProvinciaMigrationService provinciaMigrationService;

	@Autowired
	private NazioneMigrationService nazioneMigrationService;

	@Autowired
	private MigrateAllService migrateAllService;

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

	@ApiOperation(value = "Migrate country")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/country")
	public String country() {
		nazioneMigrationService.migrateData();
		return "Dati nazionali importati";

	}


	@ApiOperation(value = "Migrate all")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/all")
	public String all() {
		try {
			migrateAllService.dowloadAllFilesManually();
			return "Tutti i dati sono stati importati.";
		} catch (Exception e) {
			return "Errore nella migrazione dei files [guarda i logs per maggiori informazioni]";
		}
	}

}

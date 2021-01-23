package it.carmelolagamba.ita.covid19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.carmelolagamba.ita.covid19.service.migration.VacciniAnagraficaSummaryMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.MigrateAllService;
import it.carmelolagamba.ita.covid19.service.migration.NazioneMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.ProvinciaMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.RegioneMigrationService;
import it.carmelolagamba.ita.covid19.service.migration.VacciniSummaryMigrationService;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "Migration of data")
public class MigrationController {
	
	@Autowired
	private RegioneMigrationService regioneMigrationService;

	@Autowired
	private ProvinciaMigrationService provinciaMigrationService;

	@Autowired
	private NazioneMigrationService nazioneMigrationService;

	@Autowired
	private MigrateAllService migrateAllService;
	
	@Autowired
	private VacciniSummaryMigrationService vacciniSummryMigrationService;
	
	@Autowired
	private VacciniAnagraficaSummaryMigrationService anagraficaVacciniSummryMigrationService;

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

	@ApiOperation(value = "Migrate vaccini summary")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/vaccini/summary")
	public String vacciniSummary() throws Exception {
		vacciniSummryMigrationService.migrateData();
		return "Dati sommari dei vaccini importati";

	}
	
	@ApiOperation(value = "Migrate vaccini summary")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/vaccini/anagrafica/summary")
	public String anagraficaVacciniSummary() throws Exception {
		anagraficaVacciniSummryMigrationService.migrateData();
		return "Dati sommari delle anagrafiche vaccinate importati";

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

	@ApiOperation(value = "Reset Migrate all")
	@RequestMapping(method = RequestMethod.GET, path = "/migrate/reset")
	public String reset() {
		try {
			migrateAllService.resetDataMigration();
			return "Tutti i dati sono stati importati.";
		} catch (Exception e) {
			return "Errore nella migrazione dei files [guarda i logs per maggiori informazioni]";
		}
	}

}

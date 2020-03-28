package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MigrateAllService {

    private Logger logger = LoggerFactory.getLogger(MigrateAllService.class);

    @Autowired
    private NazioneMigrationService nazioneMigrationService;

    @Autowired
    private ProvinciaMigrationService provinciaMigrationService;

    @Autowired
    private RegioneMigrationService regioneMigrationService;

    public void dowloadAllFilesManually() throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(date);

            String fileRegioniURL = String.join("", Constants.baseUrlRegioni, dateString, Constants.defaultExtension);
            String saveRegioniDir = Constants.folderRegioni;
            FileUtils.downloadFile(fileRegioniURL, saveRegioniDir);

            String fileNazioniURL = String.join("", Constants.baseUrlNazione, dateString, Constants.defaultExtension);
            String saveNazioniDir = Constants.folderNazioni;
            FileUtils.downloadFile(fileNazioniURL, saveNazioniDir);

            String fileProvinciaURL = String.join("", Constants.baseUrlProvincia, dateString, Constants.defaultExtension);
            String saveProvinciaDir = Constants.folderProvincia;
            FileUtils.downloadFile(fileProvinciaURL, saveProvinciaDir);

            logger.info("Tutti i dati sono stati scaricati.");

            nazioneMigrationService.migrateData();
            regioneMigrationService.migrateData();
            provinciaMigrationService.migrateData();

            logger.info("Tutti i dati sono stati migrati su DB.");

        } catch (IOException ex) {
            logger.error("Download files andato in errore", ex);
        }
    }
}

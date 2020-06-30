package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import it.carmelolagamba.mongo.service.custom.FileImportedDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MigrateAllService {

    private Logger logger = LoggerFactory.getLogger(MigrateAllService.class);

    @Autowired
    private NazioneMigrationService nazioneMigrationService;

    @Autowired
    private ProvinciaMigrationService provinciaMigrationService;

    @Autowired
    private RegioneMigrationService regioneMigrationService;

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    @Autowired
    private DataProvinciaDocumentService dataProvinciaDocumentService;

    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;

    @Autowired
    private FileImportedDocumentService fileImportedDocumentService;

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


    public void resetDataMigration() throws Exception {
        try {

            logger.info("Inizio a svuotare le cartelle");

            File folderNazioni = new File(Constants.folderNazioni);
            if (! folderNazioni.exists()){
                folderNazioni.mkdirs();
            }
            org.apache.commons.io.FileUtils.cleanDirectory(new File(Constants.folderNazioni));

            File folderRegioni = new File(Constants.folderRegioni);
            if (! folderRegioni.exists()){
                folderRegioni.mkdirs();
            }
            org.apache.commons.io.FileUtils.cleanDirectory(new File(Constants.folderRegioni));

            File folderProvince = new File(Constants.folderProvincia);
            if (! folderProvince.exists()){
                folderProvince.mkdirs();
            }
            org.apache.commons.io.FileUtils.cleanDirectory(new File(Constants.folderProvincia));

            logger.info("Cartelle svuotate");


            logger.info("Svuoto le collection");

            dataNazioneDocumentService.removeAll();
            dataProvinciaDocumentService.removeAll();
            dataRegioneDocumentService.removeAll();
            fileImportedDocumentService.removeAll();

            logger.info("Collection svuotate");

            String s = "2020-02-24";
            LocalDate start = LocalDate.parse(s);
            LocalDate end = LocalDate.now();
            List<LocalDate> totalDates = new ArrayList<>();
            while (!start.isAfter(end)) {
                totalDates.add(start);
                start = start.plusDays(1);
            }

            logger.info("Inizio a scaricare tutti i file da Github");
            for (LocalDate date : totalDates) {
                // SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String dateString = formatter.format(date);

                String fileRegioniURL = String.join("", Constants.baseUrlRegioni, dateString, Constants.defaultExtension);
                String saveRegioniDir = Constants.folderRegioni;
                FileUtils.downloadFile(fileRegioniURL, saveRegioniDir);

                String fileNazioniURL = String.join("", Constants.baseUrlNazione, dateString, Constants.defaultExtension);
                String saveNazioniDir = Constants.folderNazioni;
                FileUtils.downloadFile(fileNazioniURL, saveNazioniDir);

                String fileProvinciaURL = String.join("", Constants.baseUrlProvincia, dateString, Constants.defaultExtension);
                String saveProvinciaDir = Constants.folderProvincia;
                FileUtils.downloadFile(fileProvinciaURL, saveProvinciaDir);
            }

            logger.info("Tutti i dati sono stati scaricati");

            nazioneMigrationService.migrateData();
            regioneMigrationService.migrateData();
            provinciaMigrationService.migrateData();

            logger.info("Tutti i dati sono stati migrati su DB");

        } catch (IOException ex) {
            logger.error("Download files andato in errore", ex);
        }
    }
}

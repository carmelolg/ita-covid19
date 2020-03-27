package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataProvincia;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataRegione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import it.carmelolagamba.mongo.service.custom.FileImportedDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class MigrationService {

    private static Logger logger = LoggerFactory.getLogger(MigrationService.class);

    @Autowired
    private FileImportedDocumentService fileImportedDocumentService;

    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;

    @Autowired
    private DataProvinciaDocumentService dataProvinciaDocumentService;

    @Autowired
    private CSVDataRegione csvDataRegione;

    @Autowired
    private CSVDataProvincia csvDataProvincia;

    @Autowired
    private FileUtils fileUtils;

    public void migrateRegionData() {
        File folder = new File("./data/dati-regioni");
        List<File> fileList = fileUtils.listAllFiles(folder);

        fileList.forEach(file -> {

            if (checkAlreadyImported(file.getName())) {
                logger.info("{}: already imported.", file.getName());
            } else {
                List<DataRegione> dataRegioneList;
                try {
                    dataRegioneList = csvDataRegione.convertRegionDataByFilename(file.getAbsolutePath());
                    dataRegioneDocumentService.insertAll(dataRegioneList);
                    fileImportedDocumentService.insert(new FileImported(file.getName()));
                } catch (Exception e) {
                    logger.error("Errore nella conversione del file {} in oggetto", file.getName());
                    logger.error("Ecco lo stacktrace", e);
                }
            }

        });
    }


    public void migrateDistrictData() {
        File folder = new File("./data/dati-province");
        List<File> fileList = fileUtils.listAllFiles(folder);

        fileList.forEach(file -> {

            if (checkAlreadyImported(file.getName())) {
                logger.info("{}: already imported.", file.getName());
            } else {
                List<DataProvincia> dataProvinciaList;
                try {
                    dataProvinciaList = csvDataProvincia.convertProvinciaDataByFilename(file.getAbsolutePath());
                    dataProvinciaDocumentService.insertAll(dataProvinciaList);
                    fileImportedDocumentService.insert(new FileImported(file.getName()));
                } catch (Exception e) {
                    logger.error("Errore nella conversione del file {} in oggetto", file.getName());
                    logger.error("Ecco lo stacktrace", e);
                }
            }

        });
    }

    public void migrateNationalData() {

    }

    private boolean checkAlreadyImported(String name) {
        return fileImportedDocumentService.isAlreadyMigrated(name);
    }
}

package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataRegione;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class RegioneMigrationService extends AbstractMigrationService {

    private static Logger logger = LoggerFactory.getLogger(RegioneMigrationService.class);

    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;


    @Autowired
    private CSVDataRegione csvDataRegione;


    @Override
    protected void migrationInvoke(File file) throws Exception {
        List<DataRegione> dataRegioneList = csvDataRegione.convertToDataFromFilename(file.getAbsolutePath());
        dataRegioneDocumentService.insertAll(dataRegioneList);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected File getFolderPath() {
        return new File("./data/dati-regioni");
    }

}

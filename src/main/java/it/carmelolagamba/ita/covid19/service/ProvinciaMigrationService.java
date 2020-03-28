package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataProvincia;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ProvinciaMigrationService extends AbstractMigrationService {

    private static Logger logger = LoggerFactory.getLogger(ProvinciaMigrationService.class);

    @Autowired
    private DataProvinciaDocumentService dataProvinciaDocumentService;
    @Autowired
    private CSVDataProvincia csvDataProvincia;

    @Override
    protected void migrationInvoke(File file) throws Exception {
        List<DataProvincia> dataProvinciaList = csvDataProvincia.convertToDataFromFilename(file.getAbsolutePath());
        dataProvinciaDocumentService.insertAll(dataProvinciaList);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected File getFolderPath() {
        return new File("./data/dati-province");
    }

}

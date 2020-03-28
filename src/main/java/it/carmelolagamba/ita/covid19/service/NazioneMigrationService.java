package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataNazione;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class NazioneMigrationService extends AbstractMigrationService {

    private static Logger logger = LoggerFactory.getLogger(NazioneMigrationService.class);

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    @Autowired
    private CSVDataNazione csvDataNazione;

    public NazioneMigrationService() {
    }

    @Override
    protected void migrationInvoke(File file) throws Exception {
        List<DataNazione> dataNazioneList = csvDataNazione.convertToDataFromFilename(file.getAbsolutePath());
        dataNazioneDocumentService.insertAll(dataNazioneList);
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected File getFolderPath() {
        return new File("./data/dati-andamento-nazionale");
    }

}

package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataNazione;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class NazioneMigrationService extends AbstractMigrationService {

    private static Logger logger = LoggerFactory.getLogger(NazioneMigrationService.class);

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    @Autowired
    private CSVDataNazione csvDataNazione;

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
        return new File(Constants.folderNazioni);
    }

    @Scheduled(cron = "0 30 17 * * ?")
    public void getFile() throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(date);

            String fileURL = String.join("", Constants.baseUrlNazione, dateString, Constants.defaultExtension);
            String saveDir = Constants.folderNazioni;
            FileUtils.downloadFile(fileURL, saveDir);
            logger.info("Dati nazionali aggiornati correttamente alle 18h30");

            migrateData();
            logger.info("Dati nazionali migrati correttamente alle 18h30");

        } catch (IOException ex) {
            logger.error("Scheduling per scaricare i dati nazionali giornalieri andato in errore", ex);
        }
    }

}

package it.carmelolagamba.ita.covid19.service.migration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.persistence.DataNazioneDocumentService;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataNazione;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

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

    @Scheduled(cron = "0 0/5 16-19 * * *")
    public void getFile() throws Exception {

        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(date);

            String fileURL = String.join("", Constants.baseUrlNazione, dateString, Constants.defaultExtension);
            String saveDir = Constants.folderNazioni;
            boolean saved = FileUtils.downloadFile(fileURL, saveDir);
            if(saved){
                logger.info("File importato: {}", fileURL);
                migrateData();
                logger.info("File migrato: {}", fileURL);
            }else{
                logger.info("File: {} non ancora disponibile", fileURL);
            }

        } catch (IOException ex) {
            logger.error("Scheduling per scaricare i dati nazionali giornalieri andato in errore", ex);
        }
    }

}

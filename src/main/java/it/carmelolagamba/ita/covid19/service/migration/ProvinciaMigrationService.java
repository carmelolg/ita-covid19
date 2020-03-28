package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataProvincia;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
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
        return new File(Constants.folderProvincia);
    }

    @Scheduled(cron = "30 32 17 * * ?")
    public void getFile() throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(date);

            String fileURL = String.join("", Constants.baseUrlProvincia, dateString, Constants.defaultExtension);
            String saveDir = Constants.folderProvincia;
            FileUtils.downloadFile(fileURL, saveDir);
            logger.info("Dati delle province aggiornati correttamente alle 18h32:30.");

            migrateData();
            logger.info("Dati delle province migrati correttamente alle 18h32:30.");

        } catch (IOException ex) {
            logger.error("Scheduling per scaricare i dati delle province giornalieri andato in errore", ex);
        }
    }

}

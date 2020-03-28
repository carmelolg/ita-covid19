package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataRegione;
import it.carmelolagamba.ita.covid19.utils.Constants;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
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
        return new File(Constants.folderRegioni);
    }

    @Scheduled(cron = "0 35 17 * * ?")
    public void getFile() throws Exception {
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateString = format.format(date);

            String fileURL = String.join("", Constants.baseUrlRegioni, dateString, Constants.defaultExtension);
            String saveDir = Constants.folderRegioni;
            FileUtils.downloadFile(fileURL, saveDir);
            logger.info("Dati delle regioni aggiornati correttamente alle 18h35");

            migrateData();
            logger.info("Dati delle regioni migrati correttamente alle 18h35");

        } catch (IOException ex) {
            logger.error("Scheduling per scaricare i dati delle regioni giornalieri andato in errore", ex);
        }
    }
}

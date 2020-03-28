package it.carmelolagamba.ita.covid19.service.migration;

import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataProvincia;
import it.carmelolagamba.ita.covid19.service.csv.CSVDataRegione;
import it.carmelolagamba.ita.covid19.utils.FileUtils;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import it.carmelolagamba.mongo.service.custom.FileImportedDocumentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

public abstract class AbstractMigrationService {


    @Autowired
    private FileImportedDocumentService fileImportedDocumentService;

    @Autowired
    private FileUtils fileUtils;

    public void migrateData() {
        File folder = getFolderPath();
        List<File> fileList = fileUtils.listAllFiles(folder);

        fileList.forEach(file -> {

            if (checkAlreadyImported(file.getName())) {
                getLogger().info("{}: already imported.", file.getName());
            } else {
                try {
                    migrationInvoke(file);
                    fileImportedDocumentService.insert(new FileImported(file.getName()));
                } catch (Exception e) {
                    getLogger().error("Errore nella conversione del file {} in oggetto", file.getName());
                    getLogger().error("Ecco lo stacktrace", e);
                }
            }

        });
    }

    protected abstract void migrationInvoke(File file) throws Exception;

    protected abstract Logger getLogger();

    protected abstract File getFolderPath();

    private boolean checkAlreadyImported(String name) {
        return fileImportedDocumentService.isAlreadyMigrated(name);
    }
}
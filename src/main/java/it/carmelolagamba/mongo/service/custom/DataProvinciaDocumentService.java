package it.carmelolagamba.mongo.service.custom;

import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class DataProvinciaDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataProvincia";

    private Logger logger = LoggerFactory.getLogger(DataProvinciaDocumentService.class);

    @Autowired
    private DataProvinciaCollectionService dataProvinciaCollectionService;

    public boolean insertAll(List<DataProvincia> dataProvinciaList) {
        try {
            MongoCollection<DataProvincia> collection = dataProvinciaCollectionService.getCollection(COLLECTION_NAME);
            return asyncInsertMany(dataProvinciaCollectionService.getAsyncCollection(COLLECTION_NAME), dataProvinciaList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

}

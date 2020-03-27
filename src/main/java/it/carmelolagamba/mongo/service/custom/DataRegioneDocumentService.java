package it.carmelolagamba.mongo.service.custom;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class DataRegioneDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataRegione";

    private Logger logger = LoggerFactory.getLogger(DataRegioneDocumentService.class);

    @Autowired
    private DataRegioneCollectionService dataRegioneCollectionService;

    public boolean insertAll(List<DataRegione> dataRegioneList) {
        try {
            MongoCollection<DataRegione> collection = dataRegioneCollectionService.getCollection(COLLECTION_NAME);
            return asyncInsertMany(dataRegioneCollectionService.getAsyncCollection(COLLECTION_NAME), dataRegioneList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

}

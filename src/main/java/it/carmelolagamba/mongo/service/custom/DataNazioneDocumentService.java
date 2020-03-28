package it.carmelolagamba.mongo.service.custom;

import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class DataNazioneDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataNazione";

    private Logger logger = LoggerFactory.getLogger(DataNazioneDocumentService.class);

    @Autowired
    private DataNazioneCollectionService dataNazioneCollectionService;

    public boolean insertAll(List<DataNazione> dataNazioneList) {
        try {
            MongoCollection<DataNazione> collection = dataNazioneCollectionService.getCollection(COLLECTION_NAME);
            return asyncInsertMany(dataNazioneCollectionService.getAsyncCollection(COLLECTION_NAME), dataNazioneList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

}

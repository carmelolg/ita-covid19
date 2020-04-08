package it.carmelolagamba.mongo.service.custom;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.Regione;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegioneDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "Regione";

    private Logger logger = LoggerFactory.getLogger(RegioneDocumentService.class);

    @Autowired
    private RegioneCollectionService regioneCollectionService;

    public List<Regione> findAll() {
        MongoCollection<Regione> collection = regioneCollectionService.getCollection(COLLECTION_NAME);
        return findByFilters(collection, new BasicDBObject());

    }

}

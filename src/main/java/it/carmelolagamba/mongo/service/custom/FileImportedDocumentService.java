package it.carmelolagamba.mongo.service.custom;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class FileImportedDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "FileImported";

    private Logger logger = LoggerFactory.getLogger(FileImportedDocumentService.class);

    @Autowired
    private FileImportedCollectionService fileImportedCollectionService;

    public boolean isAlreadyMigrated(String filename) {

        MongoCollection<FileImported> collection = fileImportedCollectionService.getCollection(COLLECTION_NAME);

        FileImported file = findOne(collection, new BasicDBObject("filename", filename));

        return (file != null) ? true : false;

    }

    public FileImported insert(FileImported fileImported) {
        MongoCollection<FileImported> collection = fileImportedCollectionService.getCollection(COLLECTION_NAME);
        return insert(collection, fileImported);
    }

}

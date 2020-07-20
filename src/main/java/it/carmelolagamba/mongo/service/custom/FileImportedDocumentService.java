package it.carmelolagamba.mongo.service.custom;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

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

    public void removeAll(){
        removeByFilters(COLLECTION_NAME, new BasicDBObject());
    }

    public FileImported findLastByType(String type) {
        MongoCollection<FileImported> collection = fileImportedCollectionService.getCollection(COLLECTION_NAME);
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("filename", new BasicDBObject("$regex", ".*" + type +  "*."));

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("_id", -1);

        FileImported file = findOne(collection, filters, sortFilters);

        return file;
    }

    public FileImported insert(FileImported fileImported) {
        MongoCollection<FileImported> collection = fileImportedCollectionService.getCollection(COLLECTION_NAME);
        return insert(collection, fileImported);
    }

}

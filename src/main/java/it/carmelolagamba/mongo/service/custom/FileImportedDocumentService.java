package it.carmelolagamba.mongo.service.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.FileImportedMongo;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class FileImportedDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "FileImported";

	private Logger logger = LoggerFactory.getLogger(FileImportedDocumentService.class);

	@Autowired
	private FileImportedCollectionService fileImportedCollectionService;

	public boolean isAlreadyMigrated(String filename) {

		MongoCollection<FileImportedMongo> collection = fileImportedCollectionService.getCollection(COLLECTION_NAME);

		FileImportedMongo file = findOne(collection, new BasicDBObject("filename", filename));

		return (file != null) ? true : false;
	}

}

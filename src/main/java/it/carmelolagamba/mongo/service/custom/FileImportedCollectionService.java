package it.carmelolagamba.mongo.service.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.ita.covid19.domain.FileImportedMongo;
import it.carmelolagamba.mongo.service.DatabaseService;


@Component
public class FileImportedCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<FileImportedMongo> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, FileImportedMongo.class);
	}

	public MongoCollection<FileImportedMongo> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, FileImportedMongo.class);
	}
}

package it.carmelolagamba.mongo.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;


@Component
public class CollectionService {

	@Autowired
	private DatabaseService dbService;
	
	public void createCollection(String nameCollection){
		MongoDatabase db = dbService.getDefaultDb();
		db.createCollection(nameCollection, null);
	}
	
	public void createCollection(String nameCollection, CreateCollectionOptions options){
		MongoDatabase db = dbService.getDefaultDb();
		db.createCollection(nameCollection, options);
	}
	
	public com.mongodb.client.MongoCollection<Document> getCollection(String nameCollection){
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection);
	}
	
	public com.mongodb.async.client.MongoCollection<Document> getAsyncCollection(String nameCollection){
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection);
	}
}

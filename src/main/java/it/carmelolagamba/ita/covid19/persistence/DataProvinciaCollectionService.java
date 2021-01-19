package it.carmelolagamba.ita.covid19.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.mongo.service.DatabaseService;


@Component
public class DataProvinciaCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<DataProvincia> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, DataProvincia.class);
	}

	public MongoCollection<DataProvincia> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, DataProvincia.class);
	}
}

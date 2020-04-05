package it.carmelolagamba.mongo.service.custom;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.carmelolagamba.ita.covid19.domain.Provincia;
import it.carmelolagamba.ita.covid19.domain.Regione;
import it.carmelolagamba.mongo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProvinciaCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<Provincia> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, Provincia.class);
	}

	public MongoCollection<Provincia> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, Provincia.class);
	}
}

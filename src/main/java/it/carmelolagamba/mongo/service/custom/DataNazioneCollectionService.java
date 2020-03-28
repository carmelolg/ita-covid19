package it.carmelolagamba.mongo.service.custom;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.mongo.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataNazioneCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<DataNazione> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, DataNazione.class);
	}

	public MongoCollection<DataNazione> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, DataNazione.class);
	}
}

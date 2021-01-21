package it.carmelolagamba.ita.covid19.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.ita.covid19.domain.DataVacciniAnagraficaSummary;
import it.carmelolagamba.mongo.service.DatabaseService;


@Component
public class DataVacciniAnagraficaSummaryCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<DataVacciniAnagraficaSummary> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, DataVacciniAnagraficaSummary.class);
	}

	public MongoCollection<DataVacciniAnagraficaSummary> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, DataVacciniAnagraficaSummary.class);
	}
}

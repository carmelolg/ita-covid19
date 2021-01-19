package it.carmelolagamba.ita.covid19.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.ita.covid19.domain.DataAnagraficaVacciniSummary;
import it.carmelolagamba.mongo.service.DatabaseService;


@Component
public class DataAnagraficaVacciniSummaryCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<DataAnagraficaVacciniSummary> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, DataAnagraficaVacciniSummary.class);
	}

	public MongoCollection<DataAnagraficaVacciniSummary> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, DataAnagraficaVacciniSummary.class);
	}
}

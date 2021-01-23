package it.carmelolagamba.ita.covid19.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.mongo.service.DatabaseService;


@Component
public class DataVacciniSomministrazioneRegionaleSummaryCollectionService {

	@Autowired
	private DatabaseService dbService;

	public com.mongodb.client.MongoCollection<DataVacciniSomministrazioneRegionaleSummary> getCollection(String nameCollection) {
		MongoDatabase db = dbService.getDefaultDb();
		return db.getCollection(nameCollection, DataVacciniSomministrazioneRegionaleSummary.class);
	}

	public MongoCollection<DataVacciniSomministrazioneRegionaleSummary> getAsyncCollection(String nameCollection) {
		com.mongodb.async.client.MongoDatabase db = dbService.getAsyncDefaultDb();
		return db.getCollection(nameCollection, DataVacciniSomministrazioneRegionaleSummary.class);
	}
}

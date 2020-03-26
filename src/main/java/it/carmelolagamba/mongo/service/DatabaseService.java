package it.carmelolagamba.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoDatabase;

import it.carmelolagamba.mongo.config.MongoProperties;

@Component
@EnableConfigurationProperties({ MongoProperties.class })
public class DatabaseService {

	@Autowired
	private MongoService mongoService;

	@Autowired
	private MongoProperties mongoProperties;

	private MongoDatabase db = null;
	private com.mongodb.async.client.MongoDatabase asyncDb = null;

	private void get() {
		db = mongoService.getMongoClient().getDatabase(mongoProperties.getDbName())
				.withCodecRegistry(mongoService.getCodecRegistry());
	}

	private void get(String dbName) {
		db = mongoService.getMongoClient().getDatabase(dbName).withCodecRegistry(mongoService.getCodecRegistry());
	}

	private void getAsync() {
		asyncDb = mongoService.getAsyncMongoClient().getDatabase(mongoProperties.getDbName())
				.withCodecRegistry(mongoService.getCodecRegistry());
	}

	private void getAsync(String dbName) {
		asyncDb = mongoService.getAsyncMongoClient().getDatabase(dbName)
				.withCodecRegistry(mongoService.getCodecRegistry());
	}

	public MongoDatabase getDefaultDb() {
		if (db == null) {
			get();
		}

		return db;
	}

	public MongoDatabase getDb(String dbName) {
		if (db == null) {
			get(dbName);
		}

		return db;
	}

	public com.mongodb.async.client.MongoDatabase getAsyncDefaultDb() {
		if (asyncDb == null) {
			getAsync();
		}

		return asyncDb;
	}

	public com.mongodb.async.client.MongoDatabase getAsyncDb(String dbName) {
		if (asyncDb == null) {
			getAsync(dbName);
		}

		return asyncDb;
	}

}

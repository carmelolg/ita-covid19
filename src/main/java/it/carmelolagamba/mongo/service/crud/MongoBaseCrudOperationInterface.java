package it.carmelolagamba.mongo.service.crud;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.mongo.service.CollectionService;

interface MongoBaseCrudOperationInterface {

	public CollectionService getCollectionService();

	public Logger getLogger();

	default public <T> T findOne(MongoCollection<T> collection, BasicDBObject objectFilter) {
		return collection.find(objectFilter).first();
	}

	default public <T> T findOne(MongoCollection<T> collection, BasicDBObject objectFilter, BasicDBObject sortRules) {
		return collection.find(objectFilter).sort(sortRules).first();
	}

	default public <T> List<T> find(MongoCollection<T> collection, BasicDBObject objectFilter, BasicDBObject sortRules,
			int offset, int itemsPerPage) {

		Long start = System.currentTimeMillis();

		int skips = (offset - 1) * itemsPerPage;

		List<T> list = Lists
				.newArrayList(collection.find(objectFilter).sort(sortRules).skip(skips).limit(itemsPerPage));

		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public <T> List<T> find(MongoCollection<T> collection, BasicDBObject objectFilter, int offset,
			int itemsPerPage) {

		Long start = System.currentTimeMillis();

		int skips = (offset - 1) * itemsPerPage;

		List<T> list = Lists.newArrayList(collection.find(objectFilter).skip(skips).limit(itemsPerPage));

		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public <T> List<T> find(MongoCollection<T> collection, Map<String, Object> map, Map<String, Object> sort) {

		Long start = System.currentTimeMillis();

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		BasicDBObject sortRulesFilter = new BasicDBObject();
		sort.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));

		List<T> list = Lists.newArrayList(collection.find(objectFilter).sort(sortRulesFilter));

		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public <T> List<T> find(MongoCollection<T> collection, Map<String, Object> map, Map<String, Object> sort,
			int offset, int itemsPerPage) {

		Long start = System.currentTimeMillis();

		int skips = (offset - 1) * itemsPerPage;

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		BasicDBObject sortRulesFilter = new BasicDBObject();
		sort.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));

		List<T> list = Lists
				.newArrayList(collection.find(objectFilter).sort(sortRulesFilter).skip(skips).limit(itemsPerPage));

		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public <T> List<T> find(MongoCollection<T> collection, Map<String, Object> map, int offset,
			int itemsPerPage) {

		Long start = System.currentTimeMillis();

		int skips = (offset - 1) * itemsPerPage;

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		List<T> list = Lists.newArrayList(collection.find(objectFilter).skip(skips).limit(itemsPerPage));

		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public <T> List<T> find(MongoCollection<T> collection, Map<String, Object> map) {

		Long start = System.currentTimeMillis();

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		List<T> list = Lists.newArrayList(collection.find(objectFilter));
		getLogger().info("Find performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return list;
	}

	default public List<Document> find(MongoCollection<Document> collection, BasicDBObject objectFilter) {
		return Lists.newArrayList(collection.find(objectFilter).iterator());
	}

	default public List<Document> find(MongoCollection<Document> collection, Bson filters) {
		return Lists.newArrayList(collection.find(filters).iterator());
	}
}

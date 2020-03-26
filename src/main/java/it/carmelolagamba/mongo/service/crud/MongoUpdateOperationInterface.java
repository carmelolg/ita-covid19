package it.carmelolagamba.mongo.service.crud;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

interface MongoUpdateOperationInterface extends MongoBaseCrudOperationInterface {

	/**
	 * Update a single document
	 * 
	 * @param collectionName name of collection
	 * @param filters        map a map of filters {String: key on json, Object:
	 *                       value on json}
	 * @param updateObject   a {@link BasicDBObject} that represent the object to
	 *                       save
	 * @return the {@link Document} updated
	 */
	default public Document update(String collectionName, Map<String, Object> filters, BasicDBObject updateObject) {
		return update(collectionName, filters, updateObject, true);
	}

	/**
	 * Update a single document
	 * 
	 * @param collectionName name of collection
	 * @param filters        map a map of filters {String: key on json, Object:
	 *                       value on json}
	 * @param updateObject   a {@link BasicDBObject} that represent the object to
	 * @param trace          debug enabled or not save
	 * @return the {@link Document} updated
	 */
	default public Document update(String collectionName, Map<String, Object> filters, BasicDBObject updateObject,
			boolean trace) {
		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject();
		filters.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		try {

			UpdateResult ur = collection.updateOne(objectFilter, updateObject);
			getLogger().debug("Update document with id {} and ack {}", ur.getUpsertedId(), ur.wasAcknowledged());
			return ur.getModifiedCount() == 1 && ur.wasAcknowledged() ? findOne(collection, updateObject) : null;

		} catch (MongoWriteException | MongoWriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: update failed.", e);
			}
			throw e;
		}
	}

	/**
	 * 
	 * @param collection   a {@link MongoCollection}
	 * @param filters      map a map of filters {String: key on json, Object: value
	 *                     on json}
	 * @param updateObject a {@link BasicDBObject} that represent the object to save
	 * @return
	 */
	default public <T> T update(MongoCollection<T> collection, Map<String, Object> filters,
			BasicDBObject updateObject) {
		return update(collection, filters, updateObject, true);
	}

	/**
	 * 
	 * @param collection   a {@link MongoCollection}
	 * @param filters      map a map of filters {String: key on json, Object: value
	 *                     on json}
	 * @param updateObject a {@link BasicDBObject} that represent the object to save
	 * @param trace        debug enabled or not
	 * @return
	 */
	default public <T> T update(MongoCollection<T> collection, Map<String, Object> filters, BasicDBObject updateObject,
			boolean trace) {

		BasicDBObject objectFilter = new BasicDBObject();
		filters.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		try {

			UpdateResult ur = collection.updateOne(objectFilter, updateObject);
			getLogger().debug("Update document with id {} and ack {}", ur.getUpsertedId(), ur.wasAcknowledged());
			return ur.getModifiedCount() == 1 && ur.wasAcknowledged() ? findOne(collection, updateObject) : null;

		} catch (MongoWriteException | MongoWriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: update failed.", e);
			}
			throw e;
		}
	}

	/**
	 * Update a single document by filters asynchronously NOTE: this method is
	 * synchronous, but is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param filter         a {@link BasicDBObject} that represent the filters that
	 *                       match element to update
	 * @param document       the new {@link Document}
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	default public boolean asyncUpdate(String collectionName, BasicDBObject filter, BasicDBObject document)
			throws InterruptedException, ExecutionException {

		com.mongodb.async.client.MongoCollection<Document> collection = getCollectionService()
				.getAsyncCollection(collectionName);

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {

			collection.updateOne(filter, document, new SingleResultCallback<UpdateResult>() {
				@Override
				public void onResult(UpdateResult result, Throwable t) {
					if (t != null) {
						getLogger().error("Error on [DocumentService]: ", t);
						future.complete(new Boolean(false));
					} else {
						getLogger().debug("Prestazioni performed in {} s with ack {}",
								(System.currentTimeMillis() - start) / 1000, result.wasAcknowledged());
						future.complete(result.wasAcknowledged());
					}
				}
			});

			return future.get();

		} catch (MongoWriteException | MongoWriteConcernException e) {
			getLogger().error("WriteConcern error on [DocumentService]: update failed.", e);
			throw e;
		}
	}

	/**
	 * Update a single document by filters asynchronously NOTE: this method is
	 * synchronous, but is used the async library of mongo to persist elements
	 * 
	 * @param collection a {@link com.mongodb.async.client.MongoCollection}
	 * @param filter     a {@link BasicDBObject} that represent the filters that
	 *                   match element to update
	 * @param document   the new {@link Document}
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	default public <T> boolean asyncUpdate(com.mongodb.async.client.MongoCollection<T> collection, BasicDBObject filter,
			BasicDBObject document) throws InterruptedException, ExecutionException {

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {

			collection.updateOne(filter, document, new SingleResultCallback<UpdateResult>() {
				@Override
				public void onResult(UpdateResult result, Throwable t) {
					if (t != null || !result.wasAcknowledged()) {
						getLogger().error("Error on [DocumentService]: ", t);
						future.complete(new Boolean(false));
					} else {

						getLogger().debug("Prestazioni performed in {} s, ack result -> {}",
								(System.currentTimeMillis() - start) / 1000, result.wasAcknowledged());
						future.complete(result.wasAcknowledged());
					}
				}
			});

			return future.get();

		} catch (MongoWriteException | MongoWriteConcernException e) {
			getLogger().error("WriteConcern error on [DocumentService]: update failed.", e);
			throw e;
		}
	}

}

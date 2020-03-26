package it.carmelolagamba.mongo.service.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteConcernException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertManyOptions;

interface MongoInsertOperationInterface extends MongoBaseCrudOperationInterface {

	/**
	 * Insert a document inside the Collection
	 * 
	 * @param collectionName name of collection
	 * @param json           a string that represent the json to save
	 * @return {@link Document} the document saved
	 */
	default public Document insert(String collectionName, String json) {
		Document dbObject = Document.parse(json);
		return insert(collectionName, dbObject, true);
	}

	/**
	 * Insert a document inside the Collection
	 * 
	 * @param collectionName name of collection
	 * @param json           a string that represent the json to save
	 * @param trace          debug enabled or not
	 * @return {@link Document} the document saved
	 */
	default public Document insert(String collectionName, String json, boolean trace) {
		Document dbObject = Document.parse(json);
		return insert(collectionName, dbObject, trace);
	}

	/**
	 * Insert a document inside the Collection
	 * 
	 * @param collectionName name of collection
	 * @param document       a {@link Document} that represent the object to save
	 * @return {@link Document} the document saved
	 */
	default public Document insert(String collectionName, Document document) {
		return insert(collectionName, document, true);
	}

	/**
	 * Insert a document inside the Collection
	 * 
	 * @param collectionName name of collection
	 * @param document       a {@link Document} that represent the object to save
	 * @param trace          debug level
	 * @return {@link Document} the document saved
	 */
	default public Document insert(String collectionName, Document document, boolean trace) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		try {
			collection.insertOne(document);
			return document;
		} catch (MongoWriteException | MongoWriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: insert failed.", e);
			}
			throw e;
		}

	}

	/**
	 * Insert a new document on MongoDB
	 * 
	 * @param collection a {@link MongoCollection}
	 * @param document   the document to insert
	 * @return the document saved
	 */
	default public <T> T insert(MongoCollection<T> collection, T document) {
		return insert(collection, document, true);
	}

	/**
	 * Insert a new document on MongoDB
	 * 
	 * @param collection a {@link MongoCollection}
	 * @param document   the document to insert
	 * @param trace      debug level
	 * @return the document saved
	 */
	default public <T> T insert(MongoCollection<T> collection, T document, boolean trace) {

		try {
			collection.insertOne(document);
			return document;
		} catch (MongoWriteException | MongoWriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: insert failed.", e);
			}
			throw e;
		}

	}

	/**
	 * Insert a set of elements on Collection
	 * 
	 * @param collectionName name of collection
	 * @param list           a {@link List} of {@link Document} or it implementation
	 * @return the number of saved objects
	 */
	default public int insertMany(String collectionName, List<? extends Document> list) {
		return insertMany(collectionName, list, true);

	}

	/**
	 * Insert a set of elements on Collection
	 * 
	 * @param collectionName name of collection
	 * @param list           a {@link List} of {@link Document} or it implementation
	 * @param trace          debug enabled or not
	 * @return the number of saved objects
	 */
	default public int insertMany(String collectionName, List<? extends Document> list, boolean trace) {

		Long start = System.currentTimeMillis();

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		try {

			collection.insertMany(list, new InsertManyOptions());
			getLogger().debug("Prestazioni performed in {} s", (System.currentTimeMillis() - start) / 1000);
			return list.size();

		} catch (WriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: insert failed.", e);
			}
			throw e;
		}

	}

	/**
	 * Insert a set of elements on Collection
	 * 
	 * @param collectionName name of collection
	 * @param list           a {@link List} of {@link String}, each string is a json
	 *                       that represent the object to save
	 * @return the number of saved objects
	 */
	default public int insertManyJson(String collectionName, List<String> jsonList) {
		return insertManyJson(collectionName, jsonList, true);
	}

	/**
	 * Insert a set of elements on Collection
	 * 
	 * @param collectionName name of collection
	 * @param list           a {@link List} of {@link String}, each string is a json
	 *                       that represent the object to save
	 * @param trace          debug enabled or not
	 * @return the number of saved objects
	 */
	default public int insertManyJson(String collectionName, List<String> jsonList, boolean trace) {

		Long start = System.currentTimeMillis();

		List<Document> dbObjectList = new ArrayList<Document>();

		jsonList.stream().forEach(json -> dbObjectList.add(Document.parse(json)));

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		try {

			collection.insertMany(dbObjectList, new InsertManyOptions());
			getLogger().debug("Prestazioni performed in {} s", (System.currentTimeMillis() - start) / 1000);
			return dbObjectList.size();

		} catch (WriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: insert failed.", e);
			}
			throw e;
		}
	}

	/**
	 * Save a single document on collection asynchronously NOTE: this method is
	 * synchronous, but is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param document       the {@link Document} to save
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	default public boolean asyncInsert(String collectionName, Document document)
			throws InterruptedException, ExecutionException {

		com.mongodb.async.client.MongoCollection<Document> collection = getCollectionService()
				.getAsyncCollection(collectionName);

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {

			collection.insertOne(document, new SingleResultCallback<Void>() {
				@Override
				public void onResult(final Void result, final Throwable t) {
					if (t != null) {
						getLogger().error("Error on [DocumentService]: ", t);
						future.complete(new Boolean(false));
					} else {
						getLogger().debug("Prestazioni performed in {} s", (System.currentTimeMillis() - start) / 1000);
						future.complete(new Boolean(true));
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
	 * Save a set of documents on collection asynchronously NOTE: this method is
	 * synchronous, but is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param documentList   a list of documents to save
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	default public boolean asyncInsertMany(String collectionName, List<Document> documentList)
			throws InterruptedException, ExecutionException {

		com.mongodb.async.client.MongoCollection<Document> collection = getCollectionService()
				.getAsyncCollection(collectionName);

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {
			if (documentList.isEmpty()) {
				future.complete(new Boolean(true));
			} else {
				collection.insertMany(documentList, new SingleResultCallback<Void>() {
					@Override
					public void onResult(final Void result, final Throwable t) {
						if (t != null) {
							getLogger().error("Error on [DocumentService]: ", t);
							future.complete(new Boolean(false));
						} else {
							getLogger().debug("Prestazioni performed in {} s",
									(System.currentTimeMillis() - start) / 1000);
							future.complete(new Boolean(true));
						}
					}
				});
			}
			return future.get();

		} catch (MongoException e) {
			getLogger().error("MongoException error on [DocumentService]: update failed.", e);
			throw e;
		}

	}

	default public <T> boolean asyncInsertMany(com.mongodb.async.client.MongoCollection<T> collection,
			List documentList) throws InterruptedException, ExecutionException {

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {
			if (documentList.isEmpty()) {
				future.complete(new Boolean(true));
			} else {
				collection.insertMany(documentList, new SingleResultCallback<Void>() {
					@Override
					public void onResult(final Void result, final Throwable t) {
						if (t != null) {
							getLogger().error("Error on [DocumentService]: ", t);
							future.complete(new Boolean(false));
						} else {
							getLogger().debug("Prestazioni performed in {} s",
									(System.currentTimeMillis() - start) / 1000);
							future.complete(new Boolean(true));
						}
					}
				});
			}

			return future.get();
		} catch (MongoException e) {
			getLogger().error("MongoException error on [DocumentService]: update failed.", e);
			throw e;
		}

	}

}

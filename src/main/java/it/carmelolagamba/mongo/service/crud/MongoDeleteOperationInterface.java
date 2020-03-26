package it.carmelolagamba.mongo.service.crud;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

interface MongoDeleteOperationInterface extends MongoBaseCrudOperationInterface {
	/**
	 * Remove all elements that matches determinate filters
	 * 
	 * @param collectionName name of collection
	 * @param filters        a {@link Bson} that represent the filters of elements
	 *                       to delete
	 */
	default public void removeByFilters(String collectionName, Bson filters) {
		removeByFilters(collectionName, filters, true);
	}

	/**
	 * Remove all elements that matches determinate filters
	 * 
	 * @param collectionName name of collection
	 * @param filters        a {@link Bson} that represent the filters of elements
	 *                       to delete
	 */
	default public void removeByFilters(String collectionName, Bson filters, boolean trace) {

		Long start = System.currentTimeMillis();

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		try {

			DeleteResult deleteResult = collection.deleteMany(filters);
			getLogger().debug("Prestazioni deleted in {} s, ack response -> {}", (System.currentTimeMillis() - start) / 1000,
					deleteResult.wasAcknowledged());

		} catch (MongoWriteException | MongoWriteConcernException e) {
			if (trace) {
				getLogger().error("WriteConcern error on [DocumentService]: update failed.", e);
			}
			throw e;
		}
	}

}

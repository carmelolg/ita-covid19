package it.carmelolagamba.mongo.service.crud;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.client.result.DeleteResult;

import it.carmelolagamba.mongo.service.CollectionService;


/**
 * Service for documents
 * 
 * @author lagamba
 */
@Component
public abstract class AbstractDocumentService implements MongoInsertOperationInterface, MongoFindOperationInterface,
		MongoUpdateOperationInterface, MongoDeleteOperationInterface, MongoAggregateOperationInterface {

	private Logger logger = LoggerFactory.getLogger(AbstractDocumentService.class);

	@Autowired
	private CollectionService collectionService;

	@Override
	public CollectionService getCollectionService() {
		return collectionService;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	/**
	 * Replace all documents, asynchronously NOTE: this method is synchronous, but
	 * is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param filter         a {@link Document} that represent the filters that
	 *                       match element to replace
	 * @param documentList   a set of documents that replace the old ones
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public boolean asyncReplaceAll(String collectionName, Document filter, List<Document> documentList)
			throws InterruptedException, ExecutionException {
		return asyncReplaceAll(collectionName, filter, documentList, true);
	}

	/**
	 * Replace all documents, asynchronously NOTE: this method is synchronous, but
	 * is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param filter         a {@link Document} that represent the filters that
	 *                       match element to replace
	 * @param documentList   a set of documents that replace the old ones
	 * @param trace          debug enabled or not
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public boolean asyncReplaceAll(String collectionName, Document filter, List<Document> documentList,
			boolean trace) throws InterruptedException, ExecutionException {

		com.mongodb.async.client.MongoCollection<Document> collection = getCollectionService()
				.getAsyncCollection(collectionName);

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

		try {

			collection.deleteMany(filter, new SingleResultCallback<DeleteResult>() {
				@Override
				public void onResult(DeleteResult result, Throwable t) {
					if (t != null || !result.wasAcknowledged()) {
						getLogger().error("Error on [DocumentService]: ", t);
						future.complete(new Boolean(false));
					} else {
						getLogger().debug("Prestazioni deleted in {} s", (System.currentTimeMillis() - start) / 1000);
						try {
							boolean check = asyncInsertMany(collectionName, documentList) && result.wasAcknowledged();
							getLogger().debug("Prestazioni replaced in {} s with ack {}",
									(System.currentTimeMillis() - start) / 1000, result.wasAcknowledged());
							future.complete(check);
						} catch (InterruptedException | ExecutionException e) {
							getLogger().error("Error on [DocumentService]: ", e);
							future.complete(new Boolean(false));
						}
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
	 * Replace all documents, asynchronously NOTE: this method is synchronous, but
	 * is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param filter         a {@link Document} that represent the filters that
	 *                       match element to replace
	 * @param documentList   a set of documents that replace the old ones
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public <T> boolean asyncReplaceAll(com.mongodb.async.client.MongoCollection<T> collection, Document filter,
			List<Document> documentList) throws InterruptedException, ExecutionException {
		return asyncReplaceAll(collection, filter, documentList, true);
	}

	/**
	 * Replace all documents, asynchronously NOTE: this method is synchronous, but
	 * is used the async library of mongo to persist elements
	 * 
	 * @param collectionName name of collection
	 * @param filter         a {@link Document} that represent the filters that
	 *                       match element to replace
	 * @param documentList   a set of documents that replace the old ones
	 * @param trace          debug enabled or not
	 * @return true if it worked, otherwise false
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public <T> boolean asyncReplaceAll(com.mongodb.async.client.MongoCollection<T> collection, Document filter,
			List<Document> documentList, boolean trace) throws InterruptedException, ExecutionException {

		Long start = System.currentTimeMillis();
		final CompletableFuture<Boolean> future = new CompletableFuture<Boolean>();

//		TODO da valutare se sostituire con questo -> collection.findOneAndReplace(filter, replacement, callback); performance?!?!

		try {

			collection.deleteMany(filter, new SingleResultCallback<DeleteResult>() {
				@Override
				public void onResult(DeleteResult result, Throwable t) {
					if (t != null || !result.wasAcknowledged()) {
						if (trace) {
							getLogger().error("Error on [DocumentService]: ", t);
						}
						future.complete(new Boolean(false));
					} else {
						getLogger().debug("Prestazioni deleted in {} s, ack result -> {}",
								(System.currentTimeMillis() - start) / 1000, result.wasAcknowledged());
						try {
							boolean insertCheck = true;
							if (!documentList.isEmpty()) {
								insertCheck = asyncInsertMany(collection, documentList);
							}
							getLogger().debug("Prestazioni replaced in {} s",
									(System.currentTimeMillis() - start) / 1000);
							future.complete(insertCheck);
						} catch (InterruptedException | ExecutionException e) {
							if (trace) {
								getLogger().error("Error on [DocumentService]: ", e);
							}
							future.complete(new Boolean(false));
						}
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

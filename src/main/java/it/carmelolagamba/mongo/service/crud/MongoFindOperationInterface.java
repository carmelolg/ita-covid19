package it.carmelolagamba.mongo.service.crud;

import static it.carmelolagamba.mongo.filter.FilterType.AND;
import static it.carmelolagamba.mongo.filter.FilterType.NONE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import it.carmelolagamba.mongo.filter.FilterType;

interface MongoFindOperationInterface extends MongoBaseCrudOperationInterface {

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param map            a map of filters {String: key on json, Object: value on
	 *                       json}
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Map<String, Object> map) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		return find(collection, objectFilter);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param pair           a couple of data that represent a single filter
	 *                       {String: key on json, Object: value on json}
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Pair<String, Object> pair) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);
		BasicDBObject objectFilter = new BasicDBObject(pair.getLeft(), pair.getRight());

		return find(collection, objectFilter);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param map            a map of filters {String: key on json, Object: value on
	 *                       json}
	 * @param sort           sort rules
	 * @param offset         the offset (for pageable query)
	 * @param itemsPerPage   number of elements per page
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Map<String, Object> map,
			Map<String, Object> sortRules) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		BasicDBObject sortRulesFilter = new BasicDBObject();
		sortRules.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));

		return find(collection, objectFilter, sortRulesFilter);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param map            a map of filters {String: key on json, Object: value on
	 *                       json}
	 * @param offset         the offset (for pageable query)
	 * @param itemsPerPage   number of elements per page
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Map<String, Object> map, int offset,
			int itemsPerPage) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		return find(collection, objectFilter, offset, itemsPerPage);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param pair           a couple of data that represent a single filter
	 *                       {String: key on json, Object: value on json}
	 * @param offset         the offset (for pageable query)
	 * @param itemsPerPage   number of elements per page
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Pair<String, Object> pair, int offset,
			int itemsPerPage) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject(pair.getLeft(), pair.getRight());

		return find(collection, objectFilter, offset, itemsPerPage);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param map            a map of filters {String: key on json, Object: value on
	 *                       json}
	 * @param sort           sort rules
	 * @param offset         the offset (for pageable query)
	 * @param itemsPerPage   number of elements per page
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Map<String, Object> map,
			Map<String, Object> sortRules, int offset, int itemsPerPage) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		BasicDBObject sortRulesFilter = new BasicDBObject();
		sortRules.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));

		return find(collection, objectFilter, sortRulesFilter, offset, itemsPerPage);
	}

	/**
	 * Find by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @param sortFilter       sort filters
	 * @return {@link List} of generics object
	 */
	default public <T> List<T> findByFilters(MongoCollection<T> collection, Map<String, Object> map,
			Map<String, Object> sortFilter) {
		return find(collection, map, sortFilter);

	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param pair           a couple of data that represent a single filter
	 *                       {String: key on json, Object: value on json}
	 * @param sort           sort rules
	 * @param offset         the offset (for pageable query)
	 * @param itemsPerPage   number of elements per page
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, Pair<String, Object> pair,
			Map<String, Object> sortRules, int offset, int itemsPerPage) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		BasicDBObject objectFilter = new BasicDBObject(pair.getLeft(), pair.getRight());

		BasicDBObject sortRulesFilter = new BasicDBObject();
		sortRules.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));

		return find(collection, objectFilter, sortRulesFilter, offset, itemsPerPage);
	}

	/**
	 * Find by filters
	 * 
	 * @param collectionName name of collection
	 * @param type           {@link FilterType} (e.g. filter on AND, OR) only one
	 *                       type settable, if your input is NONE won't use
	 *                       operators.
	 * @param filters        a undefined list of Pair (a couple of data that
	 *                       represent a single filter {String: key on json, Object:
	 *                       value on json})
	 * @return {@link List} of {@link Document}
	 */
	default public List<Document> findByFilters(String collectionName, FilterType type,
			Pair<String, Object>... filters) {

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		Bson realFilters = new Document();

		if (type.equals(NONE)) {
			HashMap<String, Object> map = new HashMap<>();
			for (int i = 0; i < filters.length; i++) {
				map.put(filters[i].getLeft(), filters[i].getRight());
			}
			return findByFilters(collectionName, map);
		} else {
			List<Bson> bsons = new ArrayList<>();
			for (int i = 0; i < filters.length; i++) {
				bsons.add(new Document(filters[i].getLeft(), filters[i].getRight()));
			}
			realFilters = type.equals(AND) ? Filters.and(bsons) : Filters.or(bsons);
		}

		return find(collection, realFilters);
	}

	/**
	 * Find by filters
	 * 
	 * @param collection   A {@link MongoCollection}
	 * @param map          map of filters
	 * @param offset       the offset (for pageable query)
	 * @param itemsPerPage number of elements per page
	 * @return {@link List} of generics object
	 */
	default public <T> List<T> findByFilters(MongoCollection<T> collection, Map<String, Object> map, int offset,
			int itemsPerPage) {
		return find(collection, map, offset, itemsPerPage);
	}

	/**
	 * Find by filters
	 * 
	 * @param collection   A {@link MongoCollection}
	 * @param map          map of filters
	 * @param sort         sort filters
	 * @param offset       the offset (for pageable query)
	 * @param itemsPerPage number of elements per page
	 * @return {@link List} of generics object
	 */
	default public <T> List<T> findByFilters(MongoCollection<T> collection, Map<String, Object> map,
			Map<String, Object> sortFilter, int offset, int itemsPerPage) {
		return find(collection, map, sortFilter, offset, itemsPerPage);

	}

	/**
	 * Find by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @return {@link List} of generics object
	 */
	default public <T> List<T> findByFilters(MongoCollection<T> collection, Map<String, Object> map) {
		return find(collection, map);
	}

	/**
	 * Find by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param filters    a {@link BasicDBObject} filters
	 * @return {@link List} of generics object
	 */
	default public <T> List<T> findByFilters(MongoCollection<T> collection, BasicDBObject filters) {
		return find(collection, filters);
	}

	/**
	 * 
	 * @param collection
	 * @param filters
	 * @param sortRules
	 * @return
	 */
	default public <T> T findOne(MongoCollection<T> collection, Map<String, Object> filters,
			Map<String, Object> sortRules) {
		BasicDBObject objectFilter = new BasicDBObject();
		BasicDBObject sortRulesFilter = new BasicDBObject();
		filters.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		sortRules.entrySet().stream().forEach(entry -> sortRulesFilter.put(entry.getKey(), entry.getValue()));
		return findOne(collection, objectFilter, sortRulesFilter);
	}

	/**
	 * Find one by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @return a generic object
	 */
	default public <T> T findOne(MongoCollection<T> collection, Map<String, Object> map) {
		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		return findOne(collection, objectFilter);
	}

	/**
	 * Find or update by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @return a generic object
	 */
	default public Document findOneAndUpdate(String collectionName, Map<String, Object> map, Bson value) {
		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		MongoCollection<Document> collection = getCollectionService().getCollection(collectionName);

		return collection.findOneAndUpdate(objectFilter, value);
	}

	/**
	 * Find or update by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @return a generic object
	 */
	default public <T> T findOneAndUpdate(MongoCollection<T> collection, Map<String, Object> map, Bson value) {
		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));
		return collection.findOneAndUpdate(objectFilter, value);
	}

	/**
	 * Count by filters
	 * 
	 * @param collection A {@link MongoCollection}
	 * @param map        map of filters
	 * @return a long
	 */
	default public <T> long count(MongoCollection<T> collection, Map<String, Object> map) {

		Long start = System.currentTimeMillis();

		BasicDBObject objectFilter = new BasicDBObject();
		map.entrySet().stream().forEach(entry -> objectFilter.put(entry.getKey(), entry.getValue()));

		getLogger().debug("Count performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return collection.countDocuments(objectFilter);
	}

	default public <T> long count(MongoCollection<T> collection, BasicDBObject objectFilter) {

		Long start = System.currentTimeMillis();

		long count = collection.countDocuments(objectFilter);

		getLogger().debug("Count performed in {} s", (System.currentTimeMillis() - start) / 1000);

		return count;
	}

}

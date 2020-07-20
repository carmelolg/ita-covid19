package it.carmelolagamba.mongo.service.custom;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.AbstractData;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@SuppressWarnings("unchecked")
public abstract class DataCollectionAbstract<T extends AbstractData> extends AbstractDocumentService {

	public void removeAll() {
		removeByFilters(getCollectionName(), new BasicDBObject());
	}

	public boolean insertAll(List<T> dataList) {
		try {
			return asyncInsertMany(getAsyncCollection(), dataList);
		} catch (InterruptedException | ExecutionException e) {
			getLogInstance().error("Errore durante il salvataggio dei dati", e);
			return false;
		}

	}

	public List<T> findAll(Pair<String, Object>... filterList) {
		MongoCollection<T> collection = getCollection();

		HashMap<String, Object> filters = new HashMap<>();
		for (Pair<String, Object> pair : filterList) {
			filters.put(pair.getLeft(), pair.getRight());
		}

		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("data", 1);

		return findByFilters(collection, filters, sortFilters);
	}

	public List<T> findLastX(int x, Pair<String, Object>... filterList) {
		MongoCollection<T> collection = getCollection();

		HashMap<String, Object> filters = new HashMap<>();
		for (Pair<String, Object> pair : filterList) {
			filters.put(pair.getLeft(), pair.getRight());
		}

		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("data", 1);

		List<T> list = findByFilters(collection, filters, sortFilters);

		return list.stream().skip(Math.max(0, list.size() - x)).collect(Collectors.toList());
	}

	public T findYesterdayData(Date currentDate, Pair<String, Object>... filterList) {
		MongoCollection<T> collection = getCollection();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DATE, -1);
		calendar.add(Calendar.HOUR_OF_DAY, -12);
		Date yesterdayFromCurrentDate = calendar.getTime();

		HashMap<String, Object> filters = new HashMap<>();
		for (Pair<String, Object> pair : filterList) {
			filters.put(pair.getLeft(), pair.getRight());
		}

		filters.put("data",
				BasicDBObjectBuilder.start("$gte", yesterdayFromCurrentDate).add("$lte", currentDate).get());

		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("data", 1);

		return findOne(collection, filters, sortFilters);
	}

	public T findLast(Pair<String, Object>... filterList) {

		MongoCollection<T> collection = getCollection();

		HashMap<String, Object> filters = new HashMap<>();
		for (Pair<String, Object> pair : filterList) {
			filters.put(pair.getLeft(), pair.getRight());
		}

		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("data", -1);

		return findOne(collection, filters, sortFilters);
	}

	protected abstract Logger getLogInstance();

	protected abstract String getCollectionName();

	protected abstract MongoCollection<T> getCollection();

	protected abstract com.mongodb.async.client.MongoCollection<T> getAsyncCollection();

}

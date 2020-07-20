package it.carmelolagamba.mongo.service.custom;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;

@Component
@SuppressWarnings("unchecked")
public class DataProvinciaDocumentService extends DataCollectionAbstract<DataProvincia> {

	private static final String COLLECTION_NAME = "DataProvincia";
	private static final String FILTER_NAME = "denominazione_provincia";

	private Logger logger = LoggerFactory.getLogger(DataProvinciaDocumentService.class);

	@Autowired
	private DataProvinciaCollectionService dataProvinciaCollectionService;

	public List<DataProvincia> findLast30ByDistrictName(String name) {
		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		return findLastX(30, nameFilter);
	}

	public DataProvincia findYesterdayDataByDistrict(String name, Date currentDate) {

		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));

		return findYesterdayData(currentDate, nameFilter);
	}

	public DataProvincia findLast(String name) {

		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));

		return findLast(nameFilter);
	}

	@Override
	protected Logger getLogInstance() {
		return logger;
	}

	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	protected MongoCollection<DataProvincia> getCollection() {
		return dataProvinciaCollectionService.getCollection(COLLECTION_NAME);
	}

	@Override
	protected com.mongodb.async.client.MongoCollection<DataProvincia> getAsyncCollection() {
		return dataProvinciaCollectionService.getAsyncCollection(COLLECTION_NAME);
	}
}

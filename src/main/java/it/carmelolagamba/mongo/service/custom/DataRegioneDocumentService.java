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

import it.carmelolagamba.ita.covid19.domain.DataRegione;

@Component
@SuppressWarnings("unchecked")
public class DataRegioneDocumentService extends DataCollectionAbstract<DataRegione> {

	private static final String COLLECTION_NAME = "DataRegione";
	private static final String FILTER_NAME = "denominazione_regione";

	private Logger logger = LoggerFactory.getLogger(DataRegioneDocumentService.class);

	@Autowired
	private DataRegioneCollectionService dataRegioneCollectionService;

	public List<DataRegione> findAll(String name) {

		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		return findAll(nameFilter);
	}

	public List<DataRegione> findLast30ByDistrictName(String name) {

		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		return findLastX(30, nameFilter);
	}

	public DataRegione findYesterdayDataByDistrict(String name, Date currentDate) {

		ImmutablePair<String, Object> nameFilter = new ImmutablePair<>(FILTER_NAME,
				Pattern.compile(name, Pattern.CASE_INSENSITIVE));
		return findYesterdayData(currentDate, nameFilter);
	}

	public DataRegione findLast(String name) {
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
	protected MongoCollection<DataRegione> getCollection() {
		return dataRegioneCollectionService.getCollection(COLLECTION_NAME);
	}

	@Override
	protected com.mongodb.async.client.MongoCollection<DataRegione> getAsyncCollection() {
		return dataRegioneCollectionService.getAsyncCollection(COLLECTION_NAME);
	}

}

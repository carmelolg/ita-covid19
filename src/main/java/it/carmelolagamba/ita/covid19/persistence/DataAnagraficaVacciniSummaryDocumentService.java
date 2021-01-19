package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import it.carmelolagamba.ita.covid19.domain.DataAnagraficaVacciniSummary;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataAnagraficaVacciniSummaryDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataAnagraficaVacciniSummary";
	private static final String FILTER_NAME = "fascia_anagrafica";

	private Logger logger = LoggerFactory.getLogger(DataAnagraficaVacciniSummaryDocumentService.class);

	@Autowired
	private DataAnagraficaVacciniSummaryCollectionService dataAnagraficaVacciniSummaryCollectionService;

	public List<DataAnagraficaVacciniSummary> findAll() {

		logger.info("Find all anagrafiche vaccini summary");
		return findByFilters(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>());

	}

	public DataAnagraficaVacciniSummary upsert(DataAnagraficaVacciniSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getFascia_anagrafica());
		DataAnagraficaVacciniSummary dataOnDb = findOne(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				filters);

		if (dataOnDb != null) {
			dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME).replaceOne(filters, updateObject);
			return updateObject;
		} else {
			return insert(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME), updateObject);
		}

	}

}

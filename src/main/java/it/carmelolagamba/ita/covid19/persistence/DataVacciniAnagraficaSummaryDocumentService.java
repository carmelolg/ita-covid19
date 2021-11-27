package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import it.carmelolagamba.ita.covid19.domain.DataVacciniAnagraficaSummary;
import it.carmelolagamba.mongo.filter.SortOperator;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataVacciniAnagraficaSummaryDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataVacciniAnagraficaSummary";
	private static final String FILTER_NAME = "fascia_anagrafica";

	private Logger logger = LoggerFactory.getLogger(DataVacciniAnagraficaSummaryDocumentService.class);

	@Autowired
	private DataVacciniAnagraficaSummaryCollectionService dataAnagraficaVacciniSummaryCollectionService;

	public List<DataVacciniAnagraficaSummary> findAll() {

		logger.info("Find all anagrafiche vaccini summary");

		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("fascia_anagrafica", SortOperator.ASC.getValue());
		
		return findByFilters(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>(), sortFilters);

	}

	public DataVacciniAnagraficaSummary upsert(DataVacciniAnagraficaSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getFascia_anagrafica());
		DataVacciniAnagraficaSummary dataOnDb = findOne(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				filters);

		if (dataOnDb != null) {
			dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME).replaceOne(filters, updateObject);
			return updateObject;
		} else {
			return insert(dataAnagraficaVacciniSummaryCollectionService.getCollection(COLLECTION_NAME), updateObject);
		}

	}

}

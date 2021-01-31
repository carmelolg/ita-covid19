package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataVacciniSummaryDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataVacciniSummary";
	private static final String FILTER_NAME = "area";

	private Logger logger = LoggerFactory.getLogger(DataVacciniSummaryDocumentService.class);

	@Autowired
	private DataVacciniSummaryCollectionService dataVacciniSummaryCollectionService;

	public List<DataVacciniSummary> find(Optional<String> regionName) {

		logger.info("Find all vaccini summary by region {}", regionName.isPresent() ? regionName.get() : "All data");
		
		HashMap<String, Object> filters = new HashMap<>();

		if (regionName.isPresent()) {
			filters.put("area_descrizione", regionName.get());
		}

		return findByFilters(dataVacciniSummaryCollectionService.getCollection(COLLECTION_NAME), filters);

	}

	public DataVacciniSummary upsert(DataVacciniSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getArea());

		DataVacciniSummary dataOnDb = findOne(dataVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				filters);

		if (dataOnDb != null) {
			dataVacciniSummaryCollectionService.getCollection(COLLECTION_NAME).replaceOne(filters, updateObject);
			return updateObject;
		} else {
			return insert(dataVacciniSummaryCollectionService.getCollection(COLLECTION_NAME), updateObject);
		}

	}

}

package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSummary;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataVacciniSummaryDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataVacciniSummary";
	private static final String FILTER_NAME = "area";

	private Logger logger = LoggerFactory.getLogger(DataVacciniSummaryDocumentService.class);

	@Autowired
	private DataVacciniSummaryCollectionService dataVacciniSummaryCollectionService;
	
	@Autowired
	private RegioneDocumentService regioneDocumentService;

	public List<DataVacciniSummary> findAll() {

		logger.info("Find all vaccini summary");
		return findByFilters(dataVacciniSummaryCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>());

	}

	public DataVacciniSummary upsert(DataVacciniSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getArea());
		
		if(updateObject.getArea_descrizione() == null) {
			updateObject.setArea_descrizione(regioneDocumentService.findDescriptionByCode(updateObject.getArea()));
		}
		
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

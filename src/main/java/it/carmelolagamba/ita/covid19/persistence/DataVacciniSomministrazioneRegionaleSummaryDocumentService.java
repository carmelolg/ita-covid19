package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import it.carmelolagamba.ita.covid19.domain.DataVacciniSomministrazioneRegionaleSummary;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataVacciniSomministrazioneRegionaleSummaryDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataVacciniSomministrazioneRegionaleSummary";
	private static final String FILTER_NAME = "area";

	
	private Logger logger = LoggerFactory.getLogger(DataVacciniSomministrazioneRegionaleSummaryDocumentService.class);

	@Autowired
	private DataVacciniSomministrazioneRegionaleSummaryCollectionService dataVacciniSomministrazioneRegionaleSummaryCollectionService;

	@Autowired
    private RegioneDocumentService regioneDocumentService;

	public List<DataVacciniSomministrazioneRegionaleSummary> findAll() {

		logger.info("Find all vaccini summary");
		return findByFilters(
				dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>());

	}
	
	public List<DataVacciniSomministrazioneRegionaleSummary> find(Optional<String> regionName) {

		logger.info("Find all vaccini summary by region {}", regionName.isPresent() ? regionName.get() : "All data");
		
		HashMap<String, Object> filters = new HashMap<>();

		if (regionName.isPresent()) {
			filters.put("area_descrizione", regionName.get());
		}
		
		HashMap<String, Object> sortFilters = new HashMap<>();
		sortFilters.put("data_somministrazione", -1);

		return findByFilters(dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME), filters, sortFilters);

	}


	public void removeAll() {
		removeByFilters(COLLECTION_NAME, new BasicDBObject());
	}

	public DataVacciniSomministrazioneRegionaleSummary upsert(
			DataVacciniSomministrazioneRegionaleSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getArea());

		filters.put("data_somministrazione", updateObject.getData_somministrazione());
		filters.put("fornitore", updateObject.getFornitore());
		filters.put("fascia_anagrafica", updateObject.getFascia_anagrafica());


		DataVacciniSomministrazioneRegionaleSummary dataOnDb = findOne(
				dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME), filters);

		if (dataOnDb != null) {
			logger.info("Replace of {} {} {} date {}", updateObject.getArea(), updateObject.getFornitore(), updateObject.getFascia_anagrafica(), updateObject.getData_somministrazione());
			dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME).replaceOne(filters, updateObject);
			return updateObject;
		} else {
			logger.info("Import of {} {} {} date {}", updateObject.getArea(), updateObject.getFornitore(), updateObject.getFascia_anagrafica(), updateObject.getData_somministrazione());
            if (updateObject.getArea_descrizione() == null) {
                updateObject.setArea_descrizione(regioneDocumentService.findDescriptionByCode(updateObject.getArea()));
            }
			return insert(dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME),
					updateObject);
		}

	}

}

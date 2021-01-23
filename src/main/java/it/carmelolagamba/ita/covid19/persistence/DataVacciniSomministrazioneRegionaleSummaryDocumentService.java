package it.carmelolagamba.ita.covid19.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

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
		return findByFilters(dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>());

	}

	public DataVacciniSomministrazioneRegionaleSummary upsert(DataVacciniSomministrazioneRegionaleSummary updateObject) {

		BasicDBObject filters = new BasicDBObject(FILTER_NAME, updateObject.getArea());
		
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateInString = dateFormat.format(updateObject.getData_somministrazione());  
		filters.put("data_somministrazione", dateInString);
		
		filters.put("fornitore", updateObject.getFornitore());
		filters.put("fascia_anagrafica", updateObject.getFascia_anagrafica());
		
		logger.info("Import of {} number {}", updateObject.getArea(), updateObject.getData_somministrazione());
		
		DataVacciniSomministrazioneRegionaleSummary dataOnDb = findOne(dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME),
				filters);

		if (dataOnDb != null) {
			return updateObject;
		} else {
			if(updateObject.getArea_descrizione() == null) {
				updateObject.setArea_descrizione(regioneDocumentService.findDescriptionByCode(updateObject.getArea()));
			}
			return insert(dataVacciniSomministrazioneRegionaleSummaryCollectionService.getCollection(COLLECTION_NAME), updateObject);
		}

	}

}

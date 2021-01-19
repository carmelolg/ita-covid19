package it.carmelolagamba.ita.covid19.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoCollection;

import it.carmelolagamba.ita.covid19.domain.DataNazione;

@Component
public class DataNazioneDocumentService extends DataCollectionAbstract<DataNazione> {

	private static final String COLLECTION_NAME = "DataNazione";

	private Logger logger = LoggerFactory.getLogger(DataNazioneDocumentService.class);

	@Autowired
	private DataNazioneCollectionService dataNazioneCollectionService;

	@Override
	protected Logger getLogInstance() {
		return logger;
	}

	@Override
	protected String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	protected MongoCollection<DataNazione> getCollection() {
		return dataNazioneCollectionService.getCollection(COLLECTION_NAME);
	}

	@Override
	protected com.mongodb.async.client.MongoCollection<DataNazione> getAsyncCollection() {
		return dataNazioneCollectionService.getAsyncCollection(COLLECTION_NAME);
	}

}

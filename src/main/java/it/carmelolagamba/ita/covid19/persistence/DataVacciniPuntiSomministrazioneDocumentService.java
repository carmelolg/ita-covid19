package it.carmelolagamba.ita.covid19.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import it.carmelolagamba.ita.covid19.domain.DataVacciniPuntiSomministrazione;
import it.carmelolagamba.ita.covid19.domain.Regione;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;

@Component
public class DataVacciniPuntiSomministrazioneDocumentService extends AbstractDocumentService {

	private static final String COLLECTION_NAME = "DataVacciniPuntiSomministrazione";

	private Logger logger = LoggerFactory.getLogger(DataVacciniPuntiSomministrazioneDocumentService.class);

	@Autowired
	private DataVacciniPuntiSomministrazioneCollectionService dataPuntiSomministrazioneCollectionService;

	@Autowired
	private RegioneDocumentService regioneDocumentService;

	public List<DataVacciniPuntiSomministrazione> findAll() {

		logger.info("Find all vaccini summary");
		return findByFilters(dataPuntiSomministrazioneCollectionService.getCollection(COLLECTION_NAME),
				new HashMap<String, Object>());

	}

	public boolean replaceAll(List<DataVacciniPuntiSomministrazione> puntiSomministrazioneList) {

		List<Regione> regioni = regioneDocumentService.findAll();
		List<Document> documentList = puntiSomministrazioneList.stream().map(ps -> this.map(ps, regioni))
				.collect(Collectors.toList());

		removeByFilters(COLLECTION_NAME, new BasicDBObject());
		int itemsAdded = insertMany(COLLECTION_NAME, documentList);

		return documentList.size() == itemsAdded;
	}

	private Document map(DataVacciniPuntiSomministrazione bean, List<Regione> regioni) {
		Document entity = new Document();

		if (bean.getArea() != null) {
			entity.put("area", bean.getArea());
		
			Optional<String> areaDescrizione = regioni.stream().filter(r -> r.getCode().equals(bean.getArea()))
					.map(Regione::getNome).findAny();
			if (areaDescrizione.isPresent()) {
				entity.put("area_descrizione", areaDescrizione.get());
			}

		}

		if (bean.getProvincia() != null) {
			entity.put("provincia", bean.getProvincia());
		}

		if (bean.getComune() != null) {
			entity.put("comune", bean.getComune());
		}

		if (bean.getPresidio_ospedaliero() != null) {
			entity.put("presidio_ospedaliero", bean.getPresidio_ospedaliero());
		}

		if (bean.getUltimo_aggiornamento_interno() != null) {
			entity.put("ultimo_aggiornamento_interno", bean.getUltimo_aggiornamento_interno());
		}

		return entity;
	}
}

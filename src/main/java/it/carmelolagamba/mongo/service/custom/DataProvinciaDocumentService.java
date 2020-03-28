package it.carmelolagamba.mongo.service.custom;

import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.mongo.service.crud.AbstractDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class DataProvinciaDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataProvincia";

    private Logger logger = LoggerFactory.getLogger(DataProvinciaDocumentService.class);

    @Autowired
    private DataProvinciaCollectionService dataProvinciaCollectionService;

    public boolean insertAll(List<DataProvincia> dataProvinciaList) {
        try {
            return asyncInsertMany(dataProvinciaCollectionService.getAsyncCollection(COLLECTION_NAME), dataProvinciaList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

    public List<DataProvincia> findAllByDistrictName(String name){
        MongoCollection<DataProvincia> collection = dataProvinciaCollectionService.getCollection(COLLECTION_NAME);

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("denominazione_provincia", name);

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        return findByFilters(collection, filters, sortFilters);
    }

    public DataProvincia findYesterdayDataByDistrict(String name, Date currentDate){
        MongoCollection<DataProvincia> collection = dataProvinciaCollectionService.getCollection(COLLECTION_NAME);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date yesterdayFromCurrentDate = calendar.getTime();

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("denominazione_provincia", name);
        filters.put("data", yesterdayFromCurrentDate);

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        return findOne(collection, filters, sortFilters);
    }

}

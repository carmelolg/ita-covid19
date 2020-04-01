package it.carmelolagamba.mongo.service.custom;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.domain.FileImported;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class DataRegioneDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataRegione";

    private Logger logger = LoggerFactory.getLogger(DataRegioneDocumentService.class);

    @Autowired
    private DataRegioneCollectionService dataRegioneCollectionService;

    public boolean insertAll(List<DataRegione> dataRegioneList) {
        try {
            MongoCollection<DataRegione> collection = dataRegioneCollectionService.getCollection(COLLECTION_NAME);
            return asyncInsertMany(dataRegioneCollectionService.getAsyncCollection(COLLECTION_NAME), dataRegioneList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

    public List<DataRegione> findLast30ByDistrictName(String name){
        MongoCollection<DataRegione> collection = dataRegioneCollectionService.getCollection(COLLECTION_NAME);

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("denominazione_regione", Pattern.compile(name, Pattern.CASE_INSENSITIVE));

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        List<DataRegione> list = findByFilters(collection, filters, sortFilters);

        return list.stream().skip(Math.max(0, list.size() - 30)).collect(Collectors.toList());
    }

    public DataRegione findYesterdayDataByDistrict(String name, Date currentDate){
        MongoCollection<DataRegione> collection = dataRegioneCollectionService.getCollection(COLLECTION_NAME);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        calendar.add(Calendar.HOUR_OF_DAY, -12);
        Date yesterdayFromCurrentDate = calendar.getTime();

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("denominazione_regione", Pattern.compile(name, Pattern.CASE_INSENSITIVE));
        filters.put("data", BasicDBObjectBuilder.start("$gte", yesterdayFromCurrentDate).add("$lte", currentDate).get());

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        return findOne(collection, filters, sortFilters);
    }

}

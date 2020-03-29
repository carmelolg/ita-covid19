package it.carmelolagamba.mongo.service.custom;

import com.mongodb.client.MongoCollection;
import it.carmelolagamba.ita.covid19.domain.DataNazione;
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
import java.util.stream.Collectors;

@Component
public class DataNazioneDocumentService extends AbstractDocumentService {

    private static final String COLLECTION_NAME = "DataNazione";

    private Logger logger = LoggerFactory.getLogger(DataNazioneDocumentService.class);

    @Autowired
    private DataNazioneCollectionService dataNazioneCollectionService;

    public boolean insertAll(List<DataNazione> dataNazioneList) {
        try {
            MongoCollection<DataNazione> collection = dataNazioneCollectionService.getCollection(COLLECTION_NAME);
            return asyncInsertMany(dataNazioneCollectionService.getAsyncCollection(COLLECTION_NAME), dataNazioneList);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Errore durante il salvataggio dei dati", e);
            return false;
        }

    }

    public List<DataNazione> findLast30(){
        MongoCollection<DataNazione> collection = dataNazioneCollectionService.getCollection(COLLECTION_NAME);

        HashMap<String, Object> filters = new HashMap<>();

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        List<DataNazione> list = findByFilters(collection, filters, sortFilters);

        return list.stream().skip(Math.max(0, list.size() - 30)).collect(Collectors.toList());
    }

    public DataNazione findYesterdayData(Date currentDate){
        MongoCollection<DataNazione> collection = dataNazioneCollectionService.getCollection(COLLECTION_NAME);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, -1);
        Date yesterdayFromCurrentDate = calendar.getTime();

        HashMap<String, Object> filters = new HashMap<>();
        filters.put("data", yesterdayFromCurrentDate);

        HashMap<String, Object> sortFilters = new HashMap<>();
        sortFilters.put("data", 1);

        return findOne(collection, filters, sortFilters);
    }

}

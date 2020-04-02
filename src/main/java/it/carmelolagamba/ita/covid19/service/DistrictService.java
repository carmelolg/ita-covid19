package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DistrictService {

    @Autowired
    private DataProvinciaDocumentService dataProvinciaDocumentService;

    public AndamentoDto findLast30ByDistrictName(String district) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataProvincia> dataProvinciaList = dataProvinciaDocumentService.findLast30ByDistrictName(district);

        if (dataProvinciaList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di casi per la provincia di %s", district));

            dataProvinciaList.forEach(data -> {
                DataProvincia yesterdayDate = dataProvinciaDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_casi() - yesterdayDate.getTotale_casi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_casi(), increaseFromYesterday, data.getData()));
            });

//            if(dataProvinciaList.size() > 2){
//                Pair<DataProvincia, DataProvincia> pair = new ImmutablePair<>(dataProvinciaList.get(dataProvinciaList.size()))
//
//            }

            return andamentoDto;
        }


    }

}

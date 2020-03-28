package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatsService {

    @Autowired
    private DataProvinciaDocumentService dataProvinciaDocumentService;

    public AndamentoDto findStatsByDistrictName(String district) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataProvincia> dataProvinciaList = dataProvinciaDocumentService.findAllByDistrictName(district);

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

            return andamentoDto;
        }


    }

}
package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionService {

    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;

    public AndamentoDto findLast30ByDistrictName(String district) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di casi per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_casi() - yesterdayDate.getTotale_casi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_casi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }


    }

    public AndamentoDto findLast30HospitalizedByDistrictName(String district) {
        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati per la regione di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_ospedalizzati() - yesterdayDate.getTotale_ospedalizzati() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_ospedalizzati(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30IntensiveCureByDistrictName(String district) {
        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di persone in terapia intensiva per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTerapia_intensiva() - yesterdayDate.getTerapia_intensiva() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTerapia_intensiva(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30DeadByDistrictName(String district) {
        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di morti per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDeceduti() - yesterdayDate.getDeceduti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDeceduti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30TestByDistrictName(String district) {
        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di tamponi per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTamponi() - yesterdayDate.getTamponi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTamponi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30RecoveredByDistrictName(String district) {
        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di guariti per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(district, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDimessi_guariti() - yesterdayDate.getDimessi_guariti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDimessi_guariti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }
}

package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.domain.DataProvincia;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import it.carmelolagamba.mongo.service.custom.DataProvinciaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NationalService {

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    public AndamentoDto findLast30TotalCases() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di casi italiani"));

            dataNazioneList.forEach(data -> {
                andamentoDto.getResults().add(new ResultDto(data.getTotale_casi(), data.getNuovi_positivi(), data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Hospitalized() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_ospedalizzati() - yesterdayDate.getTotale_ospedalizzati() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_ospedalizzati(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30IntensiveCure() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTerapia_intensiva() - yesterdayDate.getTerapia_intensiva() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTerapia_intensiva(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Dead() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDeceduti() - yesterdayDate.getDeceduti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDeceduti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Test() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTamponi() - yesterdayDate.getTamponi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTamponi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Recovered() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDimessi_guariti() - yesterdayDate.getDimessi_guariti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDimessi_guariti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }


}

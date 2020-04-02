package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.view.AndamentoDto;
import it.carmelolagamba.ita.covid19.view.NazioneStatsDto;
import it.carmelolagamba.ita.covid19.view.ResultDto;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class NationalService {

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    public NazioneStatsDto findStats(){

        NazioneStatsDto dto = new NazioneStatsDto();

        // Tasso di crescita
        DataNazione last = dataNazioneDocumentService.findLast();
        DataNazione lastYesterday = dataNazioneDocumentService.findYesterdayData(last.getData());

        if(last != null && lastYesterday != null){

            // growth rate
            Double currentCase = Double.valueOf(last.getTotale_casi());
            Double yesterdayCase = Double.valueOf(lastYesterday.getTotale_casi());

            Double rate = ((currentCase - yesterdayCase) / yesterdayCase) * 100;
            dto.setCurrentRateOfGrowth(round(rate));

            // growth rate new positive
            Double currentNewPositive = Double.valueOf(last.getNuovi_positivi());
            Double yesterdayNewPositive = Double.valueOf(lastYesterday.getNuovi_positivi());

            Double rateNewPositive = ((currentNewPositive - yesterdayNewPositive) / yesterdayNewPositive) * 100;
            dto.setCurrentNewPositiveRateOfGrowth(round(rateNewPositive));


            // percentage test based
            Double currentTest = Double.valueOf(last.getTamponi());
            Double percentage = Double.valueOf((currentCase * 100) / currentTest);

            dto.setCurrentPositivePercentageBasedOnTests(round(percentage));

        }


        return dto;
    }

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

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}

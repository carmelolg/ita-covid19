package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.utils.MathUtils;
import it.carmelolagamba.ita.covid19.view.*;
import it.carmelolagamba.mongo.service.custom.DataNazioneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static it.carmelolagamba.ita.covid19.utils.MathUtils.round;

@Component
public class NationalService {

    @Autowired
    private DataNazioneDocumentService dataNazioneDocumentService;

    
    public ResumeStatsDto findResume(boolean all) {

        ResumeStatsDto resumeDto = new ResumeStatsDto();

        List<DataNazione> dataNazioneList = new ArrayList<>();

        if (all) {
            // TODO da migliorare parte grafica prima altrimenti findAll()
            dataNazioneList = dataNazioneDocumentService.findLast30();
        } else {
            dataNazioneList = dataNazioneDocumentService.findLast30();
        }

        if (dataNazioneList.isEmpty()) {
            resumeDto.setDescription("Dati non presenti");
            return resumeDto;
        } else {
            resumeDto.setDescription(String.format("Riepilogo statistiche italiane"));

            dataNazioneList.forEach(data -> {

                AllResultDto result = new AllResultDto(
                        data.getTotale_casi(),
                        data.getTotale_positivi(),
                        data.getDimessi_guariti(),
                        data.getDeceduti(),
                        data.getData()
                );

                resumeDto.getResults().add(result);
            });

            return resumeDto;
        }
    }
    public GenericStatsDto findStats() {

        GenericStatsDto dto = new GenericStatsDto();

        // Tasso di crescita
        DataNazione last = dataNazioneDocumentService.findLast();
        DataNazione lastYesterday = dataNazioneDocumentService.findYesterdayData(last.getData());

        if(last != null) {
            dto.setCurrentDead(last.getDeceduti());
            dto.setCurrentHomeIsolation(last.getIsolamento_domiciliare());
            dto.setCurrentHospedalized(last.getTotale_ospedalizzati());
            dto.setCurrentIntesiveCare(last.getTerapia_intensiva());
            dto.setCurrentPositives(last.getTotale_positivi());
            dto.setCurrentRecovered(last.getDimessi_guariti());
            dto.setCurrentTests(last.getTamponi());
            dto.setCurrentTotalCases(last.getTotale_casi());
        }

        if (last != null && lastYesterday != null) {

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

            // percentage recovered
            Double currentRecovered = Double.valueOf(last.getDimessi_guariti());
            Double percentageRecovered = Double.valueOf((currentRecovered * 100) / currentCase);

            dto.setCurrentRecoveredPercentage(round(percentageRecovered));

            // percentage dead
            Double currentDead = Double.valueOf(last.getDeceduti());
            Double percentageDead = Double.valueOf((currentDead * 100) / currentCase);

            dto.setCurrentDeadPercentage(round(percentageDead));

            // percentage intensive care
            Double currentIntensiveCare = Double.valueOf(last.getTerapia_intensiva());
            Double percentageIntensiveCare = Double.valueOf((currentIntensiveCare * 100) / currentCase);

            dto.setCurrentIntensiveCarePercentage(round(percentageIntensiveCare));

            // percentage hospitalized
            Double currentHospitalized = Double.valueOf(last.getTotale_ospedalizzati());
            Double percentageHospitalized = Double.valueOf((currentHospitalized * 100) / currentCase);

            dto.setCurrentHospitalizedPercentage(round(percentageHospitalized));

        }


        return dto;
    }

    public GrowthRateDto findGrowthRate() {

        GrowthRateDto growthRateDto = new GrowthRateDto();

        // Tasso di crescita
        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            growthRateDto.setDescription("Dati non presenti");
            return growthRateDto;
        } else {
            growthRateDto.setDescription(String.format("Statistiche del tasso di crescita italiano"));

            dataNazioneList.forEach(data -> {
                // calcolo
                Double currentCase = Double.valueOf(data.getTotale_casi());
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                Double yesterdayCase = Double.valueOf(yesterdayDate.getTotale_casi());

                Double rate = 0.0;
                if(yesterdayCase == 0){
                    rate = currentCase * 100;
                }else{
                    rate = ((currentCase - yesterdayCase) / yesterdayCase) * 100;
                }

                growthRateDto.getResults().add(new GrowthRateResultDto(MathUtils.round(rate), data.getData()));
            });

            return growthRateDto;
        }
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

    public AndamentoDto findLast30NewPositive() {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataNazione> dataNazioneList = dataNazioneDocumentService.findLast30();

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche dei nuovi positivi in Italia"));

            dataNazioneList.forEach(data -> {
                DataNazione yesterdayDate = dataNazioneDocumentService.findYesterdayData(data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getNuovi_positivi() - yesterdayDate.getNuovi_positivi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getNuovi_positivi(), increaseFromYesterday, data.getData()));
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

package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.utils.MathUtils;
import it.carmelolagamba.ita.covid19.view.*;
import it.carmelolagamba.mongo.service.custom.DataRegioneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static it.carmelolagamba.ita.covid19.utils.MathUtils.round;

@Component
public class RegionService {

    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;

    public GrowthRateDto findGrowthRate(String region) {

        GrowthRateDto growthRateDto = new GrowthRateDto();

        // Tasso di crescita
        List<DataRegione> dataNazioneList = dataRegioneDocumentService.findLast30ByDistrictName(region);

        if (dataNazioneList.isEmpty()) {
            growthRateDto.setDescription("Dati non presenti");
            return growthRateDto;
        } else {
            growthRateDto.setDescription(String.format("Statistiche del tasso di crescita per la regione %s", region));

            dataNazioneList.forEach(data -> {
                // calcolo
                Double currentCase = Double.valueOf(data.getTotale_casi());
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(region, data.getData());
                Double yesterdayCase = Double.valueOf(yesterdayDate.getTotale_casi());

                Double rate = ((currentCase - yesterdayCase) / yesterdayCase) * 100;

                growthRateDto.getResults().add(new GrowthRateResultDto(MathUtils.round(rate), data.getData()));
            });

            return growthRateDto;
        }
    }

    public GenericStatsDto findStats(String region){

        GenericStatsDto dto = new GenericStatsDto();

        // Tasso di crescita
        DataRegione last = dataRegioneDocumentService.findLast(region);
        DataRegione lastYesterday = dataRegioneDocumentService.findYesterdayDataByDistrict(region, last.getData());

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

    public AndamentoDto findLast30ByDistrictName(String district) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataRegioneList = dataRegioneDocumentService.findLast30ByDistrictName(district);

        if (dataRegioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di casi per la provincia di %s", district));

            dataRegioneList.forEach(data -> {
                andamentoDto.getResults().add(new ResultDto(data.getTotale_casi(), data.getNuovi_positivi(), data.getData()));
            });

            return andamentoDto;
        }


    }

    public AndamentoDto findLast30NewPositiveByRegion(String region) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<DataRegione> dataNazioneList = dataRegioneDocumentService.findLast30ByDistrictName(region);

        if (dataNazioneList.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche dei nuovi positivi in Italia"));

            dataNazioneList.forEach(data -> {
                DataRegione yesterdayDate = dataRegioneDocumentService.findYesterdayDataByDistrict(region, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getNuovi_positivi() - yesterdayDate.getNuovi_positivi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getNuovi_positivi(), increaseFromYesterday, data.getData()));
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

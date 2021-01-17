package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.AbstractFullData;
import it.carmelolagamba.ita.covid19.utils.MathUtils;
import it.carmelolagamba.ita.covid19.view.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static it.carmelolagamba.ita.covid19.utils.MathUtils.round;

public abstract class AbstractService<T extends AbstractFullData> {

    public ResumeStatsDto findResume(boolean all) {
        return findResume(all, null);
    }

    public ResumeStatsDto findResume(boolean all, Optional<String> name) {

        ResumeStatsDto resumeDto = new ResumeStatsDto();

        List<T> list = new ArrayList<>();

        if (all) {
            // TODO da migliorare parte grafica prima altrimenti findAll()
            list = findLast30(name);
        } else {
            list = findLast30(name);
        }

        if (list.isEmpty()) {
            resumeDto.setDescription("Dati non presenti");
            return resumeDto;
        } else {
            resumeDto.setDescription(String.format("Riepilogo statistiche italiane"));

            list.forEach(data -> {

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
        return findStats(null);
    }

    public GenericStatsDto findStats(Optional<String> name) {

        GenericStatsDto dto = new GenericStatsDto();

        // Tasso di crescita
        T last = findLast(name);
        T lastYesterday = findYesterdayData(name, last.getData());

        if(last != null) {
            dto.setCurrentDead(last.getDeceduti());
            dto.setCurrentHomeIsolation(last.getIsolamento_domiciliare());
            dto.setCurrentHospedalized(last.getTotale_ospedalizzati());
            dto.setCurrentIntesiveCare(last.getTerapia_intensiva());
            dto.setCurrentPositives(last.getTotale_positivi());
            dto.setCurrentRecovered(last.getDimessi_guariti());
            dto.setCurrentTests(last.getTamponi());
            dto.setCurrentTotalCases(last.getTotale_casi());
            dto.setNewPositives(last.getNuovi_positivi());
            
            int variationNewPositives= (lastYesterday != null) ? last.getTotale_positivi() - lastYesterday.getTotale_positivi() : 0;
            dto.setVariationNewPositives(variationNewPositives);
            
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
            Double currentTest = Double.valueOf(last.getTamponi()- lastYesterday.getTamponi());
            Double percentage = Double.valueOf((currentNewPositive * 100) / currentTest);

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
            
            dto.setTestsToday(last.getTamponi() - lastYesterday.getTamponi());
        }


        return dto;
    }

    public GrowthRateDto findGrowthRate() {
        return findGrowthRate(null);
    }

    public GrowthRateDto findGrowthRate(Optional<String> name) {

        GrowthRateDto growthRateDto = new GrowthRateDto();

        // Tasso di crescita
        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            growthRateDto.setDescription("Dati non presenti");
            return growthRateDto;
        } else {
            growthRateDto.setDescription(String.format("Statistiche del tasso di crescita italiano"));

            list.forEach(data -> {
                // calcolo
                Double currentCase = Double.valueOf(data.getTotale_casi());
                T yesterdayDate = findYesterdayData(name, data.getData());
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

    public AndamentoDto findLast30Isolated() {
        return findLast30Isolated(null);
    }

    public AndamentoDto findLast30Isolated(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di italiani in isolamento domiciliare"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getIsolamento_domiciliare() - yesterdayDate.getIsolamento_domiciliare() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getIsolamento_domiciliare(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30TotalPositive() {
        return findLast30TotalPositive(null);
    }

    public AndamentoDto findLast30TotalPositive(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di positivi italiani"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_positivi() - yesterdayDate.getTotale_positivi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_positivi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30TotalCases() {
        return findLast30TotalCases(null);
    }

    public AndamentoDto findLast30TotalCases(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di casi italiani"));

            list.forEach(data -> {
                andamentoDto.getResults().add(new ResultDto(data.getTotale_casi(), data.getNuovi_positivi(), data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30NewPositive() {
        return findLast30NewPositive(null);
    }

    public AndamentoDto findLast30NewPositive(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche dei nuovi positivi in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getNuovi_positivi() - yesterdayDate.getNuovi_positivi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getNuovi_positivi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }
    
    public AndamentoDto findLast30VariationNewPositive() {
    	return findLast30VariationNewPositive(null);
    }
    
    public AndamentoDto findLast30VariationNewPositive(Optional<String> name) {
    	
    	AndamentoDto andamentoDto = new AndamentoDto();
    	
    	List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche della variazione dei nuovi positivi in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getVariazione_totale_positivi() - yesterdayDate.getVariazione_totale_positivi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getVariazione_totale_positivi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    	
    }

    public AndamentoDto findLast30Hospitalized() {
        return findLast30Hospitalized(null);
    }

    public AndamentoDto findLast30Hospitalized(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTotale_ospedalizzati() - yesterdayDate.getTotale_ospedalizzati() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTotale_ospedalizzati(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30IntensiveCure() {
        return findLast30IntensiveCure(null);
    }


    public AndamentoDto findLast30IntensiveCure(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTerapia_intensiva() - yesterdayDate.getTerapia_intensiva() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTerapia_intensiva(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Dead() {
        return findLast30Dead(null);
    }

    public AndamentoDto findLast30Dead(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDeceduti() - yesterdayDate.getDeceduti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDeceduti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Test() {
        return findLast30Test(null);
    }

    public AndamentoDto findLast30Test(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getTamponi() - yesterdayDate.getTamponi() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getTamponi(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    public AndamentoDto findLast30Recovered() {
        return findLast30Recovered(null);
    }

    public AndamentoDto findLast30Recovered(Optional<String> name) {

        AndamentoDto andamentoDto = new AndamentoDto();

        List<T> list = findLast30(name);

        if (list.isEmpty()) {
            andamentoDto.setDescription("Dati non presenti");
            return andamentoDto;
        } else {
            andamentoDto.setDescription(String.format("Statistiche del numero di ospedalizzati in Italia"));

            list.forEach(data -> {
                T yesterdayDate = findYesterdayData(name, data.getData());
                int increaseFromYesterday = (yesterdayDate != null) ? data.getDimessi_guariti() - yesterdayDate.getDimessi_guariti() : 0;
                andamentoDto.getResults().add(new ResultDto(data.getDimessi_guariti(), increaseFromYesterday, data.getData()));
            });

            return andamentoDto;
        }
    }

    protected abstract List<T> findAll(Optional<String> name);
    protected abstract List<T> findLast30(Optional<String> name);
    protected abstract T findYesterdayData(Optional<String> name, Date data);
    protected abstract T findLast(Optional<String> name);
}

package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.ita.covid19.domain.AbstractFullData;
import it.carmelolagamba.ita.covid19.view.AllResultDto;
import it.carmelolagamba.ita.covid19.view.ResumeStatsDto;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<T extends AbstractFullData> {

    public ResumeStatsDto findResume(boolean all) {

        ResumeStatsDto resumeDto = new ResumeStatsDto();

        List<T> list = new ArrayList<>();

        if (all) {
            list = findAll();
        } else {
            list = findLast30();
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

    protected abstract List<T> findAll();
    protected abstract List<T> findLast30();
}

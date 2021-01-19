package it.carmelolagamba.ita.covid19.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataRegione;
import it.carmelolagamba.ita.covid19.persistence.DataRegioneDocumentService;

@Component
public class RegionService extends AbstractService<DataRegione> {

    private Logger logger = LoggerFactory.getLogger(RegionService.class);
    @Autowired
    private DataRegioneDocumentService dataRegioneDocumentService;

    @Override
    protected List<DataRegione> findAll(Optional<String> name) {
        if(name.isPresent()){
            return dataRegioneDocumentService.findAll(name.get());
        }else{
            logger.error("Nome regione mancante");
            return null;
        }
    }

    @Override
    protected List<DataRegione> findLast30(Optional<String> name) {
        if(name.isPresent()){
            return dataRegioneDocumentService.findLast30ByDistrictName(name.get());
        }else{
            logger.error("Nome regione mancante");
            return null;
        }
    }

    @Override
    protected DataRegione findYesterdayData(Optional<String> name, Date data) {
        if(name.isPresent()){
            return dataRegioneDocumentService.findYesterdayDataByDistrict(name.get(), data);
        }else{
            logger.error("Nome regione mancante");
            return null;
        }
    }

    @Override
    protected DataRegione findLast(Optional<String> name) {
        if(name.isPresent()){
            return dataRegioneDocumentService.findLast(name.get());
        }else{
            logger.error("Nome regione mancante");
            return null;
        }
    }
}

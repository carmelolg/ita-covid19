package it.carmelolagamba.ita.covid19.service;

import it.carmelolagamba.mongo.service.custom.ProvinciaDocumentService;
import it.carmelolagamba.mongo.service.custom.RegioneDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilsService {

    @Autowired
    private RegioneDocumentService regioneDocumentService;

    @Autowired
    private ProvinciaDocumentService provinciaDocumentService;

    public List<String> allRegions(){
        return regioneDocumentService.findAll().stream().map(region -> region.getNome()).collect(Collectors.toList());
    }


    public List<String> allDistricts(){
        return provinciaDocumentService.findAll().stream().map(district -> district.getNome()).collect(Collectors.toList());
    }

}

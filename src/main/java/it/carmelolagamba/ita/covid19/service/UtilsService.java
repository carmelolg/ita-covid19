package it.carmelolagamba.ita.covid19.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.FileImported;
import it.carmelolagamba.ita.covid19.persistence.FileImportedDocumentService;
import it.carmelolagamba.ita.covid19.persistence.ProvinciaDocumentService;
import it.carmelolagamba.ita.covid19.persistence.RegioneDocumentService;
import it.carmelolagamba.ita.covid19.view.FileImportedDto;

@Component
public class UtilsService {

    @Autowired
    private RegioneDocumentService regioneDocumentService;

    @Autowired
    private ProvinciaDocumentService provinciaDocumentService;

    @Autowired
    private FileImportedDocumentService fileImportedDocumentService;

    public List<String> allRegions(){
        return regioneDocumentService.findAll().stream().map(region -> region.getNome()).collect(Collectors.toList());
    }


    public List<String> allDistricts(){
        return provinciaDocumentService.findAll().stream().map(district -> district.getNome()).collect(Collectors.toList());
    }

    public FileImportedDto getLastFileImportedByType(String type){

        FileImported file = fileImportedDocumentService.findLastByType(type);
        return new FileImportedDto(file.getFilename(), file.getMigrationDate());
    }

}

package it.carmelolagamba.ita.covid19.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataNazione;
import it.carmelolagamba.ita.covid19.persistence.DataNazioneDocumentService;

@Component
@SuppressWarnings("unchecked")
public class NationalService extends AbstractService<DataNazione> {

	@Autowired
	private DataNazioneDocumentService dataNazioneDocumentService;

	@Override
	protected List<DataNazione> findAll(Optional<String> name) {
		return dataNazioneDocumentService.findAll();
	}

	@Override
	protected List<DataNazione> findLast30(Optional<String> name) {
		return dataNazioneDocumentService.findLastX(30);
	}

	@Override
	protected DataNazione findYesterdayData(Optional<String> name, Date data) {
		return dataNazioneDocumentService.findYesterdayData(data);
	}

	@Override
	protected DataNazione findLast(Optional<String> name) {
		return dataNazioneDocumentService.findLast();
	}

}

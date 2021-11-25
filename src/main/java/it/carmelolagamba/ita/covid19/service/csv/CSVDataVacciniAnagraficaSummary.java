package it.carmelolagamba.ita.covid19.service.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import it.carmelolagamba.ita.covid19.domain.DataVacciniAnagraficaSummary;
import it.carmelolagamba.ita.covid19.domain.DataVacciniAnagraficaSummary.FIELD;
import it.carmelolagamba.ita.covid19.utils.FileUtils;

@Component
public class CSVDataVacciniAnagraficaSummary extends AbstractCSVDataReader<DataVacciniAnagraficaSummary> {

    private static Logger logger = LoggerFactory.getLogger(CSVDataVacciniAnagraficaSummary.class);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    protected void map(DataVacciniAnagraficaSummary anagraficaVacciniSummary, int i, String value) {

        final FIELD[] fields = FIELD.values();
        switch (fields[i]) {
            case ULTIMO_AGGIORNAMENTO:
                try {
                    Calendar dateCalendar = Calendar.getInstance();
                    dateCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                    anagraficaVacciniSummary.setUltimo_aggiornamento(dateCalendar.getTime());

                    Calendar now = Calendar.getInstance();
                    now.setTimeInMillis(System.currentTimeMillis());
                    anagraficaVacciniSummary.setUltimo_aggiornamento_interno(now.getTime());
                } catch (ParseException e) {
                    logger.error("Date format error", e);
                }
                break;
            case FASCIA_ANAGRAFICA:
                anagraficaVacciniSummary.setFascia_anagrafica(value);
                break;
            case TOTALE:
                anagraficaVacciniSummary.setTotale(FileUtils.convertStringToInteger(value));
                break;
            case SESSO_MASCHILE:
                anagraficaVacciniSummary.setSesso_maschile(FileUtils.convertStringToInteger(value));
                break;
            case SESSO_FEMMINILE:
                anagraficaVacciniSummary.setSesso_femminile(FileUtils.convertStringToInteger(value));
                break;
            case PRIMA_DOSE:
                anagraficaVacciniSummary.setPrima_dose(FileUtils.convertStringToInteger(value));
                break;
            case SECONDA_DOSE:
                anagraficaVacciniSummary.setSeconda_dose(FileUtils.convertStringToInteger(value));
                break;
            case PREGRESSA_INFEZIONE:
                anagraficaVacciniSummary.setPregressa_infezione(FileUtils.convertStringToInteger(value));
                break;
            case DOSE_AGGIUNTIVA:
                anagraficaVacciniSummary.setDose_aggiuntiva(FileUtils.convertStringToInteger(value));
                break;
            case DOSE_BOOSTER:
                anagraficaVacciniSummary.setDose_booster(FileUtils.convertStringToInteger(value));
                break;
            /*case CATEGORIA_OSS:
                anagraficaVacciniSummary
                        .setCategoria_operatori_sanitari_sociosanitari(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_OVER80:
                anagraficaVacciniSummary.setCategoria_over80(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_PERSONALE_NON_SANITARIO:
                anagraficaVacciniSummary.setCategoria_personale_non_sanitario(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_RSA:
                anagraficaVacciniSummary.setCategoria_ospiti_rsa(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_FORZE_ARMATE:
                anagraficaVacciniSummary.setCategoria_forze_armate(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_PERSONALE_SCOLASTICO:
                anagraficaVacciniSummary.setCategoria_personale_scolastico(FileUtils.convertStringToInteger(value));
                break;
            case CATEGORIA_ALTRO:
                anagraficaVacciniSummary.setCategoria_altro(FileUtils.convertStringToInteger(value));
                break;
                */
            default:
                logger.debug("Unexpected value: {}", value);
        }
    }

    @Override
    protected DataVacciniAnagraficaSummary getInstance() {
        return new DataVacciniAnagraficaSummary();
    }

}

package it.carmelolagamba.mongo.utils.jaxrs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateDeserializer extends StdDeserializer<LocalDate> {
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter dateFormat;

    public CustomLocalDateDeserializer() {
        super(LocalDate.class);

        this.dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public CustomLocalDateDeserializer(DateTimeFormatter dateFormat) {
        super(LocalDate.class);

        this.dateFormat = dateFormat;
    }

    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDate.parse(p.getText().trim(), dateFormat);
    }
}
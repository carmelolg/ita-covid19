package it.carmelolagamba.mongo.utils.jaxrs;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter dateFormat;

    public CustomLocalDateTimeDeserializer() {
        super(LocalDateTime.class);

        this.dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    }

    public CustomLocalDateTimeDeserializer(DateTimeFormatter dateFormat) {
        super(LocalDateTime.class);

        this.dateFormat = dateFormat;
    }

    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return LocalDateTime.parse(p.getText().trim(), dateFormat);
    }
}
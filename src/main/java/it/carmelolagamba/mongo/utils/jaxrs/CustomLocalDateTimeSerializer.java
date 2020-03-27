package it.carmelolagamba.mongo.utils.jaxrs;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> implements Serializable {
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter dateFormat;

    public CustomLocalDateTimeSerializer() {
    	super(LocalDateTime.class);

        this.dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        
    }

    public CustomLocalDateTimeSerializer(DateTimeFormatter dateFormat) {
        super(LocalDateTime.class);

        this.dateFormat = dateFormat;
    }

    @Override
    public void serialize(LocalDateTime instant, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(instant.format(dateFormat));
    }
}
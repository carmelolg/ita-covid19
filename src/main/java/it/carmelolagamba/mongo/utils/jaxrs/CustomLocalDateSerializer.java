package it.carmelolagamba.mongo.utils.jaxrs;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomLocalDateSerializer extends StdSerializer<LocalDate> {
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter dateFormat;

    public CustomLocalDateSerializer() {
    	super(LocalDate.class);

        this.dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
    }

    public CustomLocalDateSerializer(DateTimeFormatter dateFormat) {
        super(LocalDate.class);

        this.dateFormat = dateFormat;
    }

    @Override
    public void serialize(LocalDate instant, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(instant.format(dateFormat));
    }
}
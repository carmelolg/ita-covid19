package it.carmelolagamba.mongo.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.carmelolagamba.mongo.utils.jaxrs.CustomLocalDateDeserializer;
import it.carmelolagamba.mongo.utils.jaxrs.CustomLocalDateSerializer;
import it.carmelolagamba.mongo.utils.jaxrs.CustomLocalDateTimeDeserializer;
import it.carmelolagamba.mongo.utils.jaxrs.CustomLocalDateTimeSerializer;

@Component
public class MapperUtils {

	private ObjectMapper objectMapper;
	private Gson gsonMapper;

	private void generateJacksonObjectMapper() {
		objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
				.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.enable(SerializationFeature.INDENT_OUTPUT);

		SimpleModule moduleLD = new SimpleModule();
		moduleLD.addDeserializer(LocalDate.class, new CustomLocalDateDeserializer());
		moduleLD.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
		objectMapper.registerModule(moduleLD);

		SimpleModule moduleLDT = new SimpleModule();
		moduleLDT.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
		moduleLDT.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
		objectMapper.registerModule(moduleLDT);
	}

	private void generateGsonMapper() {
		gsonMapper = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
	}

	public Gson getGsonInstance() {
		if (gsonMapper == null)
			generateGsonMapper();

		return gsonMapper;
	}

	public ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			generateJacksonObjectMapper();
		}
		return objectMapper;
	}

}

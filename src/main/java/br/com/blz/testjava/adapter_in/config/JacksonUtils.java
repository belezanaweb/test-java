package br.com.blz.testjava.adapter_in.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Paths;


public class JacksonUtils {

    private static Logger LOG = LoggerFactory
            .getLogger(JacksonUtils.class);

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper()
                .registerModule(new SimpleModule())
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JacksonUtils() {

    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }


    public static String converteObjetoParaString(Object object) {
        try {
            return  OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOG.error("Objeto não serializavel para json :", e.getMessage());
            throw new IllegalArgumentException("Objeto não serializavel para json");
        }
    }

    public static <T> T converteStringParaObjeto(String json, Class<T> clazz){
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            LOG.error("Erro ao converter String em Objeto do tipo {}, printStack: {} ", clazz, e.getMessage());
            throw new IllegalArgumentException("Erro ao converter String em Objeto");
        }
    }

    public static void converteObjetoParaFile(String resoucePathsName, Object object) {
        try {
            OBJECT_MAPPER.writeValue(Paths.get(resoucePathsName).toFile(), object);
        } catch (IOException e) {
            LOG.error("Erro ao salvar arquivo :", e.getMessage());
            throw new IllegalArgumentException("Erro ao salvar arquivo");
        }
    }
}


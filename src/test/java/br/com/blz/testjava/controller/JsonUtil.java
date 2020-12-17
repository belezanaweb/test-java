package br.com.blz.testjava.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonUtil {

  private final ObjectMapper mapper;

  public String toJson(Object o) {

    try {
      return mapper.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
  }

  public <T> T fromJson(String json, Class<T> clazz) {

    if (json == null) {
      return null;
    }

    try {
      return mapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public ObjectMapper getMapper() {
    return mapper;
  }
}

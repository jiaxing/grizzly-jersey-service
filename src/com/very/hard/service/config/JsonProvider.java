package com.very.hard.service.config;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class JsonProvider implements ContextResolver<ObjectMapper> {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
    //MAPPER.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
  }

  public JsonProvider() {
  }

  @Override
  public ObjectMapper getContext(final Class<?> type) {
    return MAPPER;
  }
}

package com.codingwithsabbah.redisolar.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.HashMap;
import java.util.Map;

public class JacksonObjectToMapConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static Map<String, String> convertToMap(Object object) {
        if (object == null) {
            return new HashMap<>();
        }
        return objectMapper.convertValue(object, Map.class);
    }


    public static <T> T convert(Map<Object, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

}

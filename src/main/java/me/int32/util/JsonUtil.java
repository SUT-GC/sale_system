package me.int32.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

        SimpleModule module = new SimpleModule();

        mapper.registerModule(module);
    }

    @SuppressWarnings("unchecked")
    private static <T> T read(String json, JavaType javaType) {
        try {
            return json != null ? mapper.readValue(json, javaType) : null;
        } catch (IOException ex) {
            return null;
        }
    }

    public static <T> T read(String json, Class<T> type) {
        return read(json, mapper.constructType(type));
    }

    private static <T> T convert(Object fromJson, JavaType javaType) {
        try {
            return fromJson != null ? mapper.convertValue(fromJson, javaType) : null;
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static <T> T convert(Object fromJson, Class<T> type) {
        return convert(fromJson, mapper.constructType(type));
    }

    public static String writeAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(String.format("Fail to convert Object [%s] to json string", object.getClass().getName()), ex);
        }
    }

    public static boolean isJSONValid(String json) {
        try {
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

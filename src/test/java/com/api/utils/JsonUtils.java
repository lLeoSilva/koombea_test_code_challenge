package com.api.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Reads a JSON file and converts it to a list of maps.
     *
     * @param filePath The path to the JSON file.
     * @return A list of maps representing the JSON data.
     */
    public static List<Map<String, Object>> readJsonAsList(String filePath) {
        try (InputStream inputStream = JsonUtils.class.getResourceAsStream(filePath)) {
            return objectMapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
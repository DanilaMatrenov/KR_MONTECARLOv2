package ru.university.montecarlo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;
import java.io.InputStream;

public class JsonConfigLoader {

    private final ObjectMapper objectMapper;

    public JsonConfigLoader() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );
    }

    public AppConfig loadDefault() throws IOException {
        return loadFromResource("config.json");
    }

    public AppConfig loadFromResource(String resourceName) throws IOException {
        validatePath(resourceName);

        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourceName);
        }

        return objectMapper.readValue(inputStream, AppConfig.class);
    }

    private void validatePath(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Path must not be null or empty");
        }
    }
}

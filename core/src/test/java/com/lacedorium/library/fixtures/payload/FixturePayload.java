package com.lacedorium.library.fixtures.payload;

import com.lacedorium.library.fixtures.Overrider;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

public class FixturePayload extends Overrider {
    public Map<String, Object> load(String name) {
        String resourceName = String.format("/fixtures/payload/%s.json", name);

        try (InputStream in = FixturePayload.class.getResourceAsStream(resourceName)) {
            if (in == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> base = mapper.readValue(in, new TypeReference<Map<String, Object>>() {});
            HashMap<String, Object> result = new HashMap<>(base);

            if (hasOverrides()) {
                result.putAll(overrides);
            }

            return result;
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading JSON: " + resourceName, e);
        }
    }
}

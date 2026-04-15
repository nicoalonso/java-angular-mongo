package com.lacedorium.library.fixtures.payload;

import com.lacedorium.library.fixtures.Maker;

import java.util.Map;

public class FixtureBuilder<T> extends FixturePayload {
    private final Class<T> clazz;
    private final String resourceName;

    public FixtureBuilder(Class<T> clazz, String resourceName) {
        this.clazz = clazz;
        this.resourceName = resourceName;
    }

    @Override
    public FixtureBuilder<T> with(String key, Object value) {
        super.with(key, value);
        return this;
    }

    public T build() {
        T object = Maker.make(clazz, load(resourceName));
        clearOverrides();

        return object;
    }

    public T buildFrom(Map<String, Object> data) {
        return Maker.make(clazz, data);
    }
}

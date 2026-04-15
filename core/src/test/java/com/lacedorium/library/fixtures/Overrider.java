package com.lacedorium.library.fixtures;

import java.util.HashMap;

public class Overrider {
    protected HashMap<String, Object> overrides;

    public Overrider() {
        overrides = null;
    }

    public Overrider with(String key, Object value) {
        if (overrides == null) {
            overrides = new HashMap<>();
        }
        overrides.put(key, value);
        return this;
    }

    public void clearOverrides() {
        this.overrides = null;
    }

    public boolean hasOverrides() {
        return overrides != null && !overrides.isEmpty();
    }
}

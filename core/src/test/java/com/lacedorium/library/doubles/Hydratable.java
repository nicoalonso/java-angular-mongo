package com.lacedorium.library.doubles;

import java.lang.reflect.Field;
import java.util.Map;

public class Hydratable {
    public static void hydrate(Object object, Map<String, ?> data) {
        if (object == null || data == null || data.isEmpty()) {
            return;
        }

        for (Map.Entry<String, ?> entry : data.entrySet()) {
            hydrateProperty(object, entry.getKey(), entry.getValue());
        }
    }

    public static void hydrateProperty(Object object, String property, Object value) {
        if (object == null || property == null || property.isBlank()) {
            return;
        }

        try {
            Field field = findField(object.getClass(), property);

            if (field == null) {
                return;
            }

            field.setAccessible(true);
            field.set(object, value);

        } catch (IllegalAccessException | IllegalArgumentException ignored) {
            // Do nothing
        }
    }

    private static Field findField(Class<?> type, String fieldName) {
        Class<?> current = type;

        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }

        return null;
    }
}
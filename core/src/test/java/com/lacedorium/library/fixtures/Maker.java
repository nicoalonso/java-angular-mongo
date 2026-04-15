package com.lacedorium.library.fixtures;

import org.jspecify.annotations.NonNull;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class Maker {
    public static <U> @NonNull U make(Class<U> targetClass, Map<String, Object> data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registeredModules();

            if (targetClass.isRecord()) {
                var components = targetClass.getRecordComponents();
                Class<?>[] paramTypes = new Class<?>[components.length];
                Object[] args = new Object[components.length];

                for (int i = 0; i < components.length; i++) {
                    var c = components[i];
                    paramTypes[i] = c.getType();
                    Object raw = data.get(c.getName());
                    args[i] = convertValue(mapper, raw, c.getGenericType());
                }

                var ctor = targetClass.getDeclaredConstructor(paramTypes);
                ctor.setAccessible(true);
                return ctor.newInstance(args);
            }

            // Search is not args constructor
            var ctor = getConstructor(targetClass, data.size());
            ctor.setAccessible(true);

            var params = ctor.getParameters();
            Object[] args = new Object[params.length];

            for (int i = 0; i < params.length; i++) {
                var p = params[i];
                Object raw = data.get(p.getName()); // require -parameters for normal class
                args[i] = convertValue(mapper, raw, p.getParameterizedType());
            }

            @SuppressWarnings("unchecked")
            U instance = (U) ctor.newInstance(args);
            return instance;

        } catch (Exception e) {
            throw new IllegalStateException("Cannot create instance of " + targetClass.getName(), e);
        }
    }

    private static Constructor<?> getConstructor(Class<?> targetClass, long paramSize) {
        for (var ctor : targetClass.getDeclaredConstructors()) {
            if (ctor.getParameterCount() == paramSize) {
                return ctor;
            }
        }

        return targetClass.getDeclaredConstructors()[0];
    }

    private static Object convertValue(ObjectMapper mapper, Object raw, Type targetType) {
        if (raw == null) {
            return null;
        }

        if (targetType instanceof ParameterizedType pt) {
            // Supports List<T>, Map<K,V>, etc.
            var javaType = mapper.getTypeFactory().constructType(pt);
            return mapper.convertValue(raw, javaType);
        }

        if (targetType instanceof Class<?> targetClass) {
            // if the raw value is already of the target type, return as is
            if (targetClass.isInstance(raw)) {
                return raw;
            }

            // Nested object in Map form -> recursively build if record
            if (targetClass.isRecord() && raw instanceof Map<?, ?> nested) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) nested;
                return make(targetClass, nestedMap);
            }

            // General conversion (String->LocalDate, Number->Integer, etc., according to registered modules)
            return mapper.convertValue(raw, targetClass);
        }

        // Last resource
        var javaType = mapper.getTypeFactory().constructType(targetType);
        return mapper.convertValue(raw, javaType);
    }
}

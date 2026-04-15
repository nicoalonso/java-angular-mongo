package com.lacedorium.library.infrastructure.persistence.mongo.repository;

import com.lacedorium.library.infrastructure.persistence.mongo.repository.exception.IlegalMapException;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class DomainEntityMapping<T, U> {
    private final Class<T> domainClass;
    private final Class<U> entityClass;

    public DomainEntityMapping(Class<T> domainClass, Class<U> entityClass) {
        this.domainClass = domainClass;
        this.entityClass = entityClass;
    }

    public Optional<T> toDomain(U entity) {
        try {
            if (null == entity) {
                return Optional.empty();
            }

            T domain = instantiate(domainClass);
            copyFieldsByName(entity, domain);
            return Optional.of(domain);

        } catch (Exception e) {
            throw new IlegalMapException(entityClass, domainClass, e);
        }
    }

    public Optional<U> toEntity(T domain) {
        try {
            if (domain == null) {
                return Optional.empty();
            }

            U entity = instantiate(entityClass);
            copyFieldsByName(domain, entity);
            return Optional.of(entity);

        } catch (Exception e) {
            throw new IlegalMapException(domainClass, entityClass, e);
        }
    }

    private static <X> @NonNull X instantiate(@NonNull Class<X> type) throws ReflectiveOperationException {
        var ctor = type.getDeclaredConstructor();
        ctor.setAccessible(true);
        return ctor.newInstance();
    }

    private static void copyFieldsByName(@NonNull Object source, @NonNull Object target) throws ReflectiveOperationException {
        Map<String, Field> sourceFields = getAllFieldsByName(source.getClass());

        for (Field targetField : getAllFields(target.getClass())) {
            if (Modifier.isStatic(targetField.getModifiers()) || Modifier.isFinal(targetField.getModifiers())) {
                continue;
            }

            Field sourceField = sourceFields.get(targetField.getName());
            if (sourceField == null) {
                continue;
            }

            sourceField.setAccessible(true);
            Object value = sourceField.get(source);

            targetField.setAccessible(true);

            if (value == null) {
                if (!targetField.getType().isPrimitive()) {
                    targetField.set(target, null);
                }
                continue;
            }

            if (isAssignable(targetField.getType(), value.getClass())) {
                targetField.set(target, value);
                continue;
            }

            if (isEmbeddable(targetField.getType(), value.getClass())) {
                Object embed = instantiate(targetField.getType());
                copyFieldsByName(value, embed);
                targetField.set(target, embed);
                continue;
            }

            if (targetField.getType().isEnum() || value.getClass().isEnum()) {
                if (targetField.getType().isEnum() && value.getClass() == String.class) {
                    @SuppressWarnings({"unchecked", "rawtypes"})
                    Object enumValue = Enum.valueOf((Class<? extends Enum>) targetField.getType(), value.toString().toUpperCase());
                    targetField.set(target, enumValue);
                } else if (value.getClass().isEnum() && targetField.getType() == String.class) {
                    targetField.set(target, value.toString());
                }
            }
        }
    }

    private static boolean isAssignable(@NonNull Class<?> targetType, Class<?> valueType) {
        if (targetType.isAssignableFrom(valueType)) {
            return true;
        }

        // basic support primitive-wrapper
        if (targetType.isPrimitive()) {
            return (targetType == int.class && valueType == Integer.class)
                    || (targetType == long.class && valueType == Long.class)
                    || (targetType == double.class && valueType == Double.class)
                    || (targetType == float.class && valueType == Float.class)
                    || (targetType == boolean.class && valueType == Boolean.class)
                    || (targetType == byte.class && valueType == Byte.class)
                    || (targetType == short.class && valueType == Short.class)
                    || (targetType == char.class && valueType == Character.class);
        }

        return false;
    }

    private static boolean isEmbeddable(@NonNull Class<?> targetType, @NonNull Class<?> valueType) {
        if (!targetType.getName().contains("embed") && !valueType.getName().contains("embed")) {
            return false;
        }

        String targetTypeName = List.of(targetType.getName().split("\\.")).getLast();
        String valueTypeName = List.of(valueType.getName().split("\\.")).getLast();
        int length = Math.min(targetTypeName.length(), valueTypeName.length());

        return targetTypeName.substring(0, length).equals(valueTypeName.substring(0, length));
    }

    private static @NonNull Map<String, Field> getAllFieldsByName(Class<?> type) {
        Map<String, Field> fields = new HashMap<>();
        for (Field f : getAllFields(type)) {
            if (!fields.containsKey(f.getName())) {
                fields.put(f.getName(), f);
            }
        }
        return fields;
    }

    private static @NonNull Iterable<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        Class<?> current = type;
        while (current != null && current != Object.class) {
            Field[] declared = current.getDeclaredFields();
            Collections.addAll(fields, declared);
            current = current.getSuperclass();
        }
        return fields;
    }
}

package com.lacedorium.library.fixtures.mothers.base;

import com.lacedorium.library.fixtures.Maker;
import com.lacedorium.library.fixtures.Overrider;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class BaseMother<T> extends Overrider {
    Class<T> clazz;
    Map<String, Object> base;

    public BaseMother(Class<T> clazz, Map<String, Object> base) {
        super();
        this.clazz = clazz;
        this.base = base;
    }

    protected record MappingResult(Object value, MotherMapping mapping, Supplier<?> supplier) {}

    protected record TransformToDate(String value) {}

    protected static TransformToDate date(String value) {
        return new TransformToDate(value);
    }

    protected static <T> Supplier<T> lazy(Supplier<T> supplier) {
        return supplier;
    }

    @Override
    public BaseMother<T> with(String key, Object value) {
        super.with(key, value);
        return this;
    }

    public T build() {
        HashMap<String, Object> values = new HashMap<>(base);

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (hasOverrides()
                    && overrides.containsKey(entry.getKey())
                    && overrides.get(entry.getKey()) != null
            ) {
                values.put(entry.getKey(), overrides.get(entry.getKey()));
                continue;
            }

            MappingResult mapping = getMapping(entry.getValue());
            values.put(entry.getKey(), applyMapping(mapping));
        }

        clearOverrides();

        return Maker.make(clazz, values);
    }

    protected @NonNull MappingResult getMapping(Object value) {
        if (value instanceof Supplier<?> s) {
            return new MappingResult(null, MotherMapping.MOTHER, s);
        }

        if (value instanceof TransformToDate(String dateValue)) {
            return new MappingResult(dateValue, MotherMapping.DATE, null);
        }

        return new MappingResult(value, MotherMapping.NONE, null);
    }

    protected Object applyMapping(@NonNull MappingResult mapping) {
        if (mapping.mapping() == MotherMapping.MOTHER) {
            return ((BaseMother<?>) mapping.supplier().get()).build();
        }

        if (mapping.mapping() == MotherMapping.DATE && mapping.value() instanceof String s) {
            try {
                LocalDate ld = LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
                return ld.atStartOfDay();

            } catch (DateTimeParseException e) {
                return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        }

        return mapping.value();
    }
}

package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@Getter
public class FieldMap {
    private final List<Field> fields;

    public FieldMap(@NonNull List<Field> fields) {
        this.fields = fields;
    }

    public @NonNull Boolean hasField(String alias) {
        return fields.stream().anyMatch(field -> field.getAlias().equals(alias));
    }

    public @NonNull Boolean canSelect(@NonNull FieldBase field) {
        Optional<Field> fieldMap = getField(field);
        return fieldMap
                .map(f -> f.getOptions().canSelect())
                .orElse(false);
    }

    public @NonNull Boolean canFilter(@NonNull FieldBase field) {
        Optional<Field> fieldMap = getField(field);
        return fieldMap
                .map(f -> f.getOptions().canFilter())
                .orElse(false);
    }

    public @NonNull Boolean canSort(@NonNull FieldBase field) {
        Optional<Field> fieldMap = getField(field);
        return fieldMap
                .map(f -> f.getOptions().canSort())
                .orElse(false);
    }

    public Optional<Field> getField(@NonNull FieldBase field) {
        Optional<Field> found = fields.stream()
                .filter(f -> f.getAlias().equals(field.getAlias()))
                .findFirst();

        found.ifPresent(field::mapping);
        return found;
    }
}

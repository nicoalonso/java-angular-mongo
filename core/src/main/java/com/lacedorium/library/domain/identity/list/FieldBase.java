package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class FieldBase {
    private String name;
    private final String alias;

    public FieldBase(String name) {
        this.name = name;
        this.alias = name;
    }

    public void mapping(@NonNull Field field) {
        this.name = field.getFieldName();
    }

    public @NonNull Boolean is(String alias) {
        return this.alias.equals(alias);
    }
}

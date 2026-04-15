package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

@Getter
public class Field {
    private final String alias;
    private String fieldName;
    private FilterType type;
    private ValueKind kind;
    private final FieldOptions options;

    public Field(String alias) {
        this.alias = alias;
        this.fieldName = alias;
        this.type = FilterType.WILDCARD;
        this.kind = ValueKind.STRING;
        this.options = new FieldOptions();
    }

    public Field(String alias, Object @NonNull ... args) {
        this.alias = alias;
        this.fieldName = alias;
        this.type = FilterType.WILDCARD;
        this.kind = ValueKind.STRING;
        this.options = new FieldOptions();

        for (Object arg : args) {
            if (arg instanceof String s) {
                this.fieldName = s;
            } else if (arg instanceof FilterType t) {
                this.type = t;
            } else if (arg instanceof ValueKind k) {
                this.kind = k;
            } else if (arg instanceof FieldOption option) {
                this.options.add(option);
            }
        }
    }
}

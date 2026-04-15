package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
public class FilterField extends FieldBase {
    private final String raw;
    private FilterType type;
    private ValueKind kind;
    private Object value;

    public FilterField(String alias, String raw) {
        super(alias);

        this.raw = raw;
        this.type = FilterType.WILDCARD;
        this.kind = ValueKind.STRING;
        this.value = raw;
    }

    public FilterField(String alias, String raw, FilterType type) {
        super(alias);

        this.raw = raw;
        this.type = type;
        this.kind = ValueKind.STRING;
        this.value = raw;
    }

    public void changeValue(Object newValue, Object @NonNull ... args) {
        this.value = newValue;

        for (Object arg : args) {
            if (arg instanceof FilterType t) {
                this.type = t;
            } else if (arg instanceof ValueKind k) {
                this.kind = k;
            }
        }
    }

    public void range(FilterRangeInterval interval, String value) {
        if (interval == FilterRangeInterval.EMPTY) {
            return;
        }

        if (isRange()) {
            if (interval == FilterRangeInterval.FROM) {
                ((FilterRange) this.value).modify(value, null);
            } else if (interval == FilterRangeInterval.TO) {
                ((FilterRange) this.value).modify(null, value);
            }
            return;
        }

        if (interval == FilterRangeInterval.FROM) {
            this.value = new FilterRange(value, null);
        } else if (interval == FilterRangeInterval.TO) {
            this.value = new FilterRange(null, value);
        }
    }

    @Override
    public void mapping(@NonNull Field field) {
        super.mapping(field);

        type = field.getType();
        kind = field.getKind();
        parseValue();
    }

    private void parseValue() {
        if (type.isValueList()) {
            parseList();
            return;
        }

        if (type == FilterType.RANGE) {
            parseRange();
            return;
        }

        value = kind.parse(raw);
    }

    private void parseList() {
        List<String> list = ValueKind.parseList(raw);

        value = switch (kind) {
            case INTEGER -> list.stream().map(ValueKind::parseInteger).toList();
            case FLOAT -> list.stream().map(ValueKind::parseFloat).toList();
            case BOOLEAN -> list.stream().map(ValueKind::parseBool).toList();
            case DATE -> list.stream()
                    .map(ValueKind::parseDate)
                    .filter(Objects::nonNull)
                    .toList();
            default -> list;
        };
    }

    private void parseRange() {
        if (!isRange()) {
            value = new FilterRange(raw, null);
        }

        ((FilterRange) value).parse(kind);
    }

    public @NonNull Boolean hasValue() {
        if (value == null) {
            return false;
        }

        if (isRange()) {
            return ((FilterRange) value).hasValue();
        }

        return (value instanceof Boolean)
                || (value instanceof Number)
                || (value instanceof LocalDateTime)
                || (value instanceof String && !((String) value).isBlank())
                || (value instanceof List<?> && !((List<?>) value).isEmpty());
    }

    public @NonNull Boolean isRange() {
        return value instanceof FilterRange;
    }

    public @NonNull Boolean isList() {
        return value instanceof List<?>;
    }
}

package com.lacedorium.library.domain.identity.list;

import lombok.NonNull;

public enum FilterType {
    WILDCARD,
    MATCH,
    FUZZY,
    RANGE,
    IN,
    ALL,
    EXISTS;

    public @NonNull Boolean isValueList() {
        return this == IN || this == ALL;
    }
}

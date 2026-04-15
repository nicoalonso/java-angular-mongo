package com.lacedorium.library.domain.identity.list;

import lombok.NonNull;

public enum FilterRangeInterval {
    EMPTY,
    FROM,
    TO;

    public record CheckResult(String name, FilterRangeInterval interval) {}

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static @NonNull CheckResult check(@NonNull String name) {
        FilterRangeInterval interval = EMPTY;
        int length = 0;

        if (name.startsWith("from")) {
            interval = FROM;
            length = 4;
        } else if (name.startsWith("to")) {
            interval = TO;
            length = 2;
        }

        if (length > 0 && name.length() > length) {
            name = name.substring(length);
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }

        return new CheckResult(name, interval);
    }
}

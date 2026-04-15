package com.lacedorium.library.domain.identity.list;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class SortField extends FieldBase {
    private final SortDirection direction;

    public SortField(String name) {
        super(name);

        this.direction = SortDirection.ASC;
    }

    public SortField(String name, SortDirection direction) {
        super(name);
        this.direction = direction;
    }

    public static @NonNull SortField fromString(@NonNull String sortValue) {
        String name = sortValue.trim();
        SortDirection direction = SortDirection.ASC;

        if (name.startsWith("+")) {
            name = name.substring(1);
        }
        if (name.startsWith("-")) {
            name = name.substring(1);
            direction = SortDirection.DESC;
        }

        return new SortField(name, direction);
    }

    public Boolean isAscending() {
        return this.direction == SortDirection.ASC;
    }

    public Boolean isDescending() {
        return this.direction == SortDirection.DESC;
    }
}

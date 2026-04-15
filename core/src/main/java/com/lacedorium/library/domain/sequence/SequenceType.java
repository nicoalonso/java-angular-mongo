package com.lacedorium.library.domain.sequence;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public enum SequenceType {
    MEMBERSHIP,
    SALE,
    BORROW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static @Nullable SequenceType fromString(@NonNull String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public @NonNull String getPrefix() {
        return switch (this) {
            case MEMBERSHIP -> "SN";
            case SALE -> "F-";
            case BORROW -> "P-";
        };
    }
}

package com.lacedorium.library.infrastructure.persistence.mongo.repository.exception;

import org.jspecify.annotations.NonNull;

public class IlegalMapException extends IllegalStateException {
    public IlegalMapException(@NonNull Class<?> from, @NonNull Class<?> to) {
        super("Can not map " + from.getSimpleName() +
                " from " + to.getSimpleName());
    }

    public IlegalMapException(@NonNull Class<?> from, @NonNull Class<?> to, Throwable ex) {
        super("Can not map " + from.getSimpleName() +
                " from " + to.getSimpleName(), ex);
    }
}

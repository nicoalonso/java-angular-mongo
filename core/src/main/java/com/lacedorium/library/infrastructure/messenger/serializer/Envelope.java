package com.lacedorium.library.infrastructure.messenger.serializer;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class Envelope<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String action;
    private final String type;
    private final T payload;

    public Envelope(String action, String type, T payload) {
        this.action = action;
        this.type = type;
        this.payload = payload;
    }
}

package com.lacedorium.library.domain.identity.exception;

public class NameEmptyException extends BadRequestException {
    public NameEmptyException() {
        super("Name is required");
    }
}

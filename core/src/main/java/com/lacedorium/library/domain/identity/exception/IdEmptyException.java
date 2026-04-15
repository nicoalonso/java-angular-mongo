package com.lacedorium.library.domain.identity.exception;

public class IdEmptyException extends BadRequestException {
    public IdEmptyException() {
        super("Id cannot be empty");
    }
}

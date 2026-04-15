package com.lacedorium.library.application.identity.list.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidFilterException extends BadRequestException {
    public InvalidFilterException(String name) {
        super("Invalid filter: " + name);
    }
}

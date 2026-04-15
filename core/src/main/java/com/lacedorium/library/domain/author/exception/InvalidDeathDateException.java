package com.lacedorium.library.domain.author.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidDeathDateException extends BadRequestException {
    public InvalidDeathDateException() {
        super("Death date cannot be in the future.");
    }

    public InvalidDeathDateException(String message) {
        super(message);
    }
}

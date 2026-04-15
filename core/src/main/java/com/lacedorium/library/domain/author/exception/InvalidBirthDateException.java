package com.lacedorium.library.domain.author.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidBirthDateException extends BadRequestException {
    public InvalidBirthDateException() {
        super("Birth date cannot be in the future.");
    }

    public InvalidBirthDateException(String message) {
        super(message);
    }
}

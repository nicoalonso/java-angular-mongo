package com.lacedorium.library.domain.book.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidPublishedDateException extends BadRequestException {
    public InvalidPublishedDateException() {
        super("Published date cannot be in the future.");
    }

    public InvalidPublishedDateException(String message) {
        super(message);
    }
}

package com.lacedorium.library.domain.book.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidIsbnException extends BadRequestException {
    public InvalidIsbnException() {
        super("The ISBN is Invalid");
    }
}

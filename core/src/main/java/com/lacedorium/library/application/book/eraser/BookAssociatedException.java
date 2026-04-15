package com.lacedorium.library.application.book.eraser;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class BookAssociatedException extends BadRequestException {
    public BookAssociatedException(String message) {
        super(message);
    }
}

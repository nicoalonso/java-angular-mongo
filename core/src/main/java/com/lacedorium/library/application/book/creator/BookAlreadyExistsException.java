package com.lacedorium.library.application.book.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class BookAlreadyExistsException extends BadRequestException {
    public BookAlreadyExistsException(String title) {
        super("Book with title '" + title + "' already exists");
    }
}

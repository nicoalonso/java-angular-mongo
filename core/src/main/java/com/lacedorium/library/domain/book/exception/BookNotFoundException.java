package com.lacedorium.library.domain.book.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(String bookId) {
        super("The book with id " + bookId + " was not found.");
    }
}

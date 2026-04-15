package com.lacedorium.library.domain.book.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class TitleEmptyException extends BadRequestException {
    public TitleEmptyException() {
        super("The title cannot be empty");
    }
}

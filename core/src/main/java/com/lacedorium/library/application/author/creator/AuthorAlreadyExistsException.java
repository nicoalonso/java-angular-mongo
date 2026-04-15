package com.lacedorium.library.application.author.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class AuthorAlreadyExistsException extends BadRequestException {
    public AuthorAlreadyExistsException(String name) {
        super("Author with name '" + name + "' already exists");
    }
}

package com.lacedorium.library.domain.author.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public final class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(String authorId) {
        super("The author with id " + authorId + " was not found.");
    }
}

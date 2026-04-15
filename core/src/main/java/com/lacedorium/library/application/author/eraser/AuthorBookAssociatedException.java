package com.lacedorium.library.application.author.eraser;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class AuthorBookAssociatedException extends BadRequestException {
    public AuthorBookAssociatedException(String authorId) {
        super("Author with id '" + authorId + "' is associated with a book and cannot be deleted.");
    }
}

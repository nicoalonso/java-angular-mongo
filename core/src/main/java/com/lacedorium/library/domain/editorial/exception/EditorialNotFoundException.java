package com.lacedorium.library.domain.editorial.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class EditorialNotFoundException extends NotFoundException {
    public EditorialNotFoundException(String editorialId) {
        super("Editorial with id '" + editorialId + "' not found");
    }
}

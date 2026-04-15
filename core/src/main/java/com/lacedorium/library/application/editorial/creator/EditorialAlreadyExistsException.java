package com.lacedorium.library.application.editorial.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class EditorialAlreadyExistsException extends BadRequestException {
    public EditorialAlreadyExistsException(String name) {
        super("Editorial with name '" + name + "' already exists");
    }
}

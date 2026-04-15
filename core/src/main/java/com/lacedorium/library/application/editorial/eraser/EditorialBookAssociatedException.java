package com.lacedorium.library.application.editorial.eraser;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class EditorialBookAssociatedException extends BadRequestException {
    public EditorialBookAssociatedException(String editorialId) {
        super("Editorial with id '" + editorialId + "' is associated with a book and cannot be deleted.");
    }
}

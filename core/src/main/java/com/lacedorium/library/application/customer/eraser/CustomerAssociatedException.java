package com.lacedorium.library.application.customer.eraser;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class CustomerAssociatedException extends BadRequestException {
    public CustomerAssociatedException(String message) {
        super(message);
    }
}

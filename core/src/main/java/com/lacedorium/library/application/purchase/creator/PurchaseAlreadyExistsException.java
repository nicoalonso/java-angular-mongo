package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class PurchaseAlreadyExistsException extends BadRequestException {
    public PurchaseAlreadyExistsException() {
        super("Purchase already exists");
    }
}

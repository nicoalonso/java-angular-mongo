package com.lacedorium.library.domain.purchase.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class PurchaseNotFoundException extends NotFoundException {
    public PurchaseNotFoundException(String purchaseId) {
        super("Purchase with ID " + purchaseId + " not found");
    }
}

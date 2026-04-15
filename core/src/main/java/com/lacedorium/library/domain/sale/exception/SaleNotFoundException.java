package com.lacedorium.library.domain.sale.exception;

import com.lacedorium.library.domain.identity.exception.NotFoundException;

public class SaleNotFoundException extends NotFoundException {
    public SaleNotFoundException(String saleId) {
        super("Sale with ID " + saleId + " not found");
    }
}

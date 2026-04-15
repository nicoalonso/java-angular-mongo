package com.lacedorium.library.application.sale.creator;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class SaleLinesEmptyException extends BadRequestException {
    public SaleLinesEmptyException() {
        super("The sale invoice must have at least one line");
    }
}

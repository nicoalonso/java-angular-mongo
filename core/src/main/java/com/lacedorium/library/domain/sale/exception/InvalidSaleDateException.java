package com.lacedorium.library.domain.sale.exception;

import com.lacedorium.library.domain.identity.exception.BadRequestException;

public class InvalidSaleDateException extends BadRequestException {
    public InvalidSaleDateException() {
        super("Sale date cannot be in the future.");
    }

    public InvalidSaleDateException(String message) {
        super(message);
    }
}

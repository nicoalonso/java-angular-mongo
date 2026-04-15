package com.lacedorium.library.domain.sale.exception;

public class InvalidSaleNumberException extends RuntimeException {
    public InvalidSaleNumberException() {
        super("The sale invoice number is required.");
    }
}

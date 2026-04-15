package com.lacedorium.library.domain.purchase.exception;

public class InvalidPurchaseDateException extends RuntimeException {
    public InvalidPurchaseDateException() {
        super("The purchase date cannot be in the future.");
    }

    public InvalidPurchaseDateException(String message) {
        super(message);
    }
}

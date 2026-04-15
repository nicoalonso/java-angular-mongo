package com.lacedorium.library.domain.purchase.exception;

public class InvalidPurchaseInvoiceNumberException extends RuntimeException {
    public InvalidPurchaseInvoiceNumberException() {
        super("The purchase invoice number is required.");
    }
}

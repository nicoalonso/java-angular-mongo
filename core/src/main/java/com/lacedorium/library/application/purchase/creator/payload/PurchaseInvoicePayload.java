package com.lacedorium.library.application.purchase.creator.payload;

public record PurchaseInvoicePayload(
        String number,
        Double amount,
        Double taxes,
        Double total
) { }

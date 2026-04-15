package com.lacedorium.library.application.purchase.creator.payload;

public record PurchaseLinePayload(
        String lineId,
        String bookId,
        Integer quantity,
        Double unitPrice,
        Double discountPercentage,
        Double total
) { }

package com.lacedorium.library.application.sale.creator.payload;

public record SaleLinePayload (
        String lineId,
        String bookId,
        Integer quantity,
        Double price,
        Double discount,
        Double total
) { }

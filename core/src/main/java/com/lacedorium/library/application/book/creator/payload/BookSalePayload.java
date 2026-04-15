package com.lacedorium.library.application.book.creator.payload;

public record BookSalePayload (
        Boolean saleable,
        Double price,
        Double discount
) { }

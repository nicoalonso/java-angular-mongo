package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.domain.book.BookSale;
import org.jspecify.annotations.NonNull;

public record BookSaleRecord(
        Boolean saleable,
        Double price,
        Double discount
) {
    public static @NonNull BookSaleRecord make(@NonNull BookSale sale) {
        return new BookSaleRecord(
                sale.getSaleable(),
                sale.getPrice(),
                sale.getDiscount()
        );
    }
}

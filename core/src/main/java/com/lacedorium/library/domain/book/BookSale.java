package com.lacedorium.library.domain.book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSale {
    private Boolean saleable;
    private Double price;
    private Double discount;

    public BookSale(Boolean saleable, Double price, Double discount) {
        this.saleable = saleable;
        this.price = price;
        this.discount = discount;
    }
}

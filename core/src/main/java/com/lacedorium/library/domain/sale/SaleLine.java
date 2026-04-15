package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.identity.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class SaleLine extends Identity {
    private String sale;
    private BookDescriptor book;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double total;

    public SaleLine(
            @NonNull Sale sale,
            @NonNull Book book,
            Integer quantity,
            Double price,
            Double discount,
            Double total
    ) {
        super();
        initialize();

        this.sale = sale.getId();
        this.book = book.getDescriptor();
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.total = total;
    }

    public void modify(
            @NonNull Book book,
            Integer quantity,
            Double price,
            Double discount,
            Double total
    ) {
        this.book = book.getDescriptor();
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.total = total;
    }
}

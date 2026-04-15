package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.identity.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class PurchaseLine extends Identity {
    private String purchase;
    private BookDescriptor book;
    private Integer quantity;
    private Double unitPrice;
    private Double discountPercentage;
    private Double total;

    public PurchaseLine(
            @NonNull Purchase purchase,
            @NonNull Book book,
            Integer quantity,
            Double unitPrice,
            Double discountPercentage,
            Double total
    ) {
        super();
        initialize();

        this.purchase = purchase.getId();
        this.book = book.getDescriptor();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountPercentage = discountPercentage;
        this.total = total;
    }

    public void modify(
            @NonNull Book book,
            Integer quantity,
            Double unitPrice,
            Double discountPercentage,
            Double total
    ) {
        this.book = book.getDescriptor();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.discountPercentage = discountPercentage;
        this.total = total;
    }
}

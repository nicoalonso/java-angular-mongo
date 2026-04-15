package com.lacedorium.library.application.sale.creator;

import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.sale.Sale;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class SaleCreatedEvent extends DomainEvent {
    private final Sale sale;
    private final List<BookDescriptor> books;

    public SaleCreatedEvent(@NonNull Sale sale, @NonNull List<BookDescriptor> books) {
        super("sale.created", "sale");

        this.sale = sale;
        this.books = books;
    }
}

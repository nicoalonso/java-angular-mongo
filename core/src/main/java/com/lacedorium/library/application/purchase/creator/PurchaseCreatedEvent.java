package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.purchase.Purchase;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class PurchaseCreatedEvent extends DomainEvent {
    private final Purchase purchase;
    private final List<BookDescriptor> books;

    public PurchaseCreatedEvent(@NonNull Purchase purchase, @NonNull List<BookDescriptor> books) {
        super("purchase.created", "purchase");

        this.purchase = purchase;
        this.books = books;
    }
}

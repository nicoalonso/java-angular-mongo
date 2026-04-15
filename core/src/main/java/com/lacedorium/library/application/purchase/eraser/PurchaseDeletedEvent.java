package com.lacedorium.library.application.purchase.eraser;

import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.purchase.Purchase;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class PurchaseDeletedEvent extends DomainEvent {
    private final Purchase purchase;
    private final List<BookDescriptor> books;

    public PurchaseDeletedEvent(@NonNull Purchase purchase, @NonNull List<BookDescriptor> books) {
        super("purchase.deleted", "purchase");

        this.purchase = purchase;
        this.books = books;
    }
}

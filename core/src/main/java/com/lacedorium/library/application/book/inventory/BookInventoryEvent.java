package com.lacedorium.library.application.book.inventory;

import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.bus.DomainRoute;
import lombok.Getter;

@Getter
public class BookInventoryEvent extends DomainEvent {
    public static final String ACTION = "book.inventory";

    private final BookDescriptor descriptor;

    public BookInventoryEvent(BookDescriptor descriptor) {
        super(ACTION, "book", DomainRoute.LIBRARY);
        this.descriptor = descriptor;
    }
}

package com.lacedorium.library.application.sale.consumer;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.domain.bus.DomainBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleConsume {
    private final DomainBus bus;

    public void dispatch(@NonNull List<BookDescriptor> books) {
        for (BookDescriptor book : books) {
            BookInventoryEvent event = new BookInventoryEvent(book);
            bus.dispatch(event);
        }
    }
}

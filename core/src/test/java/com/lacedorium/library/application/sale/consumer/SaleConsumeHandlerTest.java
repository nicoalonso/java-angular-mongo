package com.lacedorium.library.application.sale.consumer;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.application.sale.creator.SaleCreatedEvent;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.SaleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SaleConsumeHandlerTest {
    private DomainBusStub bus;
    private SaleConsumeHandler handler;

    @BeforeEach
    void setUp() {
        bus = new DomainBusStub();
        SaleConsume consumer = new SaleConsume(bus);
        handler = new SaleConsumeHandler(consumer);
    }

    @Test
    void shouldHandle() {
        Sale sale = SaleMother.johnDoeSale2().build();
        Book book = BookMother.donQuixote().build();

        SaleCreatedEvent event = new SaleCreatedEvent(sale, List.of(book.getDescriptor()));

        handler.handle(event);

        bus.assertDispatch(BookInventoryEvent.class);
    }
}
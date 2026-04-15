package com.lacedorium.library.application.sale.consumer;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.fixtures.mothers.BookMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SaleConsumeTest {
    private DomainBusStub bus;
    private SaleConsume consumer;

    @BeforeEach
    void setUp() {
        bus = new DomainBusStub();
        consumer = new SaleConsume(bus);
    }

    @Test
    void shouldConsume() {
        Book book = BookMother.donQuixote().build();

        consumer.dispatch(List.of(book.getDescriptor()));

        bus.assertDispatch(BookInventoryEvent.class);
    }
}
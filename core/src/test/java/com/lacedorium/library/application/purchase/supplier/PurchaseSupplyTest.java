package com.lacedorium.library.application.purchase.supplier;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.fixtures.mothers.BookMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PurchaseSupplyTest {
    private DomainBusStub bus;
    private PurchaseSupply supplier;

    @BeforeEach
    void setUp() {
        bus = new DomainBusStub();
        supplier = new PurchaseSupply(bus);
    }

    @Test
    void shouldSupply() {
        Book book = BookMother.donQuixote().build();

        supplier.dispatch(List.of(book.getDescriptor()));

        bus.assertDispatch(BookInventoryEvent.class);
    }
}
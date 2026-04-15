package com.lacedorium.library.application.purchase.supplier;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.application.purchase.creator.PurchaseCreatedEvent;
import com.lacedorium.library.application.purchase.eraser.PurchaseDeletedEvent;
import com.lacedorium.library.application.purchase.updater.PurchaseUpdatedEvent;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.PurchaseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PurchaseSupplyHandlerTest {
    private DomainBusStub bus;
    private PurchaseSupplyHandler handler;

    @BeforeEach
    void setUp() {
        bus = new DomainBusStub();
        PurchaseSupply supplier = new PurchaseSupply(bus);
        handler = new PurchaseSupplyHandler(supplier);
    }

    @Test
    void shouldHandleWhenCreated() {
        Purchase purchase = PurchaseMother.amazonInvoice1().build();
        Book book = BookMother.donQuixote().build();

        PurchaseCreatedEvent event = new PurchaseCreatedEvent(purchase, List.of(book.getDescriptor()));

        handler.handleCreated(event);

        bus.assertDispatch(BookInventoryEvent.class);
    }

    @Test
    void shouldHandleWhenUpdated() {
        Purchase purchase = PurchaseMother.amazonInvoice1().build();
        Book book = BookMother.donQuixote().build();

        PurchaseUpdatedEvent event = new PurchaseUpdatedEvent(purchase, List.of(book.getDescriptor()));

        handler.handleUpdated(event);

        bus.assertDispatch(BookInventoryEvent.class);
    }

    @Test
    void shouldHandleWhenDeleted() {
        Purchase purchase = PurchaseMother.amazonInvoice1().build();
        Book book = BookMother.donQuixote().build();

        PurchaseDeletedEvent event = new PurchaseDeletedEvent(purchase, List.of(book.getDescriptor()));

        handler.handleDeleted(event);

        bus.assertDispatch(BookInventoryEvent.class);
    }
}
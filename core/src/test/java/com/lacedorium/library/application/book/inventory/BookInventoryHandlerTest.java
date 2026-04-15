package com.lacedorium.library.application.book.inventory;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookInventoryHandlerTest {
    private BookRepositoryStub repoBook;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private SaleLineRepositoryStub repoSaleLine;
    private BookInventoryHandler handler;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoBook);
        repoSaleLine = new SaleLineRepositoryStub(repoBook);
        BookInventory inventory = new BookInventory(repoBook, repoPurchaseLine, repoSaleLine);
        handler = new BookInventoryHandler(inventory);
    }

    @Test
    void shouldHandleBookInventory() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);
        repoSaleLine.attach(Ref.SaleLineJohnDoe1Line2);

        BookInventoryEvent event = new BookInventoryEvent(book.getDescriptor());

        handler.handle(event);

        assertEquals(1, book.getStock());
        repoBook.assertStored();
    }

    @Test
    void shouldErrorOnHandler() {
        Book book = repoBook.get(Ref.BookRomeoAndJuliet);
        repoBook.error("Error");

        BookInventoryEvent event = new BookInventoryEvent(book.getDescriptor());
        handler.handle(event);

        repoBook.assertNotStored();
    }
}
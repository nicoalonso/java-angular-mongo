package com.lacedorium.library.application.book.inventory;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.PurchaseLineMother;
import com.lacedorium.library.fixtures.mothers.SaleLineMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookInventoryTest {
    private BookRepositoryStub repoBook;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private SaleLineRepositoryStub repoSaleLine;
    private BookInventory inventory;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoBook);
        repoSaleLine = new SaleLineRepositoryStub(repoBook);
        inventory = new BookInventory(repoBook, repoPurchaseLine, repoSaleLine);
    }

    @Test
    void shouldFailWhenBookNotFound() {
        Book book = BookMother.romeoAndJuliet().build();

        assertThrows(
                BookNotFoundException.class,
                () -> inventory.dispatch(book.getDescriptor())
        );
    }

    @Test
    void shouldZeroStockWhenLinesNotFound() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        inventory.dispatch(book.getDescriptor());

        assertEquals(0, book.getStock());
        repoBook.assertStored();
    }

    @Test
    void shouldStockPositiveWhenHasPurchaseLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);

        inventory.dispatch(book.getDescriptor());

        assertEquals(2, book.getStock());
        repoBook.assertStored();
    }

    @Test
    void shouldStockPositiveWhenHasPurchaseAndSaleLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);
        repoSaleLine.attach(Ref.SaleLineJohnDoe1Line2);

        inventory.dispatch(book.getDescriptor());

        assertEquals(1, book.getStock());
        repoBook.assertStored();
    }

    @Test
    void shouldStockZeroWhenHasPurchaseAndSaleLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        PurchaseLine purchaseLine = PurchaseLineMother.amazonLine1()
                .with("book", book)
                .with("quantity", 2)
                .build();
        repoPurchaseLine.manualAttach(purchaseLine);

        SaleLine saleLine = SaleLineMother.johnDoeSale1Line2()
                .with("book", book)
                .with("quantity", 2)
                .build();
        repoSaleLine.manualAttach(saleLine);

        inventory.dispatch(book.getDescriptor());

        assertEquals(0, book.getStock());
        repoBook.assertStored();
    }

    @Test
    void shouldStockZeroWhenHasOnlySaleLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        SaleLine saleLine = SaleLineMother.johnDoeSale1Line2()
                .with("book", book)
                .with("quantity", 2)
                .build();
        repoSaleLine.manualAttach(saleLine);

        inventory.dispatch(book.getDescriptor());

        assertEquals(0, book.getStock());
        repoBook.assertStored();
    }
}
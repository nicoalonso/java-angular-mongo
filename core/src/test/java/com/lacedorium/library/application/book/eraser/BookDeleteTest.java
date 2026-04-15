package com.lacedorium.library.application.book.eraser;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDeleteTest {
    private BookRepositoryStub repoBook;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private SaleLineRepositoryStub repoSaleLine;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BookDelete eraser;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoBook);
        repoSaleLine = new SaleLineRepositoryStub(repoBook);
        repoBorrowLine = new BorrowLineRepositoryStub(repoBook);

        eraser = new BookDelete(repoBook, repoPurchaseLine, repoSaleLine, repoBorrowLine);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                BookNotFoundException.class,
                () -> eraser.dispatch("unknown-id")
        );
    }

    @Test
    void shouldFailWhenHasPurchaseLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);

        assertThrows(
                BookAssociatedException.class,
                () -> eraser.dispatch(book.getId())
        );
    }

    @Test
    void shouldFailWhenHasSaleLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoSaleLine.attach(Ref.SaleLineJohnDoe1Line1);

        assertThrows(
                BookAssociatedException.class,
                () -> eraser.dispatch(book.getId())
        );
    }

    @Test
    void shouldFailWhenHasBorrowLines() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoBorrowLine.attach(Ref.BorrowLineJohnRomeoAndJuliet);

        assertThrows(
                BookAssociatedException.class,
                () -> eraser.dispatch(book.getId())
        );
    }

    @Test
    void shouldRunWhenDelete() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        eraser.dispatch(book.getId());

        repoBook.assertRemoved();
    }
}
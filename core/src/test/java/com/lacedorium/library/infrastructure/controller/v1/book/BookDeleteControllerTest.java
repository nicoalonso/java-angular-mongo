package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.eraser.BookDelete;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDeleteControllerTest {
    private BookRepositoryStub repoBook;
    private BookDeleteController controller;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        PurchaseLineRepositoryStub repoPurchaseLine = new PurchaseLineRepositoryStub(repoBook);
        SaleLineRepositoryStub repoSaleLine = new SaleLineRepositoryStub(repoBook);
        BorrowLineRepositoryStub repoBorrowLine = new BorrowLineRepositoryStub(repoBook);

        BookDelete eraser = new BookDelete(repoBook, repoPurchaseLine, repoSaleLine, repoBorrowLine);
        controller = new BookDeleteController(eraser);
    }

    @Test
    void shouldDeleteBook() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        controller.deleteBook(book.getId());

        String bookId = repoBook.assertRemoved();
        assertEquals(book.getId(), bookId);
    }
}
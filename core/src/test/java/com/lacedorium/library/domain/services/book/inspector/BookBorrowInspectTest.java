package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.mothers.BookMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookBorrowInspectTest {
    private BorrowLineRepositoryStub repoBorrowLine;
    private BookBorrowInspect inspector;
    private Book book;

    @BeforeEach
    void setUp() {
        repoBorrowLine = new BorrowLineRepositoryStub();
        inspector = new BookBorrowInspect(repoBorrowLine);
        book = BookMother.romeoAndJuliet().build();
    }

    @Test
    void shouldFalseWhenNoStock() {
        Boolean result = inspector.available(book);

        assertFalse(result);
    }

    @Test
    void shouldTrueWhenHasStockNotActiveBorrows() {
        book.changeStock(3);

        Boolean result = inspector.available(book);

        assertTrue(result);
    }

    @Test
    void shouldTrueWhenHasStockAndActiveBorrows() {
        repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        book.changeStock(4);

        Boolean result = inspector.available(book);

        assertTrue(result);
    }

    @Test
    void shouldFalseWhenHasStockIsEqualsToActiveBorrows() {
        repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);
        repoBorrowLine.attach(Ref.BorrowLineJohnRomeoAndJuliet);

        book.changeStock(2);

        Boolean result = inspector.available(book);

        assertFalse(result);
    }
}
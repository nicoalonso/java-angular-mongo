package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.BorrowMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowLineTest {
    private Borrow borrow;
    private Book book;

    @BeforeEach
    void setUp() {
        borrow = BorrowMother.johnDoe().build();
        book = BookMother.donQuixote().build();
    }

    @Test
    void shouldRunWhenCreate() {
        BorrowLine borrowLine = new BorrowLine(borrow, book);

        assertEquals(borrow.getId(), borrowLine.getBorrow());
        assertEquals(book.getId(), borrowLine.getBook().getId());
        assertFalse(borrowLine.getReturned());
        assertNull(borrowLine.getReturnedDate());
        assertFalse(borrowLine.getPenalty());
        assertEquals(0.0, borrowLine.getPenaltyAmount());
    }

    @Test
    void shouldRunWhenCheckIn() {
        BorrowLine borrowLine = new BorrowLine(borrow, book);
        borrowLine.checkIn();

        assertTrue(borrowLine.getReturned());
        assertNotNull(borrowLine.getReturnedDate());
    }

    @Test
    void shouldRunWhenPenalize() {
        BorrowLine borrowLine = new BorrowLine(borrow, book);
        borrowLine.penalize(10.0d);

        assertTrue(borrowLine.getPenalty());
        assertEquals(10.0d, borrowLine.getPenaltyAmount());
    }
}

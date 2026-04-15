package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.PurchaseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseLineTest {
    private Purchase purchase;
    private Book book;

    @BeforeEach
    void setUp() {
        purchase = PurchaseMother.amazonInvoice1().build();
        book = BookMother.donQuixote().build();
    }

    @Test
    void shouldRunWhenCreate() {
        PurchaseLine line = new PurchaseLine(purchase, book, 1, 10.0, 0.0, 10.0);

        assertNotNull(line);
        assertEquals(purchase.getId(), line.getPurchase());
        assertEquals(book.getId(), line.getBook().getId());
        assertEquals(1, line.getQuantity());
        assertEquals(10.0, line.getUnitPrice());
        assertEquals(0.0, line.getDiscountPercentage());
        assertEquals(10.0, line.getTotal());
    }

    @Test
    void shouldRunWhenModify() {
        PurchaseLine line = new PurchaseLine(purchase, book, 1, 10.0, 0.0, 10.0);

        Book newBook = BookMother.romeoAndJuliet().build();

        line.modify(newBook, 2, 15.0, 10.0, 27.0);

        assertEquals(newBook.getId(), line.getBook().getId());
        assertEquals(2, line.getQuantity());
        assertEquals(15.0, line.getUnitPrice());
        assertEquals(10.0, line.getDiscountPercentage());
        assertEquals(27.0, line.getTotal());
    }
}

package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.fixtures.mothers.BookMother;
import com.lacedorium.library.fixtures.mothers.SaleMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleLineTest {
    private Sale sale;
    private Book book;

    @BeforeEach
    void setUp() {
        sale = SaleMother.johnDoeSale1().build();
        book = BookMother.donQuixote().build();
    }

    @Test
    void shouldCreate() {
        SaleLine saleLine = new SaleLine(sale, book, 2, 10.0, 5.0, 19.0);

        assertEquals(sale.getId(), saleLine.getSale());
        assertEquals(book.getId(), saleLine.getBook().getId());
        assertEquals(2, saleLine.getQuantity());
        assertEquals(10.0, saleLine.getPrice());
        assertEquals(5.0, saleLine.getDiscount());
        assertEquals(19.0, saleLine.getTotal());
    }

    @Test
    void shouldRunWhenModify() {
        SaleLine saleLine = new SaleLine(sale, book, 2, 10.0, 5.0, 19.0);

        Book newBook = BookMother.romeoAndJuliet().build();

        saleLine.modify(newBook, 3, 15.0, 10.0, 27.0);

        assertEquals(newBook.getId(), saleLine.getBook().getId());
        assertEquals(3, saleLine.getQuantity());
        assertEquals(15.0, saleLine.getPrice());
        assertEquals(10.0, saleLine.getDiscount());
        assertEquals(27.0, saleLine.getTotal());
    }
}
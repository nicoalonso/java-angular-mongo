package com.lacedorium.library.domain.borrow;

import com.lacedorium.library.domain.borrow.exception.InvalidBorrowNumberException;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.fixtures.mothers.CustomerMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BorrowTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = CustomerMother.johnDoe().build();
    }

    @Test
    void shouldFailWhenInvalidNumber() {
        assertThrows(InvalidBorrowNumberException.class, () -> {
            new Borrow(customer, "", 3, "test");
        });
    }

    @Test
    void shouldCreateBorrow() {
        Borrow borrow = new Borrow(customer, "P-00255", 3, "test");

        assertNotNull(borrow);
        assertEquals("P-00255", borrow.getNumber());
        assertEquals(3, borrow.getTotalBooks());
        assertInstanceOf(LocalDateTime.class, borrow.getBorrowDate());
        assertInstanceOf(LocalDateTime.class, borrow.getDueDate());
        assertFalse(borrow.getReturned());
        assertEquals(0, borrow.getTotalReturnedBooks());
        assertNull(borrow.getReturnedDate());
        assertFalse(borrow.getPenalty());
        assertEquals(0.0f, borrow.getPenaltyAmount());
    }

    @Test
    void shouldRunWhenModifyPending() {
        Borrow borrow = new Borrow(customer, "P-00255", 3, "test");
        borrow.modify(2, "test");

        assertEquals(2, borrow.getTotalReturnedBooks());
        assertFalse(borrow.getReturned());
        assertNull(borrow.getReturnedDate());
    }

    @Test
    void shouldRunWhenModifyReturned() {
        Borrow borrow = new Borrow(customer, "P-00255", 3, "test");
        borrow.modify(3, "test");

        assertEquals(3, borrow.getTotalReturnedBooks());
        assertTrue(borrow.getReturned());
        assertNotNull(borrow.getReturnedDate());
    }

    @Test
    void shouldRunWhenPenalize() {
        Borrow borrow = new Borrow(customer, "P-00255", 3, "test");
        borrow.penalize(10.0d);

        assertTrue(borrow.getPenalty());
        assertEquals(10.0d, borrow.getPenaltyAmount());
    }
}

package com.lacedorium.library.domain.sale;

import com.lacedorium.library.domain.sale.exception.InvalidSaleDateException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleInvoiceTest {
    @Test
    void shouldFailWhenInvalidDate() {
        LocalDateTime date = LocalDateTime.now().plusDays(2);

        assertThrows(
                InvalidSaleDateException.class,
                () -> new SaleInvoice(date, 100.0, 10.0, 10.0, 110.0)
        );
    }

    @Test
    void shouldCreate() {
        SaleInvoice invoice= new SaleInvoice(LocalDateTime.now(), 100.0, 10.0, 10.0, 110.0);

        assertNotNull(invoice);
        assertInstanceOf(LocalDateTime.class, invoice.getDate());
        assertEquals(100.0, invoice.getAmount());
        assertEquals(10.0, invoice.getTaxPercentage());
        assertEquals(10.0, invoice.getTaxes());
        assertEquals(110.0, invoice.getTotal());
    }
}
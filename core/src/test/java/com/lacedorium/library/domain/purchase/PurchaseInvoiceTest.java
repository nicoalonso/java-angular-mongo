package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseInvoiceNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseInvoiceTest {
    @Test
    void shouldFailWhenNumberIsEmpty() {
        assertThrows(
                InvalidPurchaseInvoiceNumberException.class,
                () -> new PurchaseInvoice("", 100.0, 10.0, 110.0)
        );
    }

    @Test
    void shouldCreate() {
        PurchaseInvoice purchaseInvoice = new PurchaseInvoice("123456789", 100.0, 10.0, 110.0);

        assertEquals("123456789", purchaseInvoice.getNumber());
        assertEquals(100.0, purchaseInvoice.getAmount());
        assertEquals(10.0, purchaseInvoice.getTaxes());
        assertEquals(110.0, purchaseInvoice.getTotal());
    }
}
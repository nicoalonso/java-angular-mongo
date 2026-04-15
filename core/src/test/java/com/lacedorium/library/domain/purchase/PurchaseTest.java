package com.lacedorium.library.domain.purchase;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseDateException;
import com.lacedorium.library.fixtures.mothers.ProviderMother;
import com.lacedorium.library.fixtures.mothers.PurchaseInvoiceMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    private Provider provider;
    private PurchaseInvoice invoice;

    @BeforeEach
    void setUp() {
        provider = ProviderMother.bestBuy().build();
        invoice = PurchaseInvoiceMother.invoice1().with("number", "1").build();
    }

    @Test
    void shouldFailWhenPurchaseAtIsInFuture() {
        LocalDateTime date = LocalDateTime.now().plusDays(2);

        assertThrows(
                InvalidPurchaseDateException.class,
                () -> new Purchase(provider, date, invoice, "test")
        );
    }

    @Test
    void shouldCreatePurchase() {
        Purchase purchase = new Purchase(provider, LocalDateTime.now(), invoice, "test");

        assertNotNull(purchase);
        assertEquals(provider.getId(), purchase.getProvider().getId());
        assertInstanceOf(LocalDateTime.class, purchase.getPurchasedAt());
        assertEquals(invoice, purchase.getInvoice());
    }

    @Test
    void shouldRunWhenModify() {
        Purchase purchase = new Purchase(provider, LocalDateTime.now(), invoice, "test");

        LocalDateTime newDate = LocalDateTime.now().minusDays(2);
        Provider newProvider = ProviderMother.bestBuy()
                .with("name", "Best Buy 2")
                .build();
        PurchaseInvoice newInvoice = PurchaseInvoiceMother.invoice2()
                .with("number", "2")
                .build();

        purchase.modify(newProvider, newDate, newInvoice, "test");

        assertNotEquals(provider.getId(), purchase.getProvider().getId());
        assertNotEquals(invoice, purchase.getInvoice());
    }
}

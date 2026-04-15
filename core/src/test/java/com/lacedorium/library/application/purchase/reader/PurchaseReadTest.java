package com.lacedorium.library.application.purchase.reader;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.exception.PurchaseNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseReadTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private PurchaseRead reader;

    @BeforeEach
    void setUp() {
        repoPurchase = new PurchaseRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase);
        reader = new PurchaseRead(repoPurchase, repoPurchaseLine);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                PurchaseNotFoundException.class,
                () -> reader.dispatch("not-found")
        );
    }

    @Test
    void shouldReturnPurchaseWhenFound() {
        Purchase purchase = repoPurchase.put(Ref.PurchaseBestBuyInv2);
        repoPurchaseLine.attach(Ref.PurchaseLineBestBuyLine1);

        PurchaseDecorator result = reader.dispatch(purchase.getId());

        assertEquals(purchase, result.purchase());
        assertEquals(1, result.lines().size());
    }
}
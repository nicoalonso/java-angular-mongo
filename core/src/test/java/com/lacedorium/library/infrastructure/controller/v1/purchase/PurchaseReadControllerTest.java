package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.reader.PurchaseRead;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.purchase.PurchaseReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseReadControllerTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private PurchaseReadController controller;

    @BeforeEach
    void setUp() {
        repoPurchase = new PurchaseRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase);
        PurchaseRead reader = new PurchaseRead(repoPurchase, repoPurchaseLine);
        controller = new PurchaseReadController(reader);
    }

    @Test
    void shouldRead() {
        Purchase purchase = repoPurchase.put(Ref.PurchaseBestBuyInv2);
        repoPurchaseLine.attach(Ref.PurchaseLineBestBuyLine1);

        PurchaseReadView result = controller.read(purchase.getId());

        assertNotNull(result);
        assertEquals(purchase.getId(), result.getData().id());
        assertEquals(1, result.getData().lines().size());
    }
}
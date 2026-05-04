package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.eraser.PurchaseDelete;
import com.lacedorium.library.application.purchase.eraser.PurchaseDeletedEvent;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseDeleteControllerTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private DomainBusStub bus;
    private PurchaseDeleteController controller;

    @BeforeEach
    void setup() {
        repoPurchase = new PurchaseRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase);
        bus = new DomainBusStub();
        PurchaseDelete deleter = new PurchaseDelete(repoPurchase, repoPurchaseLine, bus);
        controller = new PurchaseDeleteController(deleter);
    }

    @Test
    void shouldRemoved() {
        Purchase purchase = repoPurchase.put(Ref.PurchaseBestBuyInv2);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine2);

        controller.delete(purchase.getId());

        String purchaseId = repoPurchase.assertRemoved();
        assertEquals(purchase.getId(), purchaseId);
        repoPurchaseLine.assertRemoved();
        bus.assertDispatch(PurchaseDeletedEvent.class);
    }
}
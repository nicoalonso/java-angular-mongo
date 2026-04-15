package com.lacedorium.library.application.purchase.eraser;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.exception.PurchaseNotFoundException;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseDeleteTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private DomainBusStub bus;
    private PurchaseDelete deleter;

    @BeforeEach
    void setup() {
        repoPurchase = new PurchaseRepositoryStub();
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase);
        bus = new DomainBusStub();
        deleter = new PurchaseDelete(repoPurchase, repoPurchaseLine, bus);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                PurchaseNotFoundException.class,
                () -> deleter.dispatch("not-found")
        );
    }

    @Test
    void shouldRunWhenRemoved() {
        Purchase purchase = repoPurchase.put(Ref.PurchaseBestBuyInv2);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine2);

        deleter.dispatch(purchase.getId());

        repoPurchase.assertRemoved();
        repoPurchaseLine.assertRemoved();
        bus.assertDispatch(PurchaseDeletedEvent.class);
    }
}
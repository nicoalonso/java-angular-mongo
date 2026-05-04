package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.creator.PurchaseCreate;
import com.lacedorium.library.application.purchase.creator.PurchaseCreatePayload;
import com.lacedorium.library.application.purchase.creator.PurchaseCreatedEvent;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.purchase.PurchaseCreateView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseCreateControllerTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private ProviderRepositoryStub repoProvider;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private PurchaseCreateController controller;
    private FixtureBuilder<PurchaseCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoPurchase = new PurchaseRepositoryStub(repoProvider);
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase, repoBook);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        bus = new DomainBusStub();

        PurchaseCreate creator = new PurchaseCreate(
                repoPurchase,
                repoPurchaseLine,
                repoProvider,
                repoBook,
                repoUser,
                bus
        );
        controller = new PurchaseCreateController(creator);

        fixture = new FixtureBuilder<>(PurchaseCreatePayload.class, "purchase");
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookRomeoAndJuliet);
        repoProvider.put(Ref.ProviderAmazon);
        PurchaseCreatePayload payload = fixture.build();

        PurchaseCreateView result = controller.create(payload);

        assertEquals(payload.invoice().number(), result.getData().invoice().number());
        repoPurchase.assertStored();
        repoPurchaseLine.assertStored();
        bus.assertDispatch(PurchaseCreatedEvent.class);
    }
}
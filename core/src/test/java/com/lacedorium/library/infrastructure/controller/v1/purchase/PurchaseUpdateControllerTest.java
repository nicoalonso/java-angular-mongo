package com.lacedorium.library.infrastructure.controller.v1.purchase;

import com.lacedorium.library.application.purchase.updater.PurchaseUpdate;
import com.lacedorium.library.application.purchase.updater.PurchaseUpdatePayload;
import com.lacedorium.library.application.purchase.updater.PurchaseUpdatedEvent;
import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PurchaseUpdateControllerTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private ProviderRepositoryStub repoProvider;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private PurchaseUpdateController controller;
    private FixtureBuilder<PurchaseUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoPurchase = new PurchaseRepositoryStub(repoProvider);
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase, repoBook);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        bus = new DomainBusStub();
        PurchaseUpdate updater = new PurchaseUpdate(
                repoPurchase,
                repoPurchaseLine,
                repoBook,
                repoProvider,
                repoUser,
                bus
        );
        controller = new PurchaseUpdateController(updater);

        fixture = new FixtureBuilder<>(PurchaseUpdatePayload.class, "purchase");
    }

    @Test
    void shouldRunWhenUpdate() {
        repoProvider.put(Ref.ProviderAmazon);
        repoBook.put(Ref.BookRomeoAndJuliet);

        Purchase purchase = repoPurchase.put(Ref.PurchaseAmazonInv1);
        PurchaseLine purchaseLine = repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine1);
        repoPurchaseLine.attach(Ref.PurchaseLineAmazonLine2);

        Map<String, Object> data = fixture.load("purchase");
        //noinspection unchecked
        List<HashMap<String, Object>> lines = (List<HashMap<String, Object>>) data.get("lines");
        lines.getFirst().put("lineId", purchaseLine.getId());
        PurchaseUpdatePayload payload = fixture.buildFrom(data);

        controller.update(purchase.getId(), payload);

        repoPurchase.assertStored();
        repoPurchaseLine.assertStored();
        repoPurchaseLine.assertRemoved();
        bus.assertDispatch(PurchaseUpdatedEvent.class);
    }
}
package com.lacedorium.library.application.purchase.updater;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.domain.purchase.exception.PurchaseNotFoundException;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseUpdateTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private ProviderRepositoryStub repoProvider;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private PurchaseUpdate updater;
    private FixtureBuilder<PurchaseUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoPurchase = new PurchaseRepositoryStub(repoProvider);
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase, repoBook);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        bus = new DomainBusStub();
        updater = new PurchaseUpdate(
                repoPurchase,
                repoPurchaseLine,
                repoBook,
                repoProvider,
                repoUser,
                bus
        );

        fixture = new FixtureBuilder<>(PurchaseUpdatePayload.class, "purchase");
    }

    @Test
    void shouldFailWhenNotFound() {
        PurchaseUpdatePayload payload = fixture.build();
        assertThrows(
                PurchaseNotFoundException.class,
                () -> updater.dispatch( "not-found", payload)
        );
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

        updater.dispatch(purchase.getId(), payload);

        repoPurchase.assertStored();
        repoPurchaseLine.assertStored();
        repoPurchaseLine.assertRemoved();
        bus.assertDispatch(PurchaseUpdatedEvent.class);
    }
}
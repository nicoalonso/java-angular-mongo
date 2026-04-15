package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.provider.exception.ProviderNotFoundException;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseCreateTest {
    private PurchaseRepositoryStub repoPurchase;
    private PurchaseLineRepositoryStub repoPurchaseLine;
    private ProviderRepositoryStub repoProvider;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private PurchaseCreate creator;
    private FixtureBuilder<PurchaseCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoProvider = new ProviderRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoPurchase = new PurchaseRepositoryStub(repoProvider);
        repoPurchaseLine = new PurchaseLineRepositoryStub(repoPurchase, repoBook);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        bus = new DomainBusStub();

        creator = new PurchaseCreate(
                repoPurchase,
                repoPurchaseLine,
                repoProvider,
                repoBook,
                repoUser,
                bus
        );

        fixture = new FixtureBuilder<>(PurchaseCreatePayload.class, "purchase");
    }

    @Test
    void shouldFailWhenBookNotFound() {
        repoPurchase.put(Ref.PurchaseAmazonInv1);
        PurchaseCreatePayload payload = fixture.build();

        assertThrows(
                BookNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoPurchase.put(Ref.PurchaseAmazonInv1);
        repoBook.put(Ref.BookRomeoAndJuliet);

        PurchaseCreatePayload payload = fixture.build();

        assertThrows(
                PurchaseAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenProviderNotFound() {
        repoBook.put(Ref.BookRomeoAndJuliet);

        PurchaseCreatePayload payload = fixture.build();

        assertThrows(
                ProviderNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookRomeoAndJuliet);
        repoProvider.put(Ref.ProviderAmazon);

        PurchaseCreatePayload payload = fixture.build();

        creator.dispatch(payload);

        repoPurchase.assertStored();
        repoPurchaseLine.assertStored();
        bus.assertDispatch(PurchaseCreatedEvent.class);
    }
}
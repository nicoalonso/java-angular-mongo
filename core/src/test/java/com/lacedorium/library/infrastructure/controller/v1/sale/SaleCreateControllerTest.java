package com.lacedorium.library.infrastructure.controller.v1.sale;

import com.lacedorium.library.application.sale.creator.SaleCreate;
import com.lacedorium.library.application.sale.creator.SaleCreatePayload;
import com.lacedorium.library.application.sale.creator.SaleCreatedEvent;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.sale.SaleCreateView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleCreateControllerTest {
    private SaleRepositoryStub repoSale;
    private SaleLineRepositoryStub repoSaleLine;
    private CustomerRepositoryStub repoCustomer;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private SaleCreateController controller;
    private FixtureBuilder<SaleCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoSale = new SaleRepositoryStub();
        repoSaleLine = new SaleLineRepositoryStub();
        repoCustomer = new CustomerRepositoryStub();
        repoBook = new BookRepositoryStub();
        bus = new DomainBusStub();

        SequenceNumberRepositoryStub repoSequenceNumber = new SequenceNumberRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        SaleCreate creator = new SaleCreate(
                repoSale,
                repoSaleLine,
                repoCustomer,
                repoBook,
                repoSequenceNumber,
                repoUser,
                bus
        );
        controller = new SaleCreateController(creator);

        fixture = new FixtureBuilder<>(SaleCreatePayload.class, "sale");
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookRomeoAndJuliet);
        repoCustomer.put(Ref.CustomerJohnDoe);
        SaleCreatePayload payload = fixture.build();

        SaleCreateView result = controller.create(payload);

        assertEquals(payload.invoice().total(), result.getData().invoice().total());
        repoSale.assertStored();
        repoSaleLine.assertStored();
        bus.assertDispatch(SaleCreatedEvent.class);
    }
}
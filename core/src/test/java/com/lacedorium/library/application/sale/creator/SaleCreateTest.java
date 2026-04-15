package com.lacedorium.library.application.sale.creator;

import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaleCreateTest {
    private SaleRepositoryStub repoSale;
    private SaleLineRepositoryStub repoSaleLine;
    private CustomerRepositoryStub repoCustomer;
    private BookRepositoryStub repoBook;
    private DomainBusStub bus;
    private SaleCreate creator;
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
        creator = new SaleCreate(
                repoSale,
                repoSaleLine,
                repoCustomer,
                repoBook,
                repoSequenceNumber,
                repoUser,
                bus
        );

        fixture = new FixtureBuilder<>(SaleCreatePayload.class, "sale");
    }

    @Test
    void shouldFailWhenEmptyLines() {
        SaleCreatePayload payload = fixture.with("lines", List.of()).build();

        assertThrows(
                SaleLinesEmptyException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenBookNotFound() {
        SaleCreatePayload payload = fixture.build();

        assertThrows(
                BookNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenCustomerNotFound() {
        repoBook.put(Ref.BookRomeoAndJuliet);

        SaleCreatePayload payload = fixture.build();

        assertThrows(
                CustomerNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookRomeoAndJuliet);
        repoCustomer.put(Ref.CustomerJohnDoe);

        SaleCreatePayload payload = fixture.build();

        Sale sale = creator.dispatch(payload);

        assertNotNull(sale);
        repoSale.assertStored();
        repoSaleLine.assertStored();
        bus.assertDispatch(SaleCreatedEvent.class);
    }
}
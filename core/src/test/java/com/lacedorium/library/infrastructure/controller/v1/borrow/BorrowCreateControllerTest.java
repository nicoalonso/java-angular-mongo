package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.creator.BorrowCreate;
import com.lacedorium.library.application.borrow.creator.BorrowCreatePayload;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.borrow.BorrowCreateView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowCreateControllerTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private CustomerRepositoryStub repoCustomer;
    private BookRepositoryStub repoBook;
    private BorrowCreateController controller;
    private FixtureBuilder<BorrowCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoBorrow = new BorrowRepositoryStub(repoCustomer);
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow, repoBook);
        SequenceNumberRepositoryStub repoSequenceNumber = new SequenceNumberRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();

        BorrowCreate creator = new BorrowCreate(
                repoBorrow,
                repoBorrowLine,
                repoCustomer,
                repoBook,
                repoSequenceNumber,
                repoUser
        );
        controller = new BorrowCreateController(creator);

        fixture = new FixtureBuilder<>(BorrowCreatePayload.class, "borrow-create");
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookDonQuixote);
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);
        BorrowCreatePayload payload = fixture.build();

        BorrowCreateView result = controller.create(payload);

        assertEquals(customer.getId(), result.getData().customer().id());
        repoBorrow.assertStored();
        repoBorrowLine.assertStored();
    }
}
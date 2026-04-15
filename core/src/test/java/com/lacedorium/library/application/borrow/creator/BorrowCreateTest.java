package com.lacedorium.library.application.borrow.creator;

import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.*;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorrowCreateTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private CustomerRepositoryStub repoCustomer;
    private BookRepositoryStub repoBook;
    private BorrowCreate creator;
    private FixtureBuilder<BorrowCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        repoBook = new BookRepositoryStub();
        repoBorrow = new BorrowRepositoryStub(repoCustomer);
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow, repoBook);
        SequenceNumberRepositoryStub repoSequenceNumber = new SequenceNumberRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();

        creator = new BorrowCreate(
                repoBorrow,
                repoBorrowLine,
                repoCustomer,
                repoBook,
                repoSequenceNumber,
                repoUser
        );

        fixture = new FixtureBuilder<>(BorrowCreatePayload.class, "borrow-create");
    }

    @Test
    void shouldFailWhenEmptyLines() {
        BorrowCreatePayload payload = fixture.with("lines", List.of()).build();

        assertThrows(
                BorrowLinesEmptyException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenBookNotFound() {
        BorrowCreatePayload payload = fixture.build();

        assertThrows(
                BookNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenCustomerNotFound() {
        repoBook.put(Ref.BookDonQuixote);
        BorrowCreatePayload payload = fixture.build();

        assertThrows(
                CustomerNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        repoBook.put(Ref.BookDonQuixote);
        repoCustomer.put(Ref.CustomerJohnDoe);
        BorrowCreatePayload payload = fixture.build();

        Borrow borrow = creator.dispatch(payload);

        assertNotNull(borrow);
        repoBorrow.assertStored();
        repoBorrowLine.assertStored();
    }
}

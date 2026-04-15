package com.lacedorium.library.application.customer.eraser;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDeleteTest {
    private CustomerRepositoryStub repoCustomer;
    private SaleRepositoryStub repoSale;
    private BorrowRepositoryStub repoBorrow;
    private CustomerDelete deleter;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        repoSale = new SaleRepositoryStub(repoCustomer);
        repoBorrow = new BorrowRepositoryStub(repoCustomer);
        deleter = new CustomerDelete(repoCustomer, repoSale, repoBorrow);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                CustomerNotFoundException.class,
                () -> deleter.dispatch("not-found")
        );
    }

    @Test
    void shouldFailWhenHasSales() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);
        repoSale.attach(Ref.SaleJohnDoe1);

        assertThrows(
                CustomerAssociatedException.class,
                () -> deleter.dispatch(customer.getId())
        );
    }

    @Test
    void shouldFailWhenHasBorrows() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);
        repoBorrow.attach(Ref.BorrowJohnDoe);

        assertThrows(
                CustomerAssociatedException.class,
                () -> deleter.dispatch(customer.getId())
        );
    }

    @Test
    void shouldRunWhenDelete() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);

        deleter.dispatch(customer.getId());

        repoCustomer.assertRemoved();
    }
}
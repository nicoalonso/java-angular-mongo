package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.eraser.CustomerDelete;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDeleteControllerTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerDeleteController controller;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        SaleRepositoryStub repoSale = new SaleRepositoryStub(repoCustomer);
        BorrowRepositoryStub repoBorrow = new BorrowRepositoryStub(repoCustomer);
        CustomerDelete deleter = new CustomerDelete(repoCustomer, repoSale, repoBorrow);
        controller = new CustomerDeleteController(deleter);
    }

    @Test
    void shouldDeleteCustomer() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);

        controller.deleteCustomer(customer.getId());

        String customerId = repoCustomer.assertRemoved();
        assertEquals(customer.getId(), customerId);
    }
}
package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.reader.CustomerRead;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.customer.CustomerReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerReadControllerTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerReadController controller;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        CustomerRead reader = new CustomerRead(repoCustomer);
        controller = new CustomerReadController(reader);
    }

    @Test
    void shouldRead() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);

        CustomerReadView result = controller.read(customer.getId());

        assertEquals(customer.getId(), result.getData().id());
    }
}
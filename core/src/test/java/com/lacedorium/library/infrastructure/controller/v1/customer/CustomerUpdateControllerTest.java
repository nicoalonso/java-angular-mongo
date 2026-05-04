package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.updater.CustomerUpdate;
import com.lacedorium.library.application.customer.updater.CustomerUpdatePayload;
import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerUpdateControllerTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerUpdateController controller;
    private FixtureBuilder<CustomerUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        CustomerUpdate updater = new CustomerUpdate(repoCustomer, repoUser);
        controller = new CustomerUpdateController(updater);

        fixture = new FixtureBuilder<>(CustomerUpdatePayload.class, "customer-update");
    }

    @Test
    void shouldRunWhenUpdate() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);
        CustomerUpdatePayload payload = fixture.build();

        controller.update(customer.getId(), payload);

        Customer stored = repoCustomer.assertStored();
        assertEquals(customer.getId(), stored.getId());
    }
}
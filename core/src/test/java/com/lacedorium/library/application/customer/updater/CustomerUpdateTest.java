package com.lacedorium.library.application.customer.updater;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerUpdateTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerUpdate updater;
    private FixtureBuilder<CustomerUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        updater = new CustomerUpdate(repoCustomer, repoUser);

        fixture = new FixtureBuilder<>(CustomerUpdatePayload.class, "customer-update");
    }

    @Test
    void shouldFailWhenNotFound() {
        CustomerUpdatePayload payload = fixture.build();

        assertThrows(
                CustomerNotFoundException.class,
                () -> updater.dispatch("not-foud", payload)
        );
    }

    @Test
    void shouldRunWhenUpdate() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);
        CustomerUpdatePayload payload = fixture.build();

        updater.dispatch(customer.getId(), payload);

        repoCustomer.assertStored();
    }
}
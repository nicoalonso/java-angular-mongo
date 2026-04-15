package com.lacedorium.library.application.customer.creator;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SequenceNumberRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreateTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerCreate creator;
    private FixtureBuilder<CustomerCreatePayload> builder;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        SequenceNumberRepositoryStub repoSequenceNumber = new SequenceNumberRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        creator = new CustomerCreate(repoCustomer, repoSequenceNumber, repoUser);

        builder = new FixtureBuilder<>(CustomerCreatePayload.class, "customer-create");
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoCustomer.put(Ref.CustomerJohnDoe);
        CustomerCreatePayload payload = builder.build();

        assertThrows(
                CustomerAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldCreate() {
        CustomerCreatePayload payload = builder.build();

        Customer customer = creator.dispatch(payload);

        assertEquals(payload.name(), customer.getName());
        assertEquals("SN00001", customer.getMembership().getNumber());
        repoCustomer.assertStored();
    }
}
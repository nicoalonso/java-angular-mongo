package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.creator.CustomerCreate;
import com.lacedorium.library.application.customer.creator.CustomerCreatePayload;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.SequenceNumberRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.customer.CustomerReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCreateControllerTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerCreateController controller;
    private FixtureBuilder<CustomerCreatePayload> builder;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        SequenceNumberRepositoryStub repoSequenceNumber = new SequenceNumberRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        CustomerCreate creator = new CustomerCreate(repoCustomer, repoSequenceNumber, repoUser);
        controller = new CustomerCreateController(creator);

        builder = new FixtureBuilder<>(CustomerCreatePayload.class, "customer-create");
    }

    @Test
    void shouldRunWhenCreate() {
        CustomerCreatePayload payload = builder.build();

        CustomerReadView result = controller.create(payload);

        assertEquals(payload.name(), result.getData().name());
        repoCustomer.assertStored();
    }
}
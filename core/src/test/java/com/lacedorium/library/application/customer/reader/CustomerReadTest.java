package com.lacedorium.library.application.customer.reader;

import com.lacedorium.library.domain.customer.Customer;
import com.lacedorium.library.domain.customer.exception.CustomerNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerReadTest {
    private CustomerRepositoryStub repoCustomer;
    private CustomerRead reader;

    @BeforeEach
    void setUp() {
        repoCustomer = new CustomerRepositoryStub();
        reader = new CustomerRead(repoCustomer);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                CustomerNotFoundException.class,
                () -> reader.dispatch("not-found")
        );
    }

    @Test
    void shouldRunWhenRead() {
        Customer customer = repoCustomer.put(Ref.CustomerJohnDoe);

        Customer result = reader.dispatch(customer.getId());

        assertEquals(customer, result);
    }
}
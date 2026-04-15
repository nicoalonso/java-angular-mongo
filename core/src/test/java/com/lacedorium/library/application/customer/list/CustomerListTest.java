package com.lacedorium.library.application.customer.list;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerListTest {
    private CustomerRepositoryStub repository;
    private CustomerList lister;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepositoryStub();
        lister = new CustomerList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<?> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}

package com.lacedorium.library.infrastructure.controller.v1.customer;

import com.lacedorium.library.application.customer.list.CustomerList;
import com.lacedorium.library.application.customer.list.CustomerListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.CustomerRepositoryStub;
import com.lacedorium.library.presentation.v1.customer.CustomerReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerListControllerTest {
    private CustomerRepositoryStub repository;
    private CustomerListController controller;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepositoryStub();
        CustomerList lister = new CustomerList(repository);
        controller = new CustomerListController(lister);
    }

    @Test
    void shouldRunList() {
        List<?> customers = repository.attachAll();

        CustomerListQuery query = new CustomerListQuery();
        ListView<CustomerReadRecord> result = controller.listCustomers(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(customers.size(), result.getItems().size());
    }
}
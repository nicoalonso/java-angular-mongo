package com.lacedorium.library.application.sale.list;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.SaleRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleListTest {
    private SaleRepositoryStub repository;
    private SaleList lister;

    @BeforeEach
    void setUp() {
        repository = new SaleRepositoryStub();
        lister = new SaleList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<?> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}
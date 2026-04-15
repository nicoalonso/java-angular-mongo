package com.lacedorium.library.application.purchase.list;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.PurchaseRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseListTest {
    private PurchaseRepositoryStub repository;
    private PurchaseList lister;

    @BeforeEach
    void setUp() {
        repository = new PurchaseRepositoryStub();
        lister = new PurchaseList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<?> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}
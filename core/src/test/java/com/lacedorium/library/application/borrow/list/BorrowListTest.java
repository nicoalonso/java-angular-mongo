package com.lacedorium.library.application.borrow.list;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowListTest {
    private BorrowRepositoryStub repository;
    private BorrowList lister;

    @BeforeEach
    void setUp() {
        repository = new BorrowRepositoryStub();
        lister = new BorrowList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<?> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}

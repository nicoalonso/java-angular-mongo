package com.lacedorium.library.application.editorial.list;

import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialListTest {
    private EditorialRepositoryStub repository;
    private EditorialList lister;

    @BeforeEach
    void setUp() {
        repository = new EditorialRepositoryStub();
        lister = new EditorialList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<?> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}
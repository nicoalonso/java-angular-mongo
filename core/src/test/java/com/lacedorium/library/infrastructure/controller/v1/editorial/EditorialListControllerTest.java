package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.list.EditorialList;
import com.lacedorium.library.application.editorial.list.EditorialListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EditorialListControllerTest {
    private EditorialRepositoryStub repository;
    private EditorialListController controller;

    @BeforeEach
    void setUp() {
        repository = new EditorialRepositoryStub();
        EditorialList lister = new EditorialList(repository);
        controller = new EditorialListController(lister);
    }

    @Test
    void shouldListEditorials() {
        List<?> editorials = repository.attachAll();

        EditorialListQuery query = new EditorialListQuery();
        ListView<EditorialReadRecord> result = controller.listEditorials(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(editorials.size(), result.getItems().size());
    }
}
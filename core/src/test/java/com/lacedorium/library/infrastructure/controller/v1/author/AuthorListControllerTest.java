package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.list.AuthorList;
import com.lacedorium.library.application.author.list.AuthorListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.presentation.v1.author.AuthorReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorListControllerTest {
    private AuthorRepositoryStub repository;
    private AuthorListController controller;

    @BeforeEach
    void setUp() {
        repository = new AuthorRepositoryStub();
        AuthorList lister = new AuthorList(repository);
        controller = new AuthorListController(lister);
    }

    @Test
    void shouldRunList() {
        List<?> authors = repository.attachAll();

        AuthorListQuery query = new AuthorListQuery();
        ListView<AuthorReadRecord> result = controller.listAuthors(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(authors.size(), result.getItems().size());
    }
}
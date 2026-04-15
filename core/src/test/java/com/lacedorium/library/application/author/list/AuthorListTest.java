package com.lacedorium.library.application.author.list;

import com.lacedorium.library.application.identity.list.exception.InvalidFilterException;
import com.lacedorium.library.application.identity.list.exception.InvalidSortFieldException;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorListTest {
    private AuthorRepositoryStub repository;
    private AuthorList lister;

    @Data
    @EqualsAndHashCode(callSuper = true)
    static
    class DummyAuthorPayload extends AuthorListQuery {
        String dummy;
    }

    @BeforeEach
    void setUp() {
        repository = new AuthorRepositoryStub();
        lister = new AuthorList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.clear();
        List<?> authors = repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<Author> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(authors.size(), result.getItems().size());
    }

    @Test
    void shouldFailWhenInvalidFilter() {
        DummyAuthorPayload payload = new DummyAuthorPayload();
        payload.setDummy("dummy");

        ListQuery query = ListQuery.parse(payload);

        assertThrows(
                InvalidFilterException.class,
                () -> lister.dispatch(query)
        );
    }

    @Test
    void shouldFailWhenInvalidSortField() {
        DummyAuthorPayload payload = new DummyAuthorPayload();
        payload.setSort("dummy");

        ListQuery query = ListQuery.parse(payload);

        assertThrows(
                InvalidSortFieldException.class,
                () -> lister.dispatch(query)
        );
    }

    @Test
    void shouldRunWhenEmpty() {
        DummyAuthorPayload payload = new DummyAuthorPayload();
        payload.setName("Author");

        ListQuery query = ListQuery.parse(payload);

        ListResult<Author> result = lister.dispatch(query);

        assertTrue(result.getItems().isEmpty());
    }
}

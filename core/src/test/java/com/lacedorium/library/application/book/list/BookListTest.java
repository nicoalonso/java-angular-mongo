package com.lacedorium.library.application.book.list;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.identity.list.ListQuery;
import com.lacedorium.library.domain.identity.list.ListResult;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookListTest {
    private BookRepositoryStub repository;
    private BookList lister;

    @BeforeEach
    void setUp() {
        repository = new BookRepositoryStub();
        lister = new BookList(repository);
    }

    @Test
    void shouldRunWhenList() {
        repository.attachAll();

        ListQuery query = new ListQuery();
        ListResult<Book> result = lister.dispatch(query);

        assertFalse(result.getItems().isEmpty());
    }
}
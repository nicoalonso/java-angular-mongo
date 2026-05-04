package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.list.BookList;
import com.lacedorium.library.application.book.list.BookListQuery;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.presentation.v1.book.BookReadRecord;
import com.lacedorium.library.presentation.v1.identity.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookListControllerTest {
    private BookRepositoryStub repository;
    private BookListController controller;

    @BeforeEach
    void setUp() {
        repository = new BookRepositoryStub();
        BookList lister = new BookList(repository);
        controller = new BookListController(lister);
    }

    @Test
    void listBooksReturnsAllBooks() {
        List<?> books = repository.attachAll();
        BookListQuery query = new BookListQuery();

        ListView<BookReadRecord> result = controller.listBooks(query);

        assertFalse(result.getItems().isEmpty());
        assertEquals(books.size(), result.getItems().size());
    }
}
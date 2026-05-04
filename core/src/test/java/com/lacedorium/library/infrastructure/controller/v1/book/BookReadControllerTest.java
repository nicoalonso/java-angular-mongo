package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.reader.BookRead;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.book.BookReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookReadControllerTest {
    private BookRepositoryStub repoBook;
    private BookReadController controller;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        BookRead reader = new BookRead(repoBook);
        controller = new BookReadController(reader);
    }

    @Test
    void shouldRunWhenRead() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        BookReadView result = controller.getBook(book.getId());

        assertNotNull(result);
        assertEquals(book.getId(), result.getData().id());
        assertEquals(book.getTitle(), result.getData().title());
    }
}
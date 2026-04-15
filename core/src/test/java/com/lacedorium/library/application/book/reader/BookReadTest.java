package com.lacedorium.library.application.book.reader;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookReadTest {
    private BookRepositoryStub repoBook;
    private BookRead reader;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        reader = new BookRead(repoBook);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                BookNotFoundException.class,
                () -> reader.dispatch("nonexistent-book-id")
        );
    }

    @Test
    void shouldRead() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);

        Book result = reader.dispatch(book.getId());

        assertNotNull(book);
        assertEquals(book.getId(), result.getId());
    }
}
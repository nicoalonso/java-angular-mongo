package com.lacedorium.library.application.book.available;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.domain.services.book.inspector.BookInspectFactory;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookAvailableTest {
    private BookRepositoryStub repoBook;
    private BookAvailable available;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        BorrowLineRepositoryStub repoBorrowLine = new BorrowLineRepositoryStub(repoBook);
        BookInspectFactory factory = new BookInspectFactory(repoBorrowLine);
        available = new BookAvailable(repoBook, factory);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                BookNotFoundException.class,
                () -> available.dispatch("nonexistent-book-id", true)
        );
    }

    @Test
    void shouldSucceedWhenFound() {
        Book book = repoBook.put(Ref.BookDonQuixote);
        book.changeStock(10);

        Boolean result = available.dispatch(book.getId(), true);

        assertTrue(result);
    }
}

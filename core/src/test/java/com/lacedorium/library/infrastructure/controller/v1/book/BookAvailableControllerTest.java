package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.available.BookAvailable;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.services.book.inspector.BookInspectFactory;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.book.BookAvailableView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookAvailableControllerTest {
    private BookRepositoryStub repoBook;
    private BookAvailableController controller;

    @BeforeEach
    void setUp() {
        repoBook = new BookRepositoryStub();
        BorrowLineRepositoryStub repoBorrowLine = new BorrowLineRepositoryStub(repoBook);
        BookInspectFactory factory = new BookInspectFactory(repoBorrowLine);
        BookAvailable available = new BookAvailable(repoBook, factory);
        controller = new BookAvailableController(available);
    }

    @Test
    void shouldAvailable() {
        Book book = repoBook.put(Ref.BookDonQuixote);
        book.changeStock(10);

        BookAvailableView result = controller.available(book.getId(), true);

        assertNotNull(result.getData());
    }
}
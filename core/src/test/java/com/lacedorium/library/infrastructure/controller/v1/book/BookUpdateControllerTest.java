package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.updater.BookUpdate;
import com.lacedorium.library.application.book.updater.BookUpdatePayload;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUpdateControllerTest {
    private BookRepositoryStub repoBook;
    private AuthorRepositoryStub repoAuthor;
    private EditorialRepositoryStub repoEditorial;
    private BookUpdateController controller;
    private FixtureBuilder<BookUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        repoEditorial = new EditorialRepositoryStub();
        repoBook = new BookRepositoryStub(repoAuthor, repoEditorial);
        UserRepositoryStub repoUser = new UserRepositoryStub();

        BookUpdate updater = new BookUpdate(repoBook, repoAuthor, repoEditorial, repoUser);
        controller = new BookUpdateController(updater);

        fixture = new FixtureBuilder<>(BookUpdatePayload.class, "book");
    }

    @Test
    void shouldRunWhenUpdate() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoAuthor.put(Ref.AuthorShakespeare);
        repoEditorial.put(Ref.EditorialAnaya);

        BookUpdatePayload payload = fixture.build();
        controller.update(book.getId(), payload);

        Book bookStored = repoBook.assertStored();
        assertEquals(payload.title(), bookStored.getTitle());
    }
}
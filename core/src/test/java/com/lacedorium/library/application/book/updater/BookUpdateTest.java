package com.lacedorium.library.application.book.updater;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.book.exception.BookNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookUpdateTest {
    private BookRepositoryStub repoBook;
    private AuthorRepositoryStub repoAuthor;
    private EditorialRepositoryStub repoEditorial;
    private BookUpdate updater;
    private FixtureBuilder<BookUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        repoEditorial = new EditorialRepositoryStub();
        repoBook = new BookRepositoryStub(repoAuthor, repoEditorial);
        UserRepositoryStub repoUser = new UserRepositoryStub();

        updater = new BookUpdate(repoBook, repoAuthor, repoEditorial, repoUser);

        fixture = new FixtureBuilder<>(BookUpdatePayload.class, "book");
    }

    @Test
    void shouldFailWhenNotFound() {
        BookUpdatePayload payload = fixture.build();

        assertThrows(
                BookNotFoundException.class,
                () -> updater.dispatch("not-found", payload)
        );
    }

    @Test
    void shouldRunWhenUpdate() {
        Book book = repoBook.put(Ref.BookRomeoAndJuliet);
        repoAuthor.put(Ref.AuthorShakespeare);
        repoEditorial.put(Ref.EditorialAnaya);

        BookUpdatePayload payload = fixture.build();
        updater.dispatch(book.getId(), payload);

        repoBook.assertStored();
    }
}
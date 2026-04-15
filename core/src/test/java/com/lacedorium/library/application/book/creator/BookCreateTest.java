package com.lacedorium.library.application.book.creator;

import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCreateTest {
    private AuthorRepositoryStub repoAuthor;
    private EditorialRepositoryStub repoEditorial;
    private BookRepositoryStub repoBook;
    private BookCreate creator;
    private FixtureBuilder<BookCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        repoEditorial = new EditorialRepositoryStub();
        repoBook = new BookRepositoryStub(repoAuthor, repoEditorial);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        creator = new BookCreate(repoBook, repoAuthor, repoEditorial, repoUser);
        fixture = new FixtureBuilder<>(BookCreatePayload.class, "book");
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoBook.put(Ref.BookRomeoAndJuliet);
        BookCreatePayload payload = fixture.build();

        assertThrows(
                BookAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenAuthorNotFound() {
        BookCreatePayload payload = fixture.build();

        assertThrows(
                AuthorNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldFailWhenEditorialNotFound() {
        repoAuthor.put(Ref.AuthorShakespeare);
        BookCreatePayload payload = fixture.build();

        assertThrows(
                EditorialNotFoundException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        repoAuthor.put(Ref.AuthorShakespeare);
        repoEditorial.put(Ref.EditorialAnaya);
        BookCreatePayload payload = fixture.build();

        Book book = creator.dispatch(payload);

        assertEquals(payload.title(), book.getTitle());
        repoBook.assertStored();
    }
}
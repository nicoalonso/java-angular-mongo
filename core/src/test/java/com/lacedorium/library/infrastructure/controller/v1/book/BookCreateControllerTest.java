package com.lacedorium.library.infrastructure.controller.v1.book;

import com.lacedorium.library.application.book.creator.BookCreate;
import com.lacedorium.library.application.book.creator.BookCreatePayload;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.book.BookReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCreateControllerTest {
    private AuthorRepositoryStub repoAuthor;
    private EditorialRepositoryStub repoEditorial;
    private BookRepositoryStub repoBook;
    private BookCreateController controller;
    private FixtureBuilder<BookCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        repoEditorial = new EditorialRepositoryStub();
        repoBook = new BookRepositoryStub(repoAuthor, repoEditorial);
        UserRepositoryStub repoUser = new UserRepositoryStub();
        BookCreate creator = new BookCreate(repoBook, repoAuthor, repoEditorial, repoUser);
        controller = new BookCreateController(creator);

        fixture = new FixtureBuilder<>(BookCreatePayload.class, "book");
    }

    @Test
    void shouldRunWhenCreate() {
        repoAuthor.put(Ref.AuthorShakespeare);
        repoEditorial.put(Ref.EditorialAnaya);
        BookCreatePayload payload = fixture.build();

        BookReadView result = controller.create(payload);

        assertNotNull(result);
        assertEquals(payload.title(), result.getData().title());
        repoBook.assertStored();
    }
}
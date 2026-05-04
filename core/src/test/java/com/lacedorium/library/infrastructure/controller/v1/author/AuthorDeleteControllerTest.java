package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.eraser.AuthorDelete;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorDeleteControllerTest {
    private AuthorRepositoryStub repoAuthor;
    private AuthorDeleteController controller;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        BookRepositoryStub repoBook = new BookRepositoryStub(repoAuthor);
        AuthorDelete deleter = new AuthorDelete(repoAuthor, repoBook);
        controller = new AuthorDeleteController(deleter);
    }

    @Test
    void shouldDeleteAuthor() {
        Author author = repoAuthor.put(Ref.AuthorShakespeare);

        controller.deleteAuthor(author.getId());

        String authorId = repoAuthor.assertRemoved();
        assertEquals(author.getId(), authorId);
    }
}
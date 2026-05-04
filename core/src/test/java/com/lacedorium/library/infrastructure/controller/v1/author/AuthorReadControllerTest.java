package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.reader.AuthorRead;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.author.AuthorReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorReadControllerTest {
    private AuthorRepositoryStub repoAuthor;
    private AuthorReadController controller;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        AuthorRead reader = new AuthorRead(repoAuthor);
        controller = new AuthorReadController(reader);
    }

    @Test
    void shouldRunWhenRead() {
        Author author = repoAuthor.put(Ref.AuthorShakespeare);

        AuthorReadView result = controller.getAuthor(author.getId());

        assertNotNull(result);
        assertEquals(author.getId(), result.getData().id());
        assertEquals(author.getName(), result.getData().name());
    }
}
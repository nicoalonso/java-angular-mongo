package com.lacedorium.library.application.author.reader;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorReadTest {
    private AuthorRepositoryStub repoAuthor;
    private AuthorRead reader;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        reader = new AuthorRead(repoAuthor);
    }

    @Test
    void shouldReadAuthor() {
        repoAuthor.put(Ref.AuthorShakespeare);
        Author author = reader.dispatch("11231545");

        assertNotNull(author);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                AuthorNotFoundException.class,
                () -> reader.dispatch("unknown-id")
        );
    }
}
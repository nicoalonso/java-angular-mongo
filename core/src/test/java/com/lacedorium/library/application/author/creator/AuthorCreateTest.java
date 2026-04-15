package com.lacedorium.library.application.author.creator;

import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorCreateTest {
    private AuthorRepositoryStub repoAuthor;
    private AuthorCreate creator;
    FixtureBuilder<AuthorCreatePayload> builder;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        creator = new AuthorCreate(repoAuthor, repoUser);
        builder = new FixtureBuilder<>(AuthorCreatePayload.class, "author");
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoAuthor.put(Ref.AuthorShakespeare);
        AuthorCreatePayload payload = builder.build();

        assertThrows(
                AuthorAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldCreate() {
        AuthorCreatePayload payload = builder.build();

        creator.dispatch(payload);

        Author author = repoAuthor.assertStored();
        assertInstanceOf(Author.class, author);
    }
}

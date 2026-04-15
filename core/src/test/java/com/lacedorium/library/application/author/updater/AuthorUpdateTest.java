package com.lacedorium.library.application.author.updater;

import com.lacedorium.library.domain.author.exception.AuthorNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorUpdateTest {
    private AuthorRepositoryStub repoAuthor;
    private AuthorUpdate updater;
    private FixtureBuilder<AuthorUpdatePayload> builder;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        updater = new AuthorUpdate(repoAuthor, repoUser);

        builder = new FixtureBuilder<>(AuthorUpdatePayload.class, "author");
    }

    @Test
    void shouldFailWhenNotFound() {
        AuthorUpdatePayload payload = builder.build();

        assertThrows(
                AuthorNotFoundException.class,
                () -> updater.dispatch("non-existent-id", payload)
        );
    }

    @Test
    void shouldRunWhenUpdate() {
        repoAuthor.put(Ref.AuthorShakespeare);
        AuthorUpdatePayload payload = builder.build();

        updater.dispatch("1121212", payload);

        repoAuthor.assertStored();
    }
}

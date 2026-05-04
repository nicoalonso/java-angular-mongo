package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.updater.AuthorUpdate;
import com.lacedorium.library.application.author.updater.AuthorUpdatePayload;
import com.lacedorium.library.domain.author.Author;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorUpdateControllerTest {
    private AuthorRepositoryStub repoAuthor;
    private FixtureBuilder<AuthorUpdatePayload> builder;
    private AuthorUpdateController controller;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        AuthorUpdate updater = new AuthorUpdate(repoAuthor, repoUser);
        controller = new AuthorUpdateController(updater);

        builder = new FixtureBuilder<>(AuthorUpdatePayload.class, "author");
    }

    @Test
    void shouldRunWhenUpdate() {
        Author author = repoAuthor.put(Ref.AuthorShakespeare);
        AuthorUpdatePayload payload = builder.build();

        controller.update(author.getId(), payload);

        Author authorStored = repoAuthor.assertStored();
        assertEquals(author.getId(), authorStored.getId());
    }
}
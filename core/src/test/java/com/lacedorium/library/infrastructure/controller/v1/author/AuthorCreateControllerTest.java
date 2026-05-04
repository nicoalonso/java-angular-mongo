package com.lacedorium.library.infrastructure.controller.v1.author;

import com.lacedorium.library.application.author.creator.AuthorCreate;
import com.lacedorium.library.application.author.creator.AuthorCreatePayload;
import com.lacedorium.library.doubles.infrastructure.persistence.AuthorRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.author.AuthorReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorCreateControllerTest {
    private AuthorRepositoryStub repoAuthor;
    private FixtureBuilder<AuthorCreatePayload> builder;
    private AuthorCreateController controller;

    @BeforeEach
    void setUp() {
        repoAuthor = new AuthorRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        AuthorCreate creator = new AuthorCreate(repoAuthor, repoUser);
        builder = new FixtureBuilder<>(AuthorCreatePayload.class, "author");
        controller = new AuthorCreateController(creator);
    }

    @Test
    void shouldRunWhenCreate() {
        AuthorCreatePayload payload = builder.build();

        AuthorReadView result = controller.create(payload);

        assertNotNull(result);
        repoAuthor.assertStored();
    }
}
package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.creator.EditorialCreate;
import com.lacedorium.library.application.editorial.creator.EditorialCreatePayload;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialCreateControllerTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialCreateController controller;
    private FixtureBuilder<EditorialCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        EditorialCreate creator = new EditorialCreate(repoEditorial, repoUser);
        controller = new EditorialCreateController(creator);

        fixture = new FixtureBuilder<>(EditorialCreatePayload.class, "editorial");
    }

    @Test
    void shouldRunWhenCreate() {
        EditorialCreatePayload payload = fixture.build();

        EditorialReadView result = controller.create(payload);

        assertEquals(payload.name(), result.getData().name());
        repoEditorial.assertStored();
    }
}
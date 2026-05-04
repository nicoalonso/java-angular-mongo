package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.updater.EditorialUpdate;
import com.lacedorium.library.application.editorial.updater.EditorialUpdatePayload;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialUpdateControllerTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialUpdateController controller;
    private FixtureBuilder<EditorialUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        EditorialUpdate updater = new EditorialUpdate(repoEditorial, repoUser);
        controller = new EditorialUpdateController(updater);

        fixture = new FixtureBuilder<>(EditorialUpdatePayload.class, "editorial");
    }

    @Test
    void shouldRunWhenUpdate() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);
        EditorialUpdatePayload payload = fixture.build();

        controller.update(editorial.getId(), payload);

        Editorial editorialStored = repoEditorial.assertStored();
        assertEquals(payload.name(), editorialStored.getName());
    }
}
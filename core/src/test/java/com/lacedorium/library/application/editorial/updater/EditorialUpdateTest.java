package com.lacedorium.library.application.editorial.updater;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialUpdateTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialUpdate updater;
    private FixtureBuilder<EditorialUpdatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        updater = new EditorialUpdate(repoEditorial, repoUser);

        fixture = new FixtureBuilder<>(EditorialUpdatePayload.class, "editorial");
    }

    @Test
    void shouldFailWhenNotFound() {
        EditorialUpdatePayload payload = fixture.build();

        assertThrows(
                EditorialNotFoundException.class,
                () -> updater.dispatch("not-found", payload)
        );
    }

    @Test
    void shouldRunWhenUpdate() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);

        EditorialUpdatePayload payload = fixture.build();

        updater.dispatch(editorial.getId(), payload);

        repoEditorial.assertStored();
    }
}
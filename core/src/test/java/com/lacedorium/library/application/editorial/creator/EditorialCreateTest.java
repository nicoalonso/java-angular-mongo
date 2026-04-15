package com.lacedorium.library.application.editorial.creator;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.UserRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialCreateTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialCreate creator;
    private FixtureBuilder<EditorialCreatePayload> fixture;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        UserRepositoryStub repoUser = new UserRepositoryStub();
        creator = new EditorialCreate(repoEditorial, repoUser);

        fixture = new FixtureBuilder<>(EditorialCreatePayload.class, "editorial");
    }

    @Test
    void shouldFailWhenAlreadyExists() {
        repoEditorial.put(Ref.EditorialAnaya);

        EditorialCreatePayload payload = fixture.build();

        assertThrows(
                EditorialAlreadyExistsException.class,
                () -> creator.dispatch(payload)
        );
    }

    @Test
    void shouldRunWhenCreate() {
        EditorialCreatePayload payload = fixture.build();

        Editorial editorial = creator.dispatch(payload);

        assertEquals(payload.name(), editorial.getName());
        repoEditorial.assertStored();
    }
}
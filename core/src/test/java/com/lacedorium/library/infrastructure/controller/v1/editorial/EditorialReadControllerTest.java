package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.reader.EditorialRead;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.editorial.EditorialReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialReadControllerTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialReadController controller;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        EditorialRead reader = new EditorialRead(repoEditorial);
        controller = new EditorialReadController(reader);
    }

    @Test
    void shouldRead() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);

        EditorialReadView result = controller.dispatch(editorial.getId());

        assertEquals(editorial.getId(), result.getData().id());
    }
}
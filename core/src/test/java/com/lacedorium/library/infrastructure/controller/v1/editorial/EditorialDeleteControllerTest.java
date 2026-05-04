package com.lacedorium.library.infrastructure.controller.v1.editorial;

import com.lacedorium.library.application.editorial.eraser.EditorialDelete;
import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialDeleteControllerTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialDeleteController controller;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        BookRepositoryStub repoBook = new BookRepositoryStub(repoEditorial);
        EditorialDelete delete = new EditorialDelete(repoEditorial, repoBook);
        controller = new EditorialDeleteController(delete);
    }

    @Test
    void shouldDelete() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);

        controller.deleteEditorial(editorial.getId());

        String editorialId = repoEditorial.assertRemoved();
        assertEquals(editorial.getId(), editorialId);
    }
}
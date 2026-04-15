package com.lacedorium.library.application.editorial.reader;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialReadTest {
    private EditorialRepositoryStub repoEditorial;
    private EditorialRead reader;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        reader = new EditorialRead(repoEditorial);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                EditorialNotFoundException.class,
                () -> reader.dispatch("not-found")
        );
    }

    @Test
    void shouldReturnEditorialWhenFound() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);

        Editorial result = reader.dispatch(editorial.getId());

        assertEquals(editorial, result);
    }
}
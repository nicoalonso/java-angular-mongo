package com.lacedorium.library.application.editorial.eraser;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.domain.editorial.exception.EditorialNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BookRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.EditorialRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditorialDeleteTest {
    private EditorialRepositoryStub repoEditorial;
    private BookRepositoryStub repoBook;
    private EditorialDelete delete;

    @BeforeEach
    void setUp() {
        repoEditorial = new EditorialRepositoryStub();
        repoBook = new BookRepositoryStub(repoEditorial);
        delete = new EditorialDelete(repoEditorial, repoBook);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                EditorialNotFoundException.class,
                () -> delete.dispatch("not-found")
        );
    }

    @Test
    void shouldFailWhenAssociated() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);
        repoBook.attach(Ref.BookRomeoAndJuliet);

        assertThrows(
                EditorialBookAssociatedException.class,
                () -> delete.dispatch(editorial.getId())
        );
    }

    @Test
    void shouldRunWhenDelete() {
        Editorial editorial = repoEditorial.put(Ref.EditorialAnaya);

        delete.dispatch(editorial.getId());

        repoEditorial.assertRemoved();
    }}
package com.lacedorium.library.domain.services.book.inspector;

import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookInspectFactoryTest {
    private BookInspectFactory factory;

    @BeforeEach
    void setUp() {
        BorrowLineRepositoryStub repoBorrowLine = new BorrowLineRepositoryStub();
        factory = new BookInspectFactory(repoBorrowLine);
    }

    @Test
    void shouldCreateSaleInspector() {
        BookInspector inspector = factory.create(true);

        assertNotNull(inspector);
        assertInstanceOf(BookSaleInspect.class, inspector);
    }

    @Test
    void shouldCreateBorrowInspector() {
        BookInspector inspector = factory.create(false);

        assertNotNull(inspector);
        assertInstanceOf(BookBorrowInspect.class, inspector);
    }
}
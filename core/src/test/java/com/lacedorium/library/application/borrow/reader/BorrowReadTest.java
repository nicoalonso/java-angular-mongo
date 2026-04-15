package com.lacedorium.library.application.borrow.reader;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.exception.BorrowNotFoundException;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowReadTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowRead reader;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        reader = new BorrowRead(repoBorrow, repoBorrowLine);
    }

    @Test
    void shouldFailWhenNotFound() {
        assertThrows(
                BorrowNotFoundException.class,
                () -> reader.dispatch("nonexistent-borrow-id")
        );
    }

    @Test
    void shouldRead() {
        Borrow borrow = repoBorrow.put(Ref.BorrowJohnDoe);
        repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        BorrowDecorator decorator = reader.dispatch(borrow.getId());

        assertNotNull(decorator);
        assertEquals(borrow.getId(), decorator.borrow().getId());
        assertEquals(1, decorator.lines().size());
    }
}
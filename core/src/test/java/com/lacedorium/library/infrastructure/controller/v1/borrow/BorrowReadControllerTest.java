package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.reader.BorrowRead;
import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import com.lacedorium.library.presentation.v1.borrow.BorrowReadView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorrowReadControllerTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowReadController controller;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        BorrowRead reader = new BorrowRead(repoBorrow, repoBorrowLine);
        controller = new BorrowReadController(reader);
    }

    @Test
    void shouldRead() {
        Borrow borrow = repoBorrow.put(Ref.BorrowJohnDoe);
        repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        BorrowReadView result = controller.read(borrow.getId());

        assertNotNull(result);
        assertEquals(borrow.getId(), result.getData().id());
        assertEquals(1, result.getData().lines().size());
    }
}
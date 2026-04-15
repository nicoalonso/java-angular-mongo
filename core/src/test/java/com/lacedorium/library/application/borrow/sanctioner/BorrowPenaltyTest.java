package com.lacedorium.library.application.borrow.sanctioner;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.doubles.Hydratable;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BorrowPenaltyTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowPenalty sanctioner;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        sanctioner = new BorrowPenalty(repoBorrow, repoBorrowLine);
    }

    @Test
    void shouldFailWhenBorrowLineNotFound() {
        Borrow borrow = repoBorrow.attach(Ref.BorrowJohnDoe);
        LocalDateTime dueDate = borrow.getDueDate().minusDays(16);
        Hydratable.hydrateProperty(borrow, "dueDate", dueDate);

        int count = sanctioner.dispatch();

        assertEquals(0, count);
        repoBorrow.assertNotStored();
        repoBorrowLine.assertNotStored();
    }

    @Test
    void shouldRunWhenFoundPenalties() {
        Borrow borrow = repoBorrow.attach(Ref.BorrowJohnDoe);
        BorrowLine line = repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        LocalDateTime dueDate = borrow.getDueDate().minusDays(16);
        Hydratable.hydrateProperty(borrow, "dueDate", dueDate);

        int count = sanctioner.dispatch();

        assertEquals(1, count);
        assertTrue(borrow.getPenalty());
        assertEquals(5.0, borrow.getPenaltyAmount());
        assertTrue(line.getPenalty());
        assertEquals(5.0, line.getPenaltyAmount());
        repoBorrow.assertStored();
        repoBorrowLine.assertStored();
    }
}
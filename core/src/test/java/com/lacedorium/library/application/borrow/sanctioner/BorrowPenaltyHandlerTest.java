package com.lacedorium.library.application.borrow.sanctioner;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.doubles.Hydratable;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowLineRepositoryStub;
import com.lacedorium.library.doubles.infrastructure.persistence.BorrowRepositoryStub;
import com.lacedorium.library.fixtures.Ref;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class BorrowPenaltyHandlerTest {
    private BorrowRepositoryStub repoBorrow;
    private BorrowLineRepositoryStub repoBorrowLine;
    private BorrowPenaltyHandler handler;

    @BeforeEach
    void setUp() {
        repoBorrow = new BorrowRepositoryStub();
        repoBorrowLine = new BorrowLineRepositoryStub(repoBorrow);
        BorrowPenalty sanctioner = new BorrowPenalty(repoBorrow, repoBorrowLine);
        handler = new BorrowPenaltyHandler(sanctioner);
    }

    @Test
    void shouldHandle() {
        Borrow borrow = repoBorrow.attach(Ref.BorrowJohnDoe);
        repoBorrowLine.attach(Ref.BorrowLineJohnQuixote);

        LocalDateTime dueDate = borrow.getDueDate().minusDays(16);
        Hydratable.hydrateProperty(borrow, "dueDate", dueDate);

        BorrowPenaltyEvent event = new BorrowPenaltyEvent();
        handler.handle(event);

        repoBorrow.assertStored();
        repoBorrowLine.assertStored();
    }

    @Test
    void shouldError() {
        repoBorrow.error("Error");

        BorrowPenaltyEvent event = new BorrowPenaltyEvent();
        handler.handle(event);

        repoBorrow.assertNotStored();
        repoBorrowLine.assertNotStored();
    }
}
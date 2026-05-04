package com.lacedorium.library.infrastructure.controller.v1.borrow;

import com.lacedorium.library.application.borrow.sanctioner.BorrowPenaltyEvent;
import com.lacedorium.library.doubles.infrastructure.bus.DomainBusStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorrowManualPenaltyControllerTest {
    private DomainBusStub bus;
    private BorrowManualPenaltyController controller;

    @BeforeEach
    void setUp() {
        bus = new DomainBusStub();
        controller = new BorrowManualPenaltyController(bus);
    }

    @Test
    void shouldManualPenalty() {
        controller.manualPenalty();

        bus.assertDispatch(BorrowPenaltyEvent.class);
    }
}
package com.lacedorium.library.application.borrow.sanctioner;

import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.bus.DomainRoute;

public class BorrowPenaltyEvent extends DomainEvent {
    public static final String ACTION = "borrow.penalty";

    public BorrowPenaltyEvent() {
        super(ACTION, "borrow", DomainRoute.LIBRARY);
    }
}

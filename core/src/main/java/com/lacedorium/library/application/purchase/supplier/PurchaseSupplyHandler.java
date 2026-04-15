package com.lacedorium.library.application.purchase.supplier;

import com.lacedorium.library.application.purchase.creator.PurchaseCreatedEvent;
import com.lacedorium.library.application.purchase.eraser.PurchaseDeletedEvent;
import com.lacedorium.library.application.purchase.updater.PurchaseUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseSupplyHandler {
    private final PurchaseSupply supply;

    @EventListener
    public void handleCreated(PurchaseCreatedEvent event) {
        supply.dispatch(event.getBooks());
    }

    @EventListener
    public void handleUpdated(PurchaseUpdatedEvent event) {
        supply.dispatch(event.getBooks());
    }

    @EventListener
    public void handleDeleted(PurchaseDeletedEvent event) {
        supply.dispatch(event.getBooks());
    }
}

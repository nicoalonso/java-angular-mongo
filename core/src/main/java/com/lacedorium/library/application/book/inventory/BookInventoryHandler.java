package com.lacedorium.library.application.book.inventory;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryHandler {
    private final BookInventory inventory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void handle(BookInventoryEvent event) {
        try {
            inventory.dispatch(event.getDescriptor());
        } catch (Exception e) {
            logger.error("Error dispatching book inventory event: {}", e.getMessage(), e);
        }
    }
}

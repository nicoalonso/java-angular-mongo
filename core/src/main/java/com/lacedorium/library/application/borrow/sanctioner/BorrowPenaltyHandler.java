package com.lacedorium.library.application.borrow.sanctioner;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowPenaltyHandler {
    private final BorrowPenalty sanctioner;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void handle(BorrowPenaltyEvent event) {
        try {
            sanctioner.dispatch();
        } catch (Exception e) {
            logger.error("Error handling borrow penalty for event: {}", event, e);
        }
    }
}

package com.lacedorium.library.application.sale.consumer;

import com.lacedorium.library.application.sale.creator.SaleCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleConsumeHandler {
    private final SaleConsume consumer;

    @EventListener
    public void handle(SaleCreatedEvent event) {
        consumer.dispatch(event.getBooks());
    }
}

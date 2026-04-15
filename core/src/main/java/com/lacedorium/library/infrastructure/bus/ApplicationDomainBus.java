package com.lacedorium.library.infrastructure.bus;

import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.bus.DomainRoute;
import com.lacedorium.library.infrastructure.messenger.serializer.Envelope;
import com.lacedorium.library.infrastructure.messenger.serializer.MessengerSerializer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationDomainBus implements DomainBus {
    private static final String EXCHANGE = "library";

    private final ApplicationEventPublisher publisher;
    private final AmqpTemplate amqpTemplate;
    private final MessengerSerializer serializer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void dispatch(@NonNull DomainEvent event) {
        logger.info("Dispatching event: {}", event.getClass().getSimpleName());
        logger.info("Action: {} for type: {}", event.getAction(), event.getType());

        if (event.getRoute() == DomainRoute.NONE) {
            publish(event);
        } else {
            sendToTransport(event);
        }
    }

    private void publish(DomainEvent event) {
        try {
            logger.info("Send event {} to Listener", event.getClass().getSimpleName());
            publisher.publishEvent(event);
        } catch (Exception e) {
            logger.error("Failed to publish event {}: {}", event.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void sendToTransport(DomainEvent event) {
        try {
            String routingKey = event.getRoute().toString();
            logger.info("Send event {} to transport: {}", event.getClass().getSimpleName(), routingKey);

            Envelope<?> envelope = serializer.encode(event);
            amqpTemplate.convertAndSend(EXCHANGE, routingKey, envelope);

        } catch (Exception e) {
            logger.error("Failed to send event {} to transport: {}", event.getClass().getSimpleName(), e.getMessage());
        }
    }
}

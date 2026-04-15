package com.lacedorium.library.infrastructure.messenger.rabbit;

import com.lacedorium.library.domain.bus.DomainBus;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.domain.bus.DomainRoute;
import com.lacedorium.library.infrastructure.messenger.config.RabbitTopologyConfig;
import com.lacedorium.library.infrastructure.messenger.serializer.Envelope;
import com.lacedorium.library.infrastructure.messenger.serializer.MessengerSerializer;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.messaging.consumer.enabled", havingValue = "true")
public class LibraryQueueListener {
    private final DomainBus bus;
    private final MessengerSerializer serializer;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitTopologyConfig.LIBRARY_QUEUE)
    public void onMessage(@NonNull Envelope<LinkedHashMap<String, Object>> envelope) {
        try {
            logger.info("Received message: {}", envelope.getAction());
            DomainEvent event = serializer.decode(envelope);
            logger.info("Decoded message: {}", event.getClass().getSimpleName());

            // Change route to NONE for dispatch to the EventListener
            event.changeRoute(DomainRoute.NONE);
            bus.dispatch(event);

        } catch (Exception e) {
            logger.error("Failed to process message: {}", e.getMessage());
        }
    }
}

package com.lacedorium.library.infrastructure.messenger.serializer;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.application.borrow.sanctioner.BorrowPenaltyEvent;
import com.lacedorium.library.domain.bus.DomainEvent;
import com.lacedorium.library.infrastructure.messenger.serializer.book.BookInventorySerializer;
import com.lacedorium.library.infrastructure.messenger.serializer.borrow.BorrowPenaltySerializer;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class MessengerSerializer {
    public Envelope<?> encode(@NonNull DomainEvent event) {
        return switch (event) {
            case BookInventoryEvent bookInventoryEvent -> new BookInventorySerializer(bookInventoryEvent);
            case BorrowPenaltyEvent borrowPenaltyEvent -> new BorrowPenaltySerializer(borrowPenaltyEvent);
            default -> throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getSimpleName());
        };
    }

    public DomainEvent decode(@NonNull Envelope<LinkedHashMap<String, Object>> envelope) {
        return switch (envelope.getAction()) {
            case BookInventoryEvent.ACTION -> BookInventorySerializer.decode(envelope.getPayload());
            case BorrowPenaltyEvent.ACTION -> BorrowPenaltySerializer.decode();
            default -> throw new IllegalArgumentException("Unsupported envelope type: " + envelope.getClass().getSimpleName());
        };
    }
}

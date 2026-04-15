package com.lacedorium.library.infrastructure.messenger.serializer.borrow;

import com.lacedorium.library.application.borrow.sanctioner.BorrowPenaltyEvent;
import com.lacedorium.library.infrastructure.messenger.serializer.Envelope;
import lombok.NonNull;

import java.util.HashMap;

public class BorrowPenaltySerializer extends Envelope<HashMap<String, Object>> {
    public BorrowPenaltySerializer(@NonNull BorrowPenaltyEvent event) {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("type", event.getType());
        super(event.getAction(), event.getType(), payload);
    }

    public static @NonNull BorrowPenaltyEvent decode() {
        return new BorrowPenaltyEvent();
    }
}

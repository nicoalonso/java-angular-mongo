package com.lacedorium.library.application.purchase.creator;

import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseCreatePayloadTest {
    @Test
    void shouldFailWhenInvalidDate() {
        FixtureBuilder<PurchaseCreatePayload> builder = new FixtureBuilder<>(PurchaseCreatePayload.class, "purchase");

        PurchaseCreatePayload payload = builder.with("purchasedAt", null).build();

        assertThrows(
                InvalidPurchaseDateException.class,
                payload::getPurchasedAt
        );
    }
}
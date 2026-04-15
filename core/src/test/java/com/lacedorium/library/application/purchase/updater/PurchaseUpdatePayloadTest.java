package com.lacedorium.library.application.purchase.updater;

import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseUpdatePayloadTest {
    @Test
    void shouldFailWhenInvalidDate() {
        FixtureBuilder<PurchaseUpdatePayload> builder = new FixtureBuilder<>(PurchaseUpdatePayload.class, "purchase");

        PurchaseUpdatePayload payload = builder.with("purchasedAt", null).build();

        assertThrows(
                InvalidPurchaseDateException.class,
                payload::getPurchasedAt
        );
    }
}
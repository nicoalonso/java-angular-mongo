package com.lacedorium.library.application.sale.creator.payload;

import com.lacedorium.library.application.sale.creator.SaleCreatePayload;
import com.lacedorium.library.domain.sale.exception.InvalidSaleDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SaleInvoicePayloadTest {
    @Test
    void shouldFailWhenBirthDateIsNull() {
        FixtureBuilder<SaleCreatePayload> builder = new FixtureBuilder<>(SaleCreatePayload.class, "sale");
        SaleCreatePayload payload = builder
                .with("invoice", Map.of("date", ""))
                .build();

        assertThrows(InvalidSaleDateException.class, () -> payload.invoice().getDate());
    }
}
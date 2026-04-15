package com.lacedorium.library.application.purchase.updater;

import com.lacedorium.library.application.purchase.creator.payload.PurchaseInvoicePayload;
import com.lacedorium.library.application.purchase.creator.payload.PurchaseLinePayload;
import com.lacedorium.library.domain.purchase.exception.InvalidPurchaseDateException;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PurchaseUpdatePayload(
        String providerId,
        LocalDate purchasedAt,
        PurchaseInvoicePayload invoice,
        List<PurchaseLinePayload> lines
) {
    public @NonNull LocalDateTime getPurchasedAt() {
        if (purchasedAt == null) {
            throw new InvalidPurchaseDateException("The purchase date is required");
        }
        return purchasedAt.atStartOfDay();
    }
}

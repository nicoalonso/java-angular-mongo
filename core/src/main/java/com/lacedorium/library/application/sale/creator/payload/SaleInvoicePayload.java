package com.lacedorium.library.application.sale.creator.payload;

import com.lacedorium.library.domain.sale.exception.InvalidSaleDateException;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SaleInvoicePayload (
        LocalDate date,
        Double amount,
        Double taxPercentage,
        Double taxes,
        Double total
) {
    public @NonNull LocalDateTime getDate() {
        if (date == null) {
            throw new InvalidSaleDateException("Date is required");
        }
        return date.atStartOfDay();
    }
}

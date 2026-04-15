package com.lacedorium.library.presentation.v1.sale;

import com.lacedorium.library.domain.sale.SaleInvoice;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record SaleInvoiceRecord (
        String date,
        Double amount,
        Double taxPercentage,
        Double taxes,
        Double total
) {
    public static @NonNull SaleInvoiceRecord make(@NonNull SaleInvoice invoice) {
        return new SaleInvoiceRecord(
                Result.formatShortDate(invoice.getDate()),
                invoice.getAmount(),
                invoice.getTaxPercentage(),
                invoice.getTaxes(),
                invoice.getTotal()
        );
    }
}

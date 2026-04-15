package com.lacedorium.library.presentation.v1.purchase;

import com.lacedorium.library.domain.purchase.PurchaseInvoice;
import lombok.NonNull;

public record PurchaseInvoiceRecord (
        String number,
        Double amount,
        Double taxes,
        Double total
) {
    public static @NonNull PurchaseInvoiceRecord make(@NonNull PurchaseInvoice invoice) {
        return new PurchaseInvoiceRecord(
                invoice.getNumber(),
                invoice.getAmount(),
                invoice.getTaxes(),
                invoice.getTotal()
        );
    }
}

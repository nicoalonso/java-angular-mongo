package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.purchase.PurchaseInvoice;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class PurchaseInvoiceMother extends BaseMother<PurchaseInvoice> {
    private static final Map<String, Object> INVOICE_1 = Map.of(
        "number", "INV-001",
        "amount", 100.0,
        "taxes", 20.0,
        "total", 120.0
    );

    private static final Map<String, Object> INVOICE_2 = Map.of(
        "number", "INV-002",
        "amount", 135.0,
        "taxes", 45.0,
        "total", 180.0
    );

    protected PurchaseInvoiceMother(Map<String, Object> base) {
        super(PurchaseInvoice.class, base);
    }

    public static @NonNull PurchaseInvoiceMother invoice1() {
        return new PurchaseInvoiceMother(INVOICE_1);
    }

    public static @NonNull PurchaseInvoiceMother invoice2() {
        return new PurchaseInvoiceMother(INVOICE_2);
    }
}

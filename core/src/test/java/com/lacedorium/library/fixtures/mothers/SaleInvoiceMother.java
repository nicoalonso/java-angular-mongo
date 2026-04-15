package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.sale.SaleInvoice;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class SaleInvoiceMother extends BaseMother<SaleInvoice> {
    private static final Map<String, Object> JOHN_DOE_SALE_1 = Map.of(
            "date", date("2024-01-01"),
            "amount", 100.0,
            "taxPercentage", 21.0,
            "taxes", 21.0,
            "total", 121.0
    );

    private static final Map<String, Object> JOHN_DOE_SALE_2 = Map.of(
            "date", date("2026-03-06"),
            "amount", 80.0,
            "taxPercentage", 21.0,
            "taxes", 16.8,
            "total", 96.8
    );

    protected SaleInvoiceMother(Map<String, Object> base) {
        super(SaleInvoice.class, base);
    }

    public static @NonNull SaleInvoiceMother johnDoeSale1() {
        return new SaleInvoiceMother(JOHN_DOE_SALE_1);
    }

    public static @NonNull SaleInvoiceMother johnDoeSale2() {
        return new SaleInvoiceMother(JOHN_DOE_SALE_2);
    }
}

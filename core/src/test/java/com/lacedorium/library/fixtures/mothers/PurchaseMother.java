package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.purchase.Purchase;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class PurchaseMother extends BaseMother<Purchase> {
    private static final Map<String, Object> AMAZON_INV_1 = Map.of(
            "provider", lazy(ProviderMother::amazon),
            "purchasedAt", date("2026-03-02"),
            "invoice", lazy(PurchaseInvoiceMother::invoice1),
            "createdBy", "test"
    );

    private static final Map<String, Object> BEST_BUY_INV_2 = Map.of(
            "provider", lazy(ProviderMother::bestBuy),
            "purchasedAt", date("2026-03-02"),
            "invoice", lazy(PurchaseInvoiceMother::invoice2),
            "createdBy", "test"
    );

    protected PurchaseMother(Map<String, Object> base) {
        super(Purchase.class, base);
    }

    public static @NonNull PurchaseMother amazonInvoice1() {
        return new PurchaseMother(AMAZON_INV_1);
    }

    public static @NonNull PurchaseMother bestBuyInvoice2() {
        return new PurchaseMother(BEST_BUY_INV_2);
    }
}

package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.purchase.PurchaseLine;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class PurchaseLineMother extends BaseMother<PurchaseLine> {
    private static final Map<String, Object> AMAZON_LINE_1 = Map.of(
            "purchase", lazy(PurchaseMother::amazonInvoice1),
            "book", lazy(BookMother::romeoAndJuliet),
            "quantity", 2,
            "unitPrice", 10.0,
            "discountPercentage", 5.0,
            "total", 19.0
    );

    private static final Map<String, Object> AMAZON_LINE_2 = Map.of(
            "purchase", lazy(PurchaseMother::amazonInvoice1),
            "book", lazy(BookMother::donQuixote),
            "quantity", 3,
            "unitPrice", 15.0,
            "discountPercentage", 10.0,
            "total", 40.5
    );

    private static final Map<String, Object> BEST_BUY_LINE_1 = Map.of(
            "purchase", lazy(PurchaseMother::bestBuyInvoice2),
            "book", lazy(BookMother::romeoAndJuliet),
            "quantity", 1,
            "unitPrice", 20.0,
            "discountPercentage", 0.0,
            "total", 20.0
    );

    protected PurchaseLineMother(Map<String, Object> base) {
        super(PurchaseLine.class, base);
    }

    public static @NonNull PurchaseLineMother amazonLine1() {
        return new PurchaseLineMother(AMAZON_LINE_1);
    }

    public static @NonNull PurchaseLineMother amazonLine2() {
        return new PurchaseLineMother(AMAZON_LINE_2);
    }

    public static @NonNull PurchaseLineMother bestBuyLine1() {
        return new PurchaseLineMother(BEST_BUY_LINE_1);
    }
}

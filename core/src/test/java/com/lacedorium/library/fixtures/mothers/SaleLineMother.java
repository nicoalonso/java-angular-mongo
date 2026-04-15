package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.sale.SaleLine;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class SaleLineMother extends BaseMother<SaleLine> {
    private static final Map<String, Object> JOHN_SALE_1_LINE_1 = Map.of(
            "sale", lazy(SaleMother::johnDoeSale1),
            "book", lazy(BookMother::donQuixote),
            "quantity", 2,
            "price", 10.0,
            "discount", 0.0,
            "total", 20.0
    );

    private static final Map<String, Object> JOHN_SALE_1_LINE_2 = Map.of(
            "sale", lazy(SaleMother::johnDoeSale1),
            "book", lazy(BookMother::romeoAndJuliet),
            "quantity", 1,
            "price", 12.0,
            "discount", 0.0,
            "total", 12.0
    );

    private static final Map<String, Object> JOHN_SALE_2_LINE_1 = Map.of(
            "sale", lazy(SaleMother::johnDoeSale2),
            "book", lazy(BookMother::romeoAndJuliet),
            "quantity", 3,
            "price", 11.0,
            "discount", 5.0,
            "total", 31.35
    );

    protected SaleLineMother(Map<String, Object> base) {
        super(SaleLine.class, base);
    }

    public static @NonNull SaleLineMother johnDoeSale1Line1() {
        return new SaleLineMother(JOHN_SALE_1_LINE_1);
    }

    public static @NonNull SaleLineMother johnDoeSale1Line2() {
        return new SaleLineMother(JOHN_SALE_1_LINE_2);
    }

    public static @NonNull SaleLineMother johnDoeSale2Line1() {
        return new SaleLineMother(JOHN_SALE_2_LINE_1);
    }
}

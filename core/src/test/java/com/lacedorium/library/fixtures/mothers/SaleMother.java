package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.sale.Sale;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class SaleMother extends BaseMother<Sale> {
    private static final Map<String, Object> JOHN_DOE_SALE_1 = Map.of(
            "customer", lazy(CustomerMother::johnDoe),
            "number", "F-00001",
            "invoice", lazy(SaleInvoiceMother::johnDoeSale1),
            "createdBy", "test"
    );

    private static final Map<String, Object> JOHN_DOE_SALE_2 = Map.of(
            "customer", lazy(CustomerMother::johnDoe),
            "number", "F-00001",
            "invoice", lazy(SaleInvoiceMother::johnDoeSale2),
            "createdBy", "test"
    );

    protected SaleMother(Map<String, Object> base) {
        super(Sale.class, base);
    }

    public static @NonNull SaleMother johnDoeSale1() {
        return new SaleMother(JOHN_DOE_SALE_1);
    }

    public static @NonNull SaleMother johnDoeSale2() {
        return new SaleMother(JOHN_DOE_SALE_2);
    }
}

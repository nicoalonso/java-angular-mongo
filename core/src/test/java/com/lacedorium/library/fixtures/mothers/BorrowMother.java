package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.borrow.Borrow;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class BorrowMother extends BaseMother<Borrow> {
    private static final Map<String, Object> JOHN_DOE = Map.of(
            "customer", lazy(CustomerMother::johnDoe),
            "number", "P-00022",
            "totalBooks", 3,
            "createdBy", "test"
    );

    protected BorrowMother(Map<String, Object> base) {
        super(Borrow.class, base);
    }

    public static @NonNull BorrowMother johnDoe() {
        return new BorrowMother(JOHN_DOE);
    }
}

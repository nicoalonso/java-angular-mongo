package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.book.BookSale;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class BookSaleMother extends BaseMother<BookSale> {
    private static final Map<String, Object> VALID = Map.of(
            "saleable", true,
            "price", 100.0,
            "discount", 10.0
    );

    protected BookSaleMother(Map<String, Object> base) {
        super(BookSale.class, base);
    }

    public static @NonNull BookSaleMother valid() {
        return new BookSaleMother(VALID);
    }
}

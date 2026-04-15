package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.borrow.BorrowLine;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class BorrowLineMother extends BaseMother<BorrowLine> {
    public static final Map<String, Object> ROMEO_AND_JULIET = Map.of(
        "borrow", lazy(BorrowMother::johnDoe),
        "book", lazy(BookMother::romeoAndJuliet)
    );

    public static final Map<String, Object> DON_QUIXOTE = Map.of(
        "borrow", lazy(BorrowMother::johnDoe),
        "book", lazy(BookMother::donQuixote)
    );

    protected BorrowLineMother(Map<String, Object> base) {
        super(BorrowLine.class, base);
    }

    public static @NonNull BorrowLineMother romeoAndJuliet() {
        return new BorrowLineMother(ROMEO_AND_JULIET);
    }

    public static @NonNull BorrowLineMother donQuixote() {
        return new BorrowLineMother(DON_QUIXOTE);
    }
}

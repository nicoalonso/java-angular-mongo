package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.book.BookDetail;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class BookDetailMother extends BaseMother<BookDetail> {
    private static final Map<String, Object> VALID = Map.of(
            "edition", "001",
            "isbn", "978-1234567890",
            "language", "English",
            "publishedAt", date("2020-01-01"),
            "pages", 100
    );

    private static final Map<String, Object> QUIJOTE = Map.of(
            "edition", "001",
            "isbn", "978-1234567890",
            "language", "Spanish",
            "publishedAt", date("1615-01-01"),
            "pages", 100
    );

    protected BookDetailMother(Map<String, Object> base) {
        super(BookDetail.class, base);
    }

    public static @NonNull BookDetailMother valid() {
        return new BookDetailMother(VALID);
    }

    public static @NonNull BookDetailMother quijote() {
        return new BookDetailMother(QUIJOTE);
    }
}

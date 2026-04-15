package com.lacedorium.library.fixtures.mothers;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.fixtures.mothers.base.BaseMother;
import lombok.NonNull;

import java.util.Map;

public class BookMother extends BaseMother<Book> {
    private static final Map<String, Object> ROMEO_AND_JULIET = Map.of(
            "title", "Romeo and Juliet",
            "description", "Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families.",
            "author", lazy(AuthorMother::shakespeare),
            "editorial", lazy(EditorialMother::anaya),
            "detail", lazy(BookDetailMother::valid),
            "sale", lazy(BookSaleMother::valid),
            "createdBy", "test"
    );

    private static final Map<String, Object> DON_QUIXOTE = Map.of(
            "title", "Don Quijote",
            "description", "Don Quijote de la Mancha is a Spanish novel by Miguel de Cervantes. It follows the adventures of a nobleman who reads so many chivalric romances that he loses his sanity and decides to become a knight-errant, reviving chivalry and serving his nation.",
            "author", lazy(AuthorMother::cervantes),
            "editorial", lazy(EditorialMother::anaya),
            "detail", lazy(BookDetailMother::quijote),
            "sale", lazy(BookSaleMother::valid),
            "createdBy", "test"
    );

    protected BookMother(Map<String, Object> base) {
        super(Book.class, base);
    }

    public static @NonNull BookMother romeoAndJuliet() {
        return new BookMother(ROMEO_AND_JULIET);
    }

    public static @NonNull BookMother donQuixote() {
        return new BookMother(DON_QUIXOTE);
    }
}

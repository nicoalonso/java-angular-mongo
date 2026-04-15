package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.domain.book.BookDetail;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record BookDetailRecord(
        String edition,
        String isbn,
        String language,
        String publishedAt,
        Integer pages
) {
    public static @NonNull BookDetailRecord make(@NonNull BookDetail detail) {
        return new BookDetailRecord(
                detail.getEdition(),
                detail.getIsbn(),
                detail.getLanguage(),
                Result.formatShortDate(detail.getPublishedAt()),
                detail.getPages()
        );
    }
}

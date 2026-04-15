package com.lacedorium.library.application.book.creator.payload;

import com.lacedorium.library.domain.book.exception.InvalidPublishedDateException;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BookDetailPayload (
        String edition,
        String isbn,
        String language,
        LocalDate publishedAt,
        Integer pages
) {
    public @NonNull LocalDateTime getPublishedAt() {
        if (publishedAt == null) {
            throw new InvalidPublishedDateException("PublishedAt is required");
        }

        return publishedAt.atStartOfDay();
    }
}

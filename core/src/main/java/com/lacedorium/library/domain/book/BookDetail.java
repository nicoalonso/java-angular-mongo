package com.lacedorium.library.domain.book;

import com.lacedorium.library.domain.book.exception.InvalidIsbnException;
import com.lacedorium.library.domain.book.exception.InvalidPublishedDateException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookDetail {
    private String edition;
    private String isbn;
    private String language;
    private LocalDateTime publishedAt;
    private Integer pages;

    public BookDetail(String edition, String isbn, String language, LocalDateTime publishedAt, Integer pages) {
        check(isbn, publishedAt);

        this.edition = edition;
        this.isbn = isbn;
        this.language = language;
        this.publishedAt = publishedAt;
        this.pages = pages;
    }

    private void check(String isbn, LocalDateTime publishedAt) {
        if (isbn == null || isbn.isBlank()) {
            throw new InvalidIsbnException();
        }

        if (publishedAt == null) {
            throw new InvalidPublishedDateException("PublishedAt is required");
        }

        LocalDateTime limit = LocalDateTime.now().plusDays(1);
        if (publishedAt.isAfter(limit)) {
            throw new InvalidPublishedDateException();
        }
    }
}

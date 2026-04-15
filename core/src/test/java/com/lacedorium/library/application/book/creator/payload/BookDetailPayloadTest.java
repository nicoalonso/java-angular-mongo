package com.lacedorium.library.application.book.creator.payload;

import com.lacedorium.library.domain.book.exception.InvalidPublishedDateException;
import com.lacedorium.library.fixtures.payload.FixtureBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDetailPayloadTest {
    @Test
    void shouldFailWhenInvalidPublishedDate() {
        FixtureBuilder<BookDetailPayload> builder = new FixtureBuilder<>(BookDetailPayload.class, "book");
        BookDetailPayload payload = builder.with("publishedAt", null).build();

        assertThrows(InvalidPublishedDateException.class, payload::getPublishedAt);
    }
}
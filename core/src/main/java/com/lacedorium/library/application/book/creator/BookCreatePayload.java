package com.lacedorium.library.application.book.creator;

import com.lacedorium.library.application.book.creator.payload.BookDetailPayload;
import com.lacedorium.library.application.book.creator.payload.BookSalePayload;

public record BookCreatePayload (
        String title,
        String description,
        String authorId,
        String editorialId,
        BookDetailPayload detail,
        BookSalePayload sale
) { }

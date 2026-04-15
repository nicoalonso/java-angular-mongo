package com.lacedorium.library.application.book.updater;

import com.lacedorium.library.application.book.creator.payload.BookDetailPayload;
import com.lacedorium.library.application.book.creator.payload.BookSalePayload;

public record BookUpdatePayload (
        String title,
        String description,
        String authorId,
        String editorialId,
        BookDetailPayload detail,
        BookSalePayload sale
) { }

package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.domain.book.Book;
import com.lacedorium.library.presentation.v1.author.AuthorDescriptorRecord;
import com.lacedorium.library.presentation.v1.editorial.EditorialDescriptorRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

public record BookReadRecord (
        String id,
        String title,
        AuthorDescriptorRecord author,
        EditorialDescriptorRecord editorial,
        BookDetailRecord detail,
        BookSaleRecord sale,
        Integer stock,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull BookReadRecord make(@NonNull Book book) {
        return new BookReadRecord(
                book.getId(),
                book.getTitle(),
                AuthorDescriptorRecord.make(book.getAuthor()),
                EditorialDescriptorRecord.make(book.getEditorial()),
                BookDetailRecord.make(book.getDetail()),
                BookSaleRecord.make(book.getSale()),
                book.getStock(),
                book.getCreatedBy(),
                Result.formatDate(book.getCreatedAt()),
                book.getUpdatedBy(),
                Result.formatDate(book.getUpdatedAt())
        );
    }
}

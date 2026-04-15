package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.domain.book.BookDescriptor;
import org.jspecify.annotations.NonNull;

public record BookDescriptorRecord(
        String id,
        String title,
        String isb
) {
    public static @NonNull BookDescriptorRecord make(@NonNull BookDescriptor book) {
        return new BookDescriptorRecord(
                book.getId(),
                book.getTitle(),
                book.getIsbn()
        );
    }
}

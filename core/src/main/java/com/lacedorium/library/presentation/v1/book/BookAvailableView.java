package com.lacedorium.library.presentation.v1.book;

import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

record BookAvailableRecord (
        Boolean available
) {
    public static @NonNull BookAvailableRecord make(@NonNull Boolean available) {
        return new BookAvailableRecord(available);
    }
}

public class BookAvailableView extends Result<BookAvailableRecord, Boolean> {
    public BookAvailableView(@NonNull Boolean available) {
        super(available, BookAvailableRecord::make);
    }
}

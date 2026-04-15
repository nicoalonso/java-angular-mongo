package com.lacedorium.library.infrastructure.messenger.serializer.book;

import com.lacedorium.library.domain.book.BookDescriptor;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;

@Getter
public class BookDescriptorSerializer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String title;
    private final String isbn;

    public BookDescriptorSerializer(@NonNull BookDescriptor descriptor) {
        this.id = descriptor.getId();
        this.title = descriptor.getTitle();
        this.isbn = descriptor.getIsbn();
    }

    public static @NonNull BookDescriptor decode(@NonNull LinkedHashMap<String, Object> payload) {
        String id = (String) payload.get("id");
        String title = (String) payload.get("title");
        String isbn = (String) payload.get("isbn");
        return new BookDescriptor(id, title, isbn);
    }
}

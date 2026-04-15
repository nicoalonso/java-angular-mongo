package com.lacedorium.library.infrastructure.messenger.serializer.book;

import com.lacedorium.library.application.book.inventory.BookInventoryEvent;
import com.lacedorium.library.domain.book.BookDescriptor;
import com.lacedorium.library.infrastructure.messenger.serializer.Envelope;
import lombok.NonNull;

import java.util.LinkedHashMap;

public class BookInventorySerializer extends Envelope<BookDescriptorSerializer> {
    public BookInventorySerializer(@NonNull BookInventoryEvent event) {
        super(event.getAction(), event.getType(), new BookDescriptorSerializer(event.getDescriptor()));
    }

    public static @NonNull BookInventoryEvent decode(@NonNull LinkedHashMap<String, Object> payload) {
        BookDescriptor descriptor = BookDescriptorSerializer.decode(payload);
        return new BookInventoryEvent(descriptor);
    }
}

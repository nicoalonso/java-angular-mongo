package com.lacedorium.library.presentation.v1.author;

import com.lacedorium.library.domain.author.AuthorDescriptor;
import org.jspecify.annotations.NonNull;

public record AuthorDescriptorRecord (
        String id,
        String name
) {
    public static @NonNull AuthorDescriptorRecord make(@NonNull AuthorDescriptor descriptor) {
        return new AuthorDescriptorRecord(
                descriptor.getId(),
                descriptor.getName()
        );
    }
}

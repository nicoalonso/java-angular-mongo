package com.lacedorium.library.presentation.v1.editorial;

import com.lacedorium.library.domain.editorial.EditorialDescriptor;
import org.jspecify.annotations.NonNull;

public record EditorialDescriptorRecord(
        String id,
        String name
) {
    public static @NonNull EditorialDescriptorRecord make(@NonNull EditorialDescriptor descriptor) {
        return new EditorialDescriptorRecord(
                descriptor.getId(),
                descriptor.getName()
        );
    }
}

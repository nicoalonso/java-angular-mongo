package com.lacedorium.library.presentation.v1.provider;

import com.lacedorium.library.domain.provider.ProviderDescriptor;
import lombok.NonNull;

public record ProviderDescriptorRecord (
        String id,
        String name
) {
    public static @NonNull ProviderDescriptorRecord make(@NonNull ProviderDescriptor provider) {
        return new ProviderDescriptorRecord(
                provider.getId(),
                provider.getName()
        );
    }
}

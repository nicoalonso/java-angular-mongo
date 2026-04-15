package com.lacedorium.library.presentation.v1.customer;

import com.lacedorium.library.domain.customer.CustomerDescriptor;
import lombok.NonNull;

public record CustomerDescriptorRecord (
        String id,
        String name,
        String surname,
        String vatNumber,
        String number
) {
    public static @NonNull CustomerDescriptorRecord make(@NonNull CustomerDescriptor descriptor) {
        return new CustomerDescriptorRecord(
                descriptor.getId(),
                descriptor.getName(),
                descriptor.getSurname(),
                descriptor.getVatNumber(),
                descriptor.getNumber()
        );
    }
}

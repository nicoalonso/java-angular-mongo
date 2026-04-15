package com.lacedorium.library.presentation.v1.customer;

import com.lacedorium.library.domain.customer.ContactInfo;
import lombok.NonNull;

public record ContactInfoRecord (
        String email,
        String phone1,
        String phone2
) {
    public static @NonNull ContactInfoRecord make(@NonNull ContactInfo contact) {
        return new ContactInfoRecord(
                contact.getEmail(),
                contact.getPhone1(),
                contact.getPhone2()
        );
    }
}

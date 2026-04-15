package com.lacedorium.library.presentation.v1.common;

import com.lacedorium.library.domain.common.EnterpriseContact;
import lombok.NonNull;

public record EnterpriseContactRecord (
        String email,
        String website,
        String phone1,
        String phone2
) {
    public static @NonNull EnterpriseContactRecord make(@NonNull EnterpriseContact contact) {
        return new EnterpriseContactRecord(
                contact.getEmail(),
                contact.getWebsite(),
                contact.getPhone1(),
                contact.getPhone2()
        );
    }
}

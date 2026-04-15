package com.lacedorium.library.presentation.v1.editorial;

import com.lacedorium.library.domain.editorial.Editorial;
import com.lacedorium.library.presentation.v1.common.AddressRecord;
import com.lacedorium.library.presentation.v1.common.EnterpriseContactRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import lombok.NonNull;

public record EditorialReadRecord(
        String id,
        String name,
        String comercialName,
        EnterpriseContactRecord contact,
        AddressRecord address,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull EditorialReadRecord make(@NonNull Editorial editorial) {
        return new EditorialReadRecord(
                editorial.getId(),
                editorial.getName(),
                editorial.getComercialName(),
                EnterpriseContactRecord.make(editorial.getContact()),
                AddressRecord.make(editorial.getAddress()),
                editorial.getCreatedBy(),
                Result.formatDate(editorial.getCreatedAt()),
                editorial.getUpdatedBy(),
                Result.formatDate(editorial.getUpdatedAt())
        );
    }
}

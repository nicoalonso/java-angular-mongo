package com.lacedorium.library.presentation.v1.provider;

import com.lacedorium.library.domain.provider.Provider;
import com.lacedorium.library.presentation.v1.common.AddressRecord;
import com.lacedorium.library.presentation.v1.common.EnterpriseContactRecord;
import com.lacedorium.library.presentation.v1.identity.Result;
import org.jspecify.annotations.NonNull;

public record ProviderReadRecord(
        String id,
        String name,
        String comercialName,
        EnterpriseContactRecord contact,
        AddressRecord address,
        String vatNumber,
        String createdBy,
        String createdAt,
        String updatedBy,
        String updatedAt
) {
    public static @NonNull ProviderReadRecord make(@NonNull Provider provider) {
        return new ProviderReadRecord(
                provider.getId(),
                provider.getName(),
                provider.getComercialName(),
                EnterpriseContactRecord.make(provider.getContact()),
                AddressRecord.make(provider.getAddress()),
                provider.getVatNumber(),
                provider.getCreatedBy(),
                Result.formatDate(provider.getCreatedAt()),
                provider.getUpdatedBy(),
                Result.formatDate(provider.getUpdatedAt())
        );
    }
}

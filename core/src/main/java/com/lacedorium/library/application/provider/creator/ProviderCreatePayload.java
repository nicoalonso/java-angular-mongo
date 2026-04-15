package com.lacedorium.library.application.provider.creator;

import com.lacedorium.library.application.identity.payload.AddressPayload;
import com.lacedorium.library.application.identity.payload.EnterpriseContactPayload;

public record ProviderCreatePayload (
        String name,
        String comercialName,
        EnterpriseContactPayload contact,
        AddressPayload address,
        String vatNumber
) { }

package com.lacedorium.library.application.provider.updater;

import com.lacedorium.library.application.identity.payload.AddressPayload;
import com.lacedorium.library.application.identity.payload.EnterpriseContactPayload;

public record ProviderUpdatePayload(
        String name,
        String comercialName,
        EnterpriseContactPayload contact,
        AddressPayload address,
        String vatNumber
) { }

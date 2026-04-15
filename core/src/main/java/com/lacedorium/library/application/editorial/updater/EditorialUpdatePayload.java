package com.lacedorium.library.application.editorial.updater;

import com.lacedorium.library.application.identity.payload.AddressPayload;
import com.lacedorium.library.application.identity.payload.EnterpriseContactPayload;

public record EditorialUpdatePayload (
        String name,
        String comercialName,
        EnterpriseContactPayload contact,
        AddressPayload address
) { }

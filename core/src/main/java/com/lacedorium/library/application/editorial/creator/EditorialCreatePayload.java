package com.lacedorium.library.application.editorial.creator;

import com.lacedorium.library.application.identity.payload.AddressPayload;
import com.lacedorium.library.application.identity.payload.EnterpriseContactPayload;

public record EditorialCreatePayload(
        String name,
        String comercialName,
        EnterpriseContactPayload contact,
        AddressPayload address
) {
}

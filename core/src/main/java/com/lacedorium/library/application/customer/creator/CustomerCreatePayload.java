package com.lacedorium.library.application.customer.creator;

import com.lacedorium.library.application.customer.creator.payload.ContactPayload;
import com.lacedorium.library.application.identity.payload.AddressPayload;

public record CustomerCreatePayload (
        String name,
        String surname,
        ContactPayload contact,
        AddressPayload address,
        String vatNumber
) { }

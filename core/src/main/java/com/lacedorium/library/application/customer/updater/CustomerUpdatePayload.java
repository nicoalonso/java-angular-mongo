package com.lacedorium.library.application.customer.updater;

import com.lacedorium.library.application.customer.creator.payload.ContactPayload;
import com.lacedorium.library.application.customer.updater.payload.MembershipUpdatePayload;
import com.lacedorium.library.application.identity.payload.AddressPayload;

public record CustomerUpdatePayload (
        String name,
        String surname,
        MembershipUpdatePayload membership,
        ContactPayload contact,
        AddressPayload address,
        String vatNumber
) { }

package com.lacedorium.library.application.identity.payload;

public record AddressPayload(
        String street,
        String postalCode,
        String city,
        String province,
        String country
) {
}

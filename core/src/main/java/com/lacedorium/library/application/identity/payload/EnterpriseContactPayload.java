package com.lacedorium.library.application.identity.payload;

public record EnterpriseContactPayload(
        String email,
        String website,
        String phone1,
        String phone2
) {
}

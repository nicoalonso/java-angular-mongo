package com.lacedorium.library.application.customer.creator.payload;

public record ContactPayload (
        String email,
        String phone1,
        String phone2
) { }

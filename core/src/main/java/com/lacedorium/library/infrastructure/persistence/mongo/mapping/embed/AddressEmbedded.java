package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

@Data
public class AddressEmbedded {
    private String street;
    private String postalCode;
    private String city;
    private String province;
    private String country;
}

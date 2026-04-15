package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

@Data
public class ContactInfoEmbedded {
    private String email;
    private String phone1;
    private String phone2;
}

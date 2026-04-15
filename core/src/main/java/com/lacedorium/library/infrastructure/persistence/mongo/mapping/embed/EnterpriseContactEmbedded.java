package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;

@Data
public class EnterpriseContactEmbedded {
    private String email;
    private String website;
    private String phone1;
    private String phone2;
}

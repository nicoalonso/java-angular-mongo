package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class CustomerDescriptorEmbedded {
    @Field("id")
    private String id;
    private String name;
    private String surname;
    private String vatNumber;
    private String number;
}

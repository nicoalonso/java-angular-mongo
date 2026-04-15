package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class AuthorDescriptorEmbedded {
    @Field("id")
    private String id;
    private String name;
}

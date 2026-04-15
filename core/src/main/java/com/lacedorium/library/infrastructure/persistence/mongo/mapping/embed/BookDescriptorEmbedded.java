package com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class BookDescriptorEmbedded {
    @Field("id")
    private String id;
    private String title;
    private String isbn;
}

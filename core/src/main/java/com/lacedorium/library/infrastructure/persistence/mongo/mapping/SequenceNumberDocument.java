package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sequences" )
public class SequenceNumberDocument {
    @Id
    private String id;
    private String type;
    private String prefix;
    private Integer number;
}

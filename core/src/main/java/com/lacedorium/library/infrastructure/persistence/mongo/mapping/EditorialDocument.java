package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.AddressEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.EnterpriseContactEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "editorials")
public class EditorialDocument {
    @Id
    private String id;
    private String name;
    private String comercialName;
    private AddressEmbedded address;
    private EnterpriseContactEmbedded contact;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

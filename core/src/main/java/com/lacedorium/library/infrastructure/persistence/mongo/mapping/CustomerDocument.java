package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.AddressEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.ContactInfoEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.MembershipEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "customers")
public class CustomerDocument {
    @Id
    private String id;
    private String name;
    private String surname;
    private MembershipEmbedded membership;
    private ContactInfoEmbedded contact;
    private AddressEmbedded address;
    private String vatNumber;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

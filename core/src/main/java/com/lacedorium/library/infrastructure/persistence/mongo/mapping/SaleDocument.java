package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.CustomerDescriptorEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.SaleInvoiceEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "sales")
public class SaleDocument {
    @Id
    private String id;
    private CustomerDescriptorEmbedded customer;
    private String number;
    private SaleInvoiceEmbedded invoice;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

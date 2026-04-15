package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.ProviderDescriptorEmbedded;
import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.PurchaseInvoiceEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "purchases")
public class PurchaseDocument {
    @Id
    private String id;
    private ProviderDescriptorEmbedded provider;
    private LocalDateTime purchasedAt;
    private PurchaseInvoiceEmbedded invoice;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

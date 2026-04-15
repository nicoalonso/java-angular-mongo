package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.BookDescriptorEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "purchaseLines")
public class PurchaseLineDocument {
    @Id
    private String id;
    private String purchase;
    private BookDescriptorEmbedded book;
    private Integer quantity;
    private Double unitPrice;
    private Double discountPercentage;
    private Double total;
}

package com.lacedorium.library.infrastructure.persistence.mongo.mapping;

import com.lacedorium.library.infrastructure.persistence.mongo.mapping.embed.BookDescriptorEmbedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "saleLines")
public class SaleLineDocument {
    @Id
    private String id;
    private String sale;
    private BookDescriptorEmbedded book;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double total;
}
